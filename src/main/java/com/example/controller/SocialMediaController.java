package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountNotFoundException;
import com.example.exception.AlreadyExistsException;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
 @RestController
 public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register")
    public ResponseEntity<?> registerAccount(@RequestBody  Account account){
        try{
            Account registeredAccount = accountService.registerAccount(account);                                          
            return ResponseEntity.ok(registeredAccount);
        }catch(AlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> loginAccount(@RequestBody  Account account){
        try{
            Account registeredAccount = accountService.loginAccount(account);                                          
            return ResponseEntity.ok(registeredAccount);
        }catch(AccountNotFoundException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("messages")
    public ResponseEntity<?> createMessage(@RequestBody  Message message){
        try{
            Message newMessage = messageService.createMessage(message);                                          
            return ResponseEntity.ok(newMessage);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PatchMapping("messages/{messageId}")
    public ResponseEntity<?> updateMessage(@RequestBody  Message message, @PathVariable Integer messageId){
        try{
            int rowsAffected = messageService.updateMessage(message, messageId);                                          
            return ResponseEntity.ok(rowsAffected);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId){
        try{
           Integer rowsAffected = messageService.deleteMessage(messageId);                                          
            return ResponseEntity.ok(rowsAffected);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

   @GetMapping("messages")
    public  ResponseEntity<List<Message>> getMessages(){
        try{
            List<Message> messages= messageService.retrieveMessages();                                          
            return ResponseEntity.ok(messages);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("accounts/{accountId}/messages")
    public  ResponseEntity<List<Message>> getUserMessages(@PathVariable Integer accountId){
        try{
            List<Message> messages= messageService.retrieveUserMessages(accountId);                                          
            return ResponseEntity.ok(messages);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("messages/{messageId}")
    public  ResponseEntity<Message> getMessagesById(@PathVariable Integer messageId){
        try{
            Message message= messageService.retrieveMessageById(messageId);                                          
            return ResponseEntity.ok(message);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}