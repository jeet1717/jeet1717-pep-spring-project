package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    
    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account findAccountByUsername(@Param ("username") String username);  
    Account findByUsernameAndPassword(String username, String password);
}
