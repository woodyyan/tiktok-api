package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Message;
import com.daduo.api.tiktokapi.model.MessageData;
import com.daduo.api.tiktokapi.model.MessageRequest;
import com.daduo.api.tiktokapi.model.MessageResponse;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class MessageTranslator {
    public Message toMessage(MessageRequest request) {
        Message message = new Message();
        message.setContent(request.getContent());
        message.setCreatedTime(LocalDateTime.now());
        message.setLastModifiedTime(LocalDateTime.now());
        return message;
    }

    public MessageResponse toMessageResponse(Message message) {
        MessageResponse response = new MessageResponse();
        MessageData data = new MessageData();
        data.setContent(message.getContent());
        data.setId(message.getId());
        data.setCreatedTime(message.getCreatedTime().toDateTime());
        data.setLastModifiedTime(message.getLastModifiedTime().toDateTime());
        response.setData(data);
        return response;
    }
}
