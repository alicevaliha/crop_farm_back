package com.com_farm_back.hallo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com_farm_back.hallo.model.messages.Message;
import com.com_farm_back.hallo.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void insertMessage(Message message) {
        messageRepository.save(message);
    }
}
