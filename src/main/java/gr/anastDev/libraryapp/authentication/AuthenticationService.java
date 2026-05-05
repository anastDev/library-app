package gr.anastDev.libraryapp.authentication;

import gr.anastDev.libraryapp.dto.AuthenticationRequestDTO;
import gr.anastDev.libraryapp.dto.AuthenticationResponseDTO;
import gr.anastDev.libraryapp.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO dto) {
        log.info("Attempting authentication for username={}", dto.username());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
            log.info("Authentication successful for username={}", dto.username());
            log.info("Principal type={}", authentication.getPrincipal().getClass().getSimpleName());
            User user = (User) authentication.getPrincipal();
            log.info("User role={}", user.getRole().name());
            String token = jwtService.generateToken(authentication.getName(), user.getRole().name());
            log.info("Token generated={}", token);
            return new AuthenticationResponseDTO(user.getFirstname(), user.getLastname(), token);
        } catch (Exception e) {
            log.error("Authentication failed for username={}, error={}", dto.username(), e.getMessage(), e);
            throw e;
        }
    }
}
