package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.Message;
import com.daduo.api.tiktokapi.model.MessageRequest;
import com.daduo.api.tiktokapi.model.MessageResponse;
import com.daduo.api.tiktokapi.repository.MessageRepository;
import com.daduo.api.tiktokapi.translator.MessageTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageTranslator translator;

    @Autowired
    private MessageRepository repository;

    public MessageResponse createMessage(MessageRequest request) {
        Message message = translator.toMessage(request);
        Message savedMessage = repository.save(message);
        return translator.toMessageResponse(savedMessage);
    }
}
