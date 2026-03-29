package com.loan.demo.service;

import com.loan.demo.model.ContactMessage;
import com.loan.demo.repository.ContactMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactMessageService {

    @Autowired
    private ContactMessageRepository contactRepository;

    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }

    public Optional<ContactMessage> getMessageById(Long id) {
        return contactRepository.findById(id);
    }

    public ContactMessage saveMessage(ContactMessage message) {
        return contactRepository.save(message);
    }

    public Optional<ContactMessage> updateMessage(Long id, ContactMessage newMessage) {
        return contactRepository.findById(id).map(msg -> {
            msg.setName(newMessage.getName());
            msg.setEmail(newMessage.getEmail());
            msg.setMessage(newMessage.getMessage());
            return contactRepository.save(msg);
        });
    }

    public boolean deleteMessage(Long id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
