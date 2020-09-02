package com.bridgelabz.usermanagement.service;

import com.bridgelabz.usermanagement.dto.LogInDTO;
import com.bridgelabz.usermanagement.exception.UserException;
import com.bridgelabz.usermanagement.model.Email;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.repository.UserRepository;
import com.bridgelabz.usermanagement.util.JWTTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

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
    public User login(LogInDTO logInDTO) {
        Optional<User> byEmail = userRepository.findByEmail(logInDTO.email);
        if (!byEmail.isPresent()) {
            throw new UserException("email not registered with us", UserException.ExceptionType.WRONG_EMAIL, HttpStatus.UNAUTHORIZED.value());
        }
        if (passwordEncoder.matches(logInDTO.password, byEmail.get().password)) {
            return byEmail.get();
        }
        throw new UserException("Incorrect password", UserException.ExceptionType.WRONG_EMAIL,HttpStatus.UNAUTHORIZED.value());
    }

    @Override
    public boolean verifyJWTToken(String token) {
        String jwtToken, userEmail = null;
        Date expirationTime = null;
        if (token != null && token.startsWith("Bearer ")) {
            jwtToken = token.substring(7);
            try {
                userEmail = jwtTokenUtil.getUsernameFromToken(jwtToken);
                expirationTime = jwtTokenUtil.getExpirationDateFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        }
        if (userEmail != null && userRepository.findByEmail(userEmail).isPresent() && expirationTime.after(new Date())) {
            return true;
        }
        throw new UserException("Invalid token", UserException.ExceptionType.INVALIDE_TOKEN,HttpStatus.UNAUTHORIZED.value());
    }

    @Override
    public boolean resetPassword(String email) throws MessagingException {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (!byEmail.isPresent()) {
            throw new UserException("email not registered with us", UserException.ExceptionType.WRONG_EMAIL,HttpStatus.UNAUTHORIZED.value());
        }
        User user = byEmail.get();
        final String newPassword = UUID.randomUUID().toString().substring(1,6);

        user.password =passwordEncoder.encode(newPassword);
        userRepository.save(user);
        final String message = "Your new password is " + newPassword;

        rabbitMQSender.send(new Email(user.email,"ResetPassword",message));
        return true;
    }
}
