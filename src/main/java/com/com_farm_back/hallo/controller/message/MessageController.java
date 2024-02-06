package com.com_farm_back.hallo.controller.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.com_farm_back.hallo.model.messages.Message;
import com.com_farm_back.hallo.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public void createMessage(@RequestBody Message message) {
        messageService.insertMessage(message);
    }

}
