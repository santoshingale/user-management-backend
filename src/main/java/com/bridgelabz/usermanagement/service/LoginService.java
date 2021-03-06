package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.LogInDTO;
import com.bridgelabz.usermanagement.dto.TokenResponseDTO;
import com.bridgelabz.usermanagement.exception.LoginException;
import com.bridgelabz.usermanagement.model.Email;
import com.bridgelabz.usermanagement.model.LoginHistory;
import com.bridgelabz.usermanagement.model.UserData;
import com.bridgelabz.usermanagement.repository.UserDataRepository;
import com.bridgelabz.usermanagement.response.Responce;
import com.bridgelabz.usermanagement.util.JWTTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Slf4j
@Service
public class LoginService implements ILoginService {

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    @Autowired
    RabbitMQSender rabbitMQSender;

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    @Transactional
    public ResponseEntity login(LogInDTO logInDTO) {
        Optional<UserData> byEmail = userDataRepository.findByEmail(logInDTO.email);
        if (!byEmail.isPresent()) {
            throw new LoginException("Email not registered", LoginException.ExceptionType.WRONG_EMAIL, HttpStatus.UNAUTHORIZED.value());
        }
        if (passwordEncoder.matches(logInDTO.password, byEmail.get().getPassword())) {
            if (byEmail.get().getWorongLoginAttempt() > 2) {
                throw new LoginException("pls reset your password", LoginException.ExceptionType.RESET_PASSWORD, HttpStatus.UNAUTHORIZED.value());
            }
            UserData userData = byEmail.get();
            String token = jwtTokenUtil.generateToken(userData);
            userData.setStatus("Active");
            userData.getLastLogin().add(new LoginHistory(LocalDateTime.now()));
            userData.setWorongLoginAttempt(0);
            userDataRepository.save(userData);
            return new ResponseEntity(new Responce(HttpStatus.OK.value(), "Successfully login", new TokenResponseDTO(token)), HttpStatus.OK);
        }
        userDataRepository.updateWrongAttempts(byEmail.get().getId(), byEmail.get().getWorongLoginAttempt() + 1);
        throw new LoginException("Incorrect password", LoginException.ExceptionType.WRONG_EMAIL, HttpStatus.UNAUTHORIZED.value());
    }

    @Override
    public boolean verifyJWTToken(String token) {
        String jwtToken, userEmail = null;
        Date expirationTime = null;
        if (token != null && token.startsWith("Bearer ")) {
            jwtToken = token.substring(7);
            try {
                userEmail = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.info("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.info("JWT Token has expired");
            }
        }

        Optional<UserData> byEmail = userDataRepository.findByEmail(userEmail);
        if (userEmail != null && byEmail.isPresent()) {
            if (jwtTokenUtil.validateToken(token.substring(7), byEmail.get()))
                return true;
            return false;
        }
        throw new LoginException("Invalid token", LoginException.ExceptionType.INVALID_TOKEN, HttpStatus.UNAUTHORIZED.value());
    }

    @Override
    public boolean resetPassword(String email) throws MessagingException {
        Optional<UserData> byEmail = userDataRepository.findByEmail(email);
        if (!byEmail.isPresent()) {
            throw new LoginException("email not registered with us", LoginException.ExceptionType.WRONG_EMAIL, HttpStatus.UNAUTHORIZED.value());
        }
        UserData userData = byEmail.get();
        final String newPassword = UUID.randomUUID().toString().substring(1, 6);

        userData.setPassword(passwordEncoder.encode(newPassword));
        userDataRepository.save(userData);
        final String message = "Your new password is " + newPassword;

        rabbitMQSender.send(new Email(userData.getEmail(), "ResetPassword", message));
        return true;
    }
}
