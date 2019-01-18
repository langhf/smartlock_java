package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.bo.UmsMemberDetails;
import com.drelang.smartlock.domain.RoomInfo;
import com.drelang.smartlock.domain.RoomOrder;
import com.drelang.smartlock.domain.UmsMember;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.RoomOrderBookParam;
import com.drelang.smartlock.dto.RoomOrderResult;
import com.drelang.smartlock.repository.RoomInfoRepository;
import com.drelang.smartlock.repository.RoomOrderRepository;
import com.drelang.smartlock.repository.UmsMemberRepository;
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
    private UmsMemberRepository umsMemberRepository;

    public RoomOrderServiceImpl(RoomOrderRepository roomOrderRepository, RoomInfoRepository roomInfoRepository, UmsMemberRepository umsMemberRepository) {
        this.roomOrderRepository = roomOrderRepository;
        this.roomInfoRepository = roomInfoRepository;
        this.umsMemberRepository = umsMemberRepository;
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
            roomOrderResults.add(roomOrderResult);
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

    private UmsMember getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UmsMemberDetails umsMemberDetails = (UmsMemberDetails) authentication.getPrincipal();
        UmsMember currentUser = umsMemberDetails.getUmsMember();
        return currentUser;
    }

    /**
     * 根据 RoomOrder 返回 RoomOrderResult
     * @return
     */
    private RoomOrderResult RoomOrderToRoomOrderResult(RoomOrder roomOrder) {
        RoomOrderResult roomOrderResult = new RoomOrderResult();
        BeanUtils.copyProperties(roomOrder, roomOrderResult);
        // 将用户id和管理员id转换为姓名以及添加房间信息
        UmsMember user = umsMemberRepository.getOne(roomOrder.getUserId());
        UmsMember auditor = umsMemberRepository.getOne(roomOrder.getAuditorId());
        RoomInfo roomInfo = roomInfoRepository.getOne(roomOrder.getRoomId());
        roomOrderResult.setUsername(user.getUsername());
        roomOrderResult.setAuditorName(auditor.getUsername());
        roomOrderResult.setRoomSerialNumber(roomInfo.getSerialNumber());
        roomOrderResult.setRoomDescription(roomInfo.getDescription());
        roomOrderResult.setRoomTag(roomInfo.getTag());

        return roomOrderResult;
    }
}
