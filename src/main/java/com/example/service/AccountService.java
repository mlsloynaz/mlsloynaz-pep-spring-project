package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.AccountNotFoundException;
import com.example.exception.AlreadyExistsException;
import com.example.exception.ClientErrorException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) throws AlreadyExistsException, IllegalArgumentException {
        boolean accountExists = accountRepository.existsByUsername(account.getUsername());

        if (accountExists) {
            throw new AlreadyExistsException();
        }
        if (!validateAccountData(account)) {
            throw new ClientErrorException();
        }

        return accountRepository.save(account);
    }

    public Account loginAccount(Account account){
        Account loggedAccount = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(loggedAccount == null){
            throw new AccountNotFoundException(); 
        }
        return loggedAccount;
    }

    private boolean validateAccountData(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        if (username.length() == 0 || password.length() < 5) {
            return false;
        }
        return true;
    }


}
