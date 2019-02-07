package com.daduo.api.tiktokapi.translator;

import com.daduo.api.tiktokapi.entity.Message;
import com.daduo.api.tiktokapi.model.MessageData;
import com.daduo.api.tiktokapi.model.MessageRequest;
import com.daduo.api.tiktokapi.model.Messages;
import com.daduo.api.tiktokapi.model.PagingMeta;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageTranslator {
    public Message toMessage(MessageRequest request) {
        Message message = new Message();
        message.setContent(request.getContent());
        message.setCreatedTime(LocalDateTime.now());
        message.setLastModifiedTime(LocalDateTime.now());
        message.setUserId(request.getUserId());
        return message;
    }

    public MessageData toMessageData(Message message) {
        MessageData data = new MessageData();
        data.setContent(message.getContent());
        data.setId(message.getId());
        data.setUserId(message.getUserId());
        data.setCreatedTime(message.getCreatedTime().toDateTime());
        data.setLastModifiedTime(message.getLastModifiedTime().toDateTime());
        return data;
    }

    public Messages toMessages(Page<Message> messagePage) {
        Messages messages = new Messages();
        List<MessageData> data = new ArrayList<>();
        for (Message message : messagePage.getContent()) {
            data.add(toMessageData(message));
        }
        messages.setData(data);
        PagingMeta meta = new PagingMeta();
        meta.setPageNumber(messagePage.getNumber());
        meta.setPageSize(messagePage.getSize());
        meta.setTotalElements(messagePage.getTotalElements());
        meta.setTotalPages(messagePage.getTotalPages());
        messages.setMeta(meta);
        return messages;
    }
}
