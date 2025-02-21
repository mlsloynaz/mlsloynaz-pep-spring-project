package com.example.service;

import org.springframework.stereotype.Service;
import java.util.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.ClientErrorException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import java.util.Optional;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;
 
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message){
        Optional<Account> account = accountRepository.findById(message.getPostedBy());

        if (validateMessageData(message) && !account.isPresent()){
            return messageRepository.save(message);
        }
        throw new ClientErrorException();
    }

    public Integer updateMessage(Message message, Integer messageId){
        Optional<Message> found = messageRepository.findById(messageId);
  
        if(found.isPresent() && validateMessageData(message)){
           message.setMessageId(messageId); 
           messageRepository.save(message); 
          return 1;
        }
        return null;
    }


    public Integer deleteMessage(Integer messageId){
       Optional<Message> message = messageRepository.findById(messageId);
       if(message.isPresent()){
          messageRepository.deleteById(messageId); 
         return 1;
       }
       return null;
    }

    public List<Message> retrieveMessages(){
         return messageRepository.findAll();
    }

    public List<Message> retrieveUserMessages(Integer accountId){
        return messageRepository.findByPostedBy(accountId);
    }

    public Message retrieveMessageById(Integer messageId){
        Optional<Message> message =  messageRepository.findById(messageId);
        if(message.isPresent()){
            return message.get();
        }
        return null;
    }

    private boolean validateMessageData(Message message) {
      
        String messageTxt = message.getMessageText();

        if (messageTxt ==  null || messageTxt.length()== 0 || messageTxt.length() > 255 ) {
            return false;
        }
        
        return true;
    }
}
