package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.domain.RoomInfo;
import com.drelang.smartlock.repository.RoomInfoRepository;
import com.drelang.smartlock.service.RoomInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomInfoServiceImpl implements RoomInfoService {
    private RoomInfoRepository roomInfoRepository;

    public RoomInfoServiceImpl(RoomInfoRepository roomInfoRepository) {
        this.roomInfoRepository = roomInfoRepository;
    }

    @Override
    public List<RoomInfo> getAll() {
        return roomInfoRepository.findAll();
    }
}
