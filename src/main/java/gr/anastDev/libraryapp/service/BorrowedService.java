package gr.anastDev.libraryapp.service;

import gr.anastDev.libraryapp.core.exceptions.EntityInvalidArgumentException;
import gr.anastDev.libraryapp.core.exceptions.EntityNotFoundException;
import gr.anastDev.libraryapp.dto.BorrowedInsertDTO;
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
            Member member = memberRepository.findById(dto.getMemberId())
                    .orElseThrow(() -> new EntityNotFoundException("Member", "Member with id " + dto.getMemberId() + "not found!"));

            Book book = bookRepository.findByIsbn(dto.getBookIsbn())
                    .orElseThrow(() -> new EntityNotFoundException("Book", "Book with ISBN " + dto.getBookIsbn() + "not found!"));

            Instant borrowedAt = Instant.now();
            Instant dueDate = borrowedAt.plus(14, ChronoUnit.DAYS);

            Borrowed borrowed = new Borrowed(null, member, book, borrowedAt, dueDate, null);
            member.addBorrow(borrowed);
            book.setAvailableCopies(book.getAvailableCopies() - 1);

            borrowedRepository.save(borrowed);
            log.info("Member id={} borrowed book isbn={}.", dto.getMemberId(), dto.getBookIsbn());
            return borrowed;
        } catch (EntityNotFoundException e) {
            log.error("Borrow failed. Entity not found. memberId={}, bookIsbn={}.", dto.getMemberId(), dto.getBookIsbn(), e);
            throw e;
        }
    }
}
