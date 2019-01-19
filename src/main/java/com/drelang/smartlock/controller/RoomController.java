package com.drelang.smartlock.controller;

import com.drelang.smartlock.domain.RoomInfo;
import com.drelang.smartlock.dto.*;
import com.drelang.smartlock.service.MemberService;
import com.drelang.smartlock.service.RoomInfoService;
import com.drelang.smartlock.service.RoomOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("room")
@Api(tags = "2. RoomController", description = "房间相关")
public class RoomController {
    private RoomInfoService roomInfoService;
    private RoomOrderService roomOrderService;
    private MemberService memberService;

    @Autowired
    public RoomController(RoomInfoService roomInfoService, RoomOrderService roomOrderService, MemberService memberService) {
        this.roomInfoService = roomInfoService;
        this.roomOrderService = roomOrderService;
        this.memberService = memberService;
    }

    @GetMapping("/all")
    @ApiOperation(value = "获取所有可用房间信息", notes = "获取云端所有可用房间的信息")
    public List<RoomInfo> getAllRoomInfo() {
        return roomInfoService.getAll();
    }

    @PostMapping("/order")
    @ApiOperation(value = "预定房间", notes = "根绝请求参数要求来预定房间")
    public Boolean bookRoom(@RequestBody  RoomOrderBookParam roomOrderBookParam) {
        return roomOrderService.bookRoom(roomOrderBookParam);
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取当前登录用户的所有房间订单", notes = "获取当前登录用户的所有房间订单，包括未审核、已审核和过期的")
    public List<RoomOrderResult> getCurrentUserOrders() {
        return roomOrderService.getCurrentUserOrders();
    }

    @GetMapping("/admin")
    @ApiOperation(value = "管理员获取管理范围内的用户订单", notes = "管理员获取自己管理范围内的用户订单信息")
    public List<RoomOrderResult> getOrdersByAuditor() {
        MemberInfoResult memberInfoResult = memberService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return roomOrderService.getAllOrdersByAuditorId(memberInfoResult.getId());
    }

    @PostMapping("/review")
    @ApiOperation(value = "管理员审核用户的申请", notes = "message 参数， 1：同意申请； 0：拒绝申请")
    public CommonResult adminReviewOrder(@RequestBody @Valid RoomOrderReviewParam roomOrderReviewParam) {
        return roomOrderService.adminReview(roomOrderReviewParam.getOrderId(), roomOrderReviewParam.getMessage());
    }

    @PostMapping("/finish")
    @ApiOperation(value = "用户结束房间使用权限", notes = "用户结束特定房间订单使用权限")
    public CommonResult finishOrder(@RequestBody RoomRequestFinishParam roomRequestFinishParam) {
        return roomOrderService.finishOrder(roomRequestFinishParam.getOrderId());
    }
}
