package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BorrowedInsertDTO;
import gr.anastDev.libraryapp.dto.BorrowedReadOnlyDTO;
import gr.anastDev.libraryapp.mapper.Mapper;
import gr.anastDev.libraryapp.model.Book;
import gr.anastDev.libraryapp.model.Borrowed;
import gr.anastDev.libraryapp.model.Member;
import gr.anastDev.libraryapp.repository.BookRepository;
import gr.anastDev.libraryapp.repository.BorrowedRepository;
import gr.anastDev.libraryapp.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BorrowedService implements IBorrowedService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BorrowedRepository borrowedRepository;
    private final Mapper mapper;

    @Override
    @Transactional(rollbackOn = {EntityNotFoundException.class, EntityInvalidArgumentException.class})
    public Borrowed borrowBook(BorrowedInsertDTO dto) throws EntityNotFoundException, EntityInvalidArgumentException {
        try{
            Member member = memberRepository.findByUuid(dto.getMemberUuid())
                    .orElseThrow(() -> new EntityNotFoundException("Member", "Member with username " + dto.getMemberUuid() + "not found!"));

            Book book = bookRepository.findByIsbn(dto.getBookIsbn())
                    .orElseThrow(() -> new EntityNotFoundException("Book", "Book with ISBN " + dto.getBookIsbn() + "not found!"));

            if (book.getAvailableCopies() <= 0) {
                throw new EntityInvalidArgumentException("Book", "Book with ISBN " + dto.getBookIsbn() + "has no available copies!");
            }

            Instant borrowedAt = Instant.now();
            Instant dueDate = borrowedAt.plus(14, ChronoUnit.DAYS);

            Borrowed borrowed = new Borrowed(null, member, book, borrowedAt, dueDate, null);
            member.addBorrow(borrowed);
            book.setAvailableCopies(book.getAvailableCopies() - 1);

            borrowedRepository.save(borrowed);
            log.info("Member uuid={} borrowed book isbn={}.", dto.getMemberUuid(), dto.getBookIsbn());
            return borrowed;
        } catch (EntityNotFoundException e) {
            log.error("Borrow failed. Entity not found. memberId={}, bookIsbn={}.", dto.getMemberUuid(), dto.getBookIsbn(), e);
            throw e;
        }
    }

    @Override
    public List<BorrowedReadOnlyDTO> getBorrowedByMemberUuid(String uuid) throws EntityNotFoundException {
        Member member = memberRepository.findByUuid(uuid)
                .orElseThrow(() -> new EntityNotFoundException("Member", "Member with uuid " + uuid + "not found!"));

        List<Borrowed> borrowed = borrowedRepository.findByMember(member);
        return borrowed.stream()
                .map(mapper::mapToBorrowedReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BorrowedReadOnlyDTO> getBorrowedByBookIsbn(String isbn) throws EntityNotFoundException {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Book", "Book with isbn " + isbn + "not found!"));

        List<Borrowed> borrowed = borrowedRepository.findByBook(book);
        return borrowed.stream()
                .map(mapper::mapToBorrowedReadOnlyDTO)
                .collect(Collectors.toList());
    }
}
