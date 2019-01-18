package com.drelang.smartlock.service;

import com.drelang.smartlock.domain.RoomInfo;

import java.util.List;

public interface RoomInfoService {
    /**
     *  获取所有可用房间信息
     * @return RoomInfo
     */
    List<RoomInfo> getAll();
}
