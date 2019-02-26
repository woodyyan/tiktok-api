package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.entity.ServiceContactEntity;
import com.daduo.api.tiktokapi.model.ServiceContact;
import com.daduo.api.tiktokapi.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SettingsService {
    @Autowired
    private SettingsRepository repository;

    public ServiceContact getServiceContact() {
        ServiceContactEntity entity = getDefaultServiceContact();
        ServiceContact contact = new ServiceContact();
        contact.setQq(entity.getQq());
        contact.setWechat(entity.getWechat());
        return contact;
    }

    public ServiceContact updateServiceContact(ServiceContact serviceContact) {
        ServiceContactEntity entity = getDefaultServiceContact();
        if (!StringUtils.isEmpty(serviceContact.getWechat())) {
            entity.setWechat(serviceContact.getWechat());
        }
        if (!StringUtils.isEmpty(serviceContact.getQq())) {
            entity.setQq(serviceContact.getQq());
        }
        ServiceContactEntity serviceContactEntity = repository.saveAndFlush(entity);
        ServiceContact contact = new ServiceContact();
        contact.setQq(serviceContactEntity.getQq());
        contact.setWechat(serviceContactEntity.getWechat());
        return contact;
    }

    private ServiceContactEntity getDefaultServiceContact() {
        List<ServiceContactEntity> entities = repository.findAll();
        if (entities.size() == 0) {
            ServiceContactEntity entity = new ServiceContactEntity();
            entity.setQq("123456789");
            entity.setWechat("wechat");
            entities.add(entity);
        }
        return entities.get(0);
    }
}
