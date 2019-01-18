package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.domain.*;
import com.drelang.smartlock.domain.doorStrategy.DoorContext;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.DoorRequestHelpParam;
import com.drelang.smartlock.repository.*;
import com.drelang.smartlock.service.DoorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoorServiceImpl implements DoorService {
    private DoorInfoRepository doorInfoRepository;
    private DoorOpenLogRepository doorOpenLogRepository;
    private DoorHelpOrderRepository doorHelpOrderRepository;
    private RoomOrderRepository roomOrderRepository;
    private RoomInfoRepository roomInfoRepository;
    private UmsMemberRepository umsMemberRepository;

    public DoorServiceImpl(DoorInfoRepository doorInfoRepository,
                                                DoorOpenLogRepository doorOpenLogRepository,
                                                DoorHelpOrderRepository doorHelpOrderRepository,
                                                RoomOrderRepository roomOrderRepository,
                                                RoomInfoRepository roomInfoRepository,
                                                UmsMemberRepository umsMemberRepository) {
        this.doorInfoRepository = doorInfoRepository;
        this.doorOpenLogRepository = doorOpenLogRepository;
        this.doorHelpOrderRepository = doorHelpOrderRepository;
        this.roomOrderRepository = roomOrderRepository;
        this.roomInfoRepository = roomInfoRepository;
        this.umsMemberRepository = umsMemberRepository;
    }

    @Override
    public CommonResult openDoor(String serialNumber) {
        // 根据门序列号和类型来获取房间序列号
        DoorInfo doorInfo = doorInfoRepository.getBySerialNumber(serialNumber);
        DoorContext doorContext = new DoorContext(doorInfo.getTag(), doorInfo.getSerialNumber());
        String roomSerialNumber = doorContext.getRoomSerialNumber();
        // 根据房间序列号来获取房间信息
        RoomInfo roominfo = roomInfoRepository.findBySerialNumber(roomSerialNumber);
        // 获取当前登录用户的信息
        UmsMember currentUser = umsMemberRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        // 根据用户id，房间id和订单状态来获取订单。
        RoomOrder roomOrder = roomOrderRepository.findFirstByUserIdAndRoomIdAndStatusOrderByIdDesc(currentUser.getId(), roominfo.getId(), RoomOrder.STATUS_ACCEPTED);

        // 判断是否有开启房间的权限
        if( roomOrder == null) {
            return new CommonResult().failed("您没有开门权限！");
        }
        // 判断开门时间段是否合法
        Date timeNow = new Date();
        if(  timeNow.before(roomOrder.getEndTime()) && timeNow.after(roomOrder.getStartTime())) {
            Map<String, String> password = new HashMap<>();
            password.put("currentPwd", doorInfo.getPrePwd());
            password.put("nextPwd", doorInfo.getNextPwd());
            return new CommonResult().success(password);
        } else {
            return new CommonResult().failed("开门时间已过期，请重新申请房间使用权限！");
        }
    }

    @Override
    public CommonResult resetDoorPassword(String serialNumber) {
        return null;
    }

    @Override
    public CommonResult requestHelp(DoorRequestHelpParam doorRequestHelpParam) {
        return null;
    }

    @Override
    public CommonResult helpHandle(Long helpOrderId, Integer message) {
        return null;
    }

    @Override
    public List<DoorHelpOrder> getCurrentUserHelpOrders() {
        return null;
    }

    @Override
    public List<DoorHelpOrder> getHelpOrdersByAuditorId() {
        return null;
    }
}
