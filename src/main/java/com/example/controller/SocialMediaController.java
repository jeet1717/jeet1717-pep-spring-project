package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.Conflict;
import com.example.exception.InvalidAccountException;
import com.example.exception.LoginNotSuccessfulException;
import com.example.exception.MessageCreationException;
import com.example.service.AccountService;
import com.example.service.MessageService;
import java.util.List;
import java.util.Optional;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RequestMapping
@RestController
 public class SocialMediaController {
    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    @PostMapping(value = "/register")
	public @ResponseBody Account postRegister(@RequestBody Account account) throws InvalidAccountException,Conflict{
        return accountService.addAccount(account);
    }
	
    @PostMapping(value = "/login")
	public @ResponseBody Account postLogin(@RequestBody Account account) throws LoginNotSuccessfulException{
        return accountService.verifyLogin(account);
    }

    @PostMapping(value = "/messages")
	public @ResponseBody Message postMessage(@RequestBody Message message) throws MessageCreationException{
        return messageService.addMessage(message);
    }

    @GetMapping(value = "/messages")
    public List <Message> getMessage() {
        return messageService.getAllMessages();
    }

    @GetMapping(value = "/messages/{message_id}")
	public @ResponseBody Message getMessageById(@PathVariable int message_id) {
        return messageService.getMessageById(message_id);
    }

    @DeleteMapping(value = "/messages/{message_id}")
	public @ResponseBody int deleteMessageById(@PathVariable int message_id) {
        return messageService.deleteMessageById(message_id);
    }

    @PatchMapping(value = "/messages/{message_id}")
	public @ResponseBody int patchMessage(@RequestBody Message message, @PathVariable int message_id) 
                throws MessageCreationException{
        return messageService.updateMessageById(message, message_id);
    }

    @GetMapping(value = "/accounts/{account_id}/messages")
	public @ResponseBody List<Message> getMessageByAccountId(@PathVariable int account_id) {
        return messageService.getMessagesByAccountId(account_id);
    }
    // @GetMapping("/accounts/{account_id}/messages")
    // public ResponseEntity<List<Message>> getMessagesByAccountIdHandler(@PathVariable("account_id") int account_id){
    //     List<Message> messages = messageService.getMessagesByAccountId(account_id);
    //     return ResponseEntity.status(200).body(messages);
    // }
}
