package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.exception.Conflict;
import com.example.exception.InvalidAccountException;
import com.example.exception.LoginNotSuccessfulException;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accRepo;
	
	@Autowired
	public AccountService(AccountRepository accRepo) {
		this.accRepo = accRepo;
	}

    public Account addAccount(Account account) throws InvalidAccountException {
        Account user = accRepo.findAccountByUsername(account.getUsername());
        if(user != null) {
            throw new Conflict("User Already Exists!!!");
        }
        
        if (account.getUsername().isEmpty() || account.getPassword().length() <= 4) {
            throw new InvalidAccountException("Account not registered!!!");
        }
        
        return accRepo.save(account);
    }

    public Account verifyLogin(Account account) throws LoginNotSuccessfulException {
        Account authenticatedAccount = accRepo.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (authenticatedAccount == null) {
            throw new LoginNotSuccessfulException("Unable to login, check Username and Password!!!");
        }
        // if (account.getUsername() == null || account.getUsername() == null) {
        //     throw new LoginNotSuccessfulException("Invalid Username and Password!!!");
        // }
        
        return authenticatedAccount;
    }
    
}
