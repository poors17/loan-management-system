package com.loan.demo.controller;

import com.loan.demo.model.ContactMessage;
import com.loan.demo.service.ContactMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class ContactMessageController {

    @Autowired
    private ContactMessageService contactService;

    @GetMapping
    public List<ContactMessage> getAllMessages() {
        return contactService.getAllMessages();
    }

    @PostMapping
    public Map<String, Object> createMessage(@RequestBody ContactMessage message) {
        ContactMessage savedMessage = contactService.saveMessage(message);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", savedMessage);
        return response;
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateMessage(@PathVariable Long id, @RequestBody ContactMessage message) {
        Optional<ContactMessage> updated = contactService.updateMessage(id, message);
        Map<String, Object> response = new HashMap<>();
        if (updated.isPresent()) {
            response.put("success", true);
            response.put("message", updated.get());
        } else {
            response.put("success", false);
            response.put("error", "Message not found");
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteMessage(@PathVariable Long id) {
        boolean deleted = contactService.deleteMessage(id);
        Map<String, Object> response = new HashMap<>();
        if (deleted) {
            response.put("success", true);
            response.put("message", "Message deleted");
        } else {
            response.put("success", false);
            response.put("error", "Message not found");
        }
        return response;
    }
}
