package com.daduo.api.tiktokapi.service;

import com.daduo.api.tiktokapi.enums.OSPlatform;
import com.daduo.api.tiktokapi.model.VersionInfo;
import org.springframework.stereotype.Service;

@Service
public class VersionService {
    public VersionInfo getNewVersion(OSPlatform platform, String currentVersion) {
        //TODO：获取更新URL
        VersionInfo info = new VersionInfo();
        info.setDescription("修复了一些bug");
        info.setNeedUpdate(false);
        info.setUpdateUrl("https://www.baidu.com");
        return info;
    }
}
