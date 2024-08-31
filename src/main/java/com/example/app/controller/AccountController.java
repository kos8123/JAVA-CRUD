package com.example.app.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import com.example.app.serivce.UserService;
import com.example.app.entity.AccountEntity;

import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/api")
public class AccountController {

    @GetMapping
    public String HelloWorld() {
        return "HelloWorld";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/getUser")
    public ResponseEntity<List<AccountEntity>> getAllUser() {
    
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody AccountEntity users) {
        AccountEntity newUser = userService.addUser(users);
        return new ResponseEntity<>("User Created", HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity updateUser(@RequestBody AccountEntity users, @PathVariable Integer id) {
        AccountEntity updatedUser = userService.updateUser(users, id);
        return new ResponseEntity<>("User Updated", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User Deleted", HttpStatus.CREATED);
    }
}
