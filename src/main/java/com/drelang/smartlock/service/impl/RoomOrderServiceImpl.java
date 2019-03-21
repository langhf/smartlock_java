package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.bo.MemberDetails;
import com.drelang.smartlock.domain.DoorInfo;
import com.drelang.smartlock.domain.Member;
import com.drelang.smartlock.domain.RoomInfo;
import com.drelang.smartlock.domain.RoomOrder;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.RoomOrderBookParam;
import com.drelang.smartlock.dto.RoomOrderResult;
import com.drelang.smartlock.repository.DoorInfoRepository;
import com.drelang.smartlock.repository.MemberRepository;
import com.drelang.smartlock.repository.RoomInfoRepository;
import com.drelang.smartlock.repository.RoomOrderRepository;
import com.drelang.smartlock.service.RoomOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomOrderServiceImpl implements RoomOrderService {
    private RoomOrderRepository roomOrderRepository;
    private RoomInfoRepository roomInfoRepository;
    private MemberRepository memberRepository;
    private DoorInfoRepository doorInfoRepository;

    public RoomOrderServiceImpl(RoomOrderRepository roomOrderRepository,
                                                            RoomInfoRepository roomInfoRepository,
                                                            MemberRepository memberRepository,
                                                            DoorInfoRepository doorInfoRepository) {
        this.roomOrderRepository = roomOrderRepository;
        this.roomInfoRepository = roomInfoRepository;
        this.memberRepository = memberRepository;
        this.doorInfoRepository = doorInfoRepository;
    }

    @Override
    public Boolean bookRoom(RoomOrderBookParam roomOrderBookParam) {
        // TODO: 会议室，实验室，楼层的预定有不同的规则
        RoomInfo roomInfo = roomInfoRepository.findBySerialNumber(roomOrderBookParam.getSerialNumber());

        RoomOrder roomOrder = new RoomOrder();
        BeanUtils.copyProperties(roomOrderBookParam, roomOrder);
        roomOrder.setRoomId(roomInfo.getId());
        roomOrder.setAuditorId(1L);
        roomOrder.setRoomId(roomInfo.getId());
        roomOrder.setUserId(getCurrentUser().getId());
        roomOrderRepository.save(roomOrder);

        // TODO: 个推通知管理员
        return true;
    }

    @Override
    public CommonResult adminReview(Long orderId, Integer message) {
        RoomOrder roomOrder = roomOrderRepository.getOne(orderId);
        if(message.equals(0)) {
            roomOrder.setStatus(RoomOrder.STATUS_DECLINED);
        } else if(message.equals(1)) {
            roomOrder.setStatus(RoomOrder.STATUS_ACCEPTED);
        } else {
            return new CommonResult().failed("指令错误");
        }
        roomOrderRepository.save(roomOrder);
        // TODO: 通知用户该订单审核完毕
        return new CommonResult().success(null);
    }

    @Override
    public List<RoomOrderResult> getCurrentUserOrders() {
        List<RoomOrder> roomOrders = roomOrderRepository.getAllByUserId(getCurrentUser().getId());
        List<RoomOrderResult> roomOrderResults = new ArrayList<>();
        for(RoomOrder roomOrder : roomOrders) {
            RoomOrderResult roomOrderResult = RoomOrderToRoomOrderResult(roomOrder);
            RoomInfo roomInfo = roomInfoRepository.getOne(roomOrder.getRoomId());
            // TODO: 增加门信息
            List<DoorInfo> doorInfos = doorInfoRepository.getAllBySerialNumberLike(roomInfo.getSerialNumber());
            // 根据房间id对应的序列号去查找房间拥有的门
//            Map<String, String> door = new HashMap<>();
//            door.put("doorSerialNumber", roomInfo.getSerialNumber());
//            door.put("doorDescription", roomInfo.getDescription());
//            door.put("doorMAC", roomInfo.get)
//            roomOrderResults.add(roomOrderResult);
        }
        return roomOrderResults;
    }

    @Override
    public List<RoomOrderResult> getAllOrdersByAuditorId(Long auditorId) {
        List<RoomOrder> roomOrders = roomOrderRepository.getAllByAuditorId(auditorId);
        List<RoomOrderResult> roomOrderResults1 = new ArrayList<>();
        for(RoomOrder roomOrder : roomOrders) {
            RoomOrderResult roomOrderResult = RoomOrderToRoomOrderResult(roomOrder);
            roomOrderResults1.add(roomOrderResult);
        }
        return roomOrderResults1;
    }

    @Override
    public CommonResult finishOrder(Long orderId) {
        RoomOrder roomOrder = roomOrderRepository.getOne(orderId);
        roomOrder.setStatus(RoomOrder.STATUS_EXPIRED);
        roomOrderRepository.save(roomOrder);
        return new CommonResult().success(null);
    }

    private Member getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        MemberDetails umsMemberDetails = (MemberDetails) authentication.getPrincipal();
        Member currentUser = umsMemberDetails.getMember();
        return currentUser;
    }

    /**
     * 根据 RoomOrder 返回 RoomOrderResult
     * @return RoomOrderResult
     */
    private RoomOrderResult RoomOrderToRoomOrderResult(RoomOrder roomOrder) {
        RoomOrderResult roomOrderResult = new RoomOrderResult();
        BeanUtils.copyProperties(roomOrder, roomOrderResult);
        // 将用户id和管理员id转换为姓名以及添加房间信息
        Member user = memberRepository.getOne(roomOrder.getUserId());
        Member auditor = memberRepository.getOne(roomOrder.getAuditorId());
        RoomInfo roomInfo = roomInfoRepository.getOne(roomOrder.getRoomId());
        roomOrderResult.setUsername(user.getUsername());
        roomOrderResult.setAuditorName(auditor.getUsername());
        roomOrderResult.setRoomSerialNumber(roomInfo.getSerialNumber());
        roomOrderResult.setRoomDescription(roomInfo.getDescription());
        roomOrderResult.setRoomTag(roomInfo.getTag());

        return roomOrderResult;
    }
}
