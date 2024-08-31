package com.example.app.serivce;

import ch.qos.logback.core.model.processor.ProcessorException;
import com.example.app.entity.AccountEntity;

import com.example.app.repository.AccountRepository;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


@Service("userService")
public class UserService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountEntity> getAllUsers() {

        return accountRepository.findAll();
    }

    @ExceptionHandler()

    public Optional<AccountEntity> getUserById(Integer id) {
        Optional<AccountEntity> isIdExist = accountRepository.findById(id);
        if (!isIdExist.isPresent()) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);
        } else {
            return accountRepository.findById(id);
        }
    }


    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "test");

    public AccountEntity addUser(AccountEntity users) {
        Optional<AccountEntity> isExistUser = accountRepository.findByUsername(users.username);
        if (isExistUser.isPresent()) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        } else {
            return accountRepository.save(users);
        }
    }

    public AccountEntity updateUser(AccountEntity users, Integer id) {
        Optional<AccountEntity> isExistUser = accountRepository.findById(id);
        if (!isExistUser.isPresent()) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        } else {
            return accountRepository.save(users);
        }
    }


    public Optional<AccountEntity> deleteUser(Integer id) {
        Optional<AccountEntity> isExistUser = accountRepository.findById(id);
        if (!isExistUser.isPresent()) {
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        } else {
            AccountEntity deletedUser = isExistUser.get();
            accountRepository.deleteById(id);
            return Optional.of(deletedUser);
        }
    }

}

