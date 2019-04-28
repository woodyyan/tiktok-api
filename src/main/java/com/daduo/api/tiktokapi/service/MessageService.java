package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Message;
import com.daduo.api.tiktokapi.model.MessageData;
import com.daduo.api.tiktokapi.model.MessageRequest;
import com.daduo.api.tiktokapi.model.Messages;
import com.daduo.api.tiktokapi.repository.MessageRepository;
import com.daduo.api.tiktokapi.translator.MessageTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageTranslator translator;

    @Autowired
    private MessageRepository repository;

    public MessageData createMessage(MessageRequest request) {
        Message message = translator.toMessage(request);
        Message savedMessage = repository.save(message);
        return translator.toMessageData(savedMessage);
    }

    public Messages searchMessage(Long userId, Pageable page) {
        Page<Message> messagePage = repository.findAllByUserId(userId, page);
        return translator.toMessages(messagePage);
    }


    public void logMessage(Long userId, String message) {
        MessageRequest request = new MessageRequest();
        request.setContent(message);
        request.setUserId(userId);
        createMessage(request);
    }
}
