package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Message;
import com.example.exception.MessageCreationException;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    MessageRepository msgRepo;
	
	@Autowired
	public MessageService(MessageRepository msgRepo) {
		this.msgRepo = msgRepo;
	}

    public List<Message> getAllMessages() {
        return msgRepo.findAll();
    }

    public Message addMessage(Message message) {
        
        if(message.getMessage_text().isBlank() || message.getMessage_text().length() >= 255) { 
            throw new MessageCreationException("Message is Blank or more than 255 Character!!!");
        }
        if (!msgRepo.existsById(message.getPosted_by())) {
            throw new MessageCreationException("Message donot refers to a real, existing user!!!");
        }
        
        return msgRepo.save(message);
    }

    public Message getMessageById(int message_id) {

        return msgRepo.findById(message_id).orElse(null);
    }

    public int deleteMessageById(int message_id) {
        Optional<Message> temp = msgRepo.findById(message_id);

        if(temp.isPresent()) {
            msgRepo.deleteById(message_id);
            return 1;
        }
        return 0;
    }

    public int updateMessageById(Message message, int message_id) throws MessageCreationException{
        
        if(message.getMessage_text().length() >= 255 || message.getMessage_text().isBlank()) {
            throw new MessageCreationException("Message is more than 255 character!!!");
        }
        // if(message_text != "") {
        //     throw new MessageCreationException("Message is Blank!!!");
        // }
        Optional<Message> temp = msgRepo.findById(message_id);

        if(temp.isPresent()) {
            Message updatMessage = temp.get();
            updatMessage.setMessage_text(message.getMessage_text());
            msgRepo.save(updatMessage);
            return 1;
        }
        throw new MessageCreationException("Message not updated!!!");
    }

    // public List<Message> getMessagesByAccountId(int account_id) {
    //     // if(msgRepo.existsById(account_id)) {
    //     //     return msgRepo.getMessagesByAccountId(account_id);
    //     // }
    //     return null;        
    // }
    public List<Message> getMessagesByAccountId(int account_id){
        return msgRepo.getMessagesByAccountId(account_id);
    }
}