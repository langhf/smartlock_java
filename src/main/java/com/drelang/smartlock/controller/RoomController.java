package com.drelang.smartlock.controller;

import com.drelang.smartlock.domain.RoomInfo;
import com.drelang.smartlock.domain.UmsMember;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.RoomOrderBookParam;
import com.drelang.smartlock.dto.RoomOrderResult;
import com.drelang.smartlock.dto.RoomOrderReviewParam;
import com.drelang.smartlock.service.RoomInfoService;
import com.drelang.smartlock.service.RoomOrderService;
import com.drelang.smartlock.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("room")
public class RoomController {
    private RoomInfoService roomInfoService;
    private RoomOrderService roomOrderService;
    private UmsMemberService umsMemberService;

    @Autowired
    public RoomController(RoomInfoService roomInfoService, RoomOrderService roomOrderService, UmsMemberService umsMemberService) {
        this.roomInfoService = roomInfoService;
        this.roomOrderService = roomOrderService;
        this.umsMemberService = umsMemberService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<RoomInfo> getAllRoomInfo() {
        return roomInfoService.getAll();
    }

    @PostMapping("/order")
    @ResponseBody
    public Boolean bookRoom(@RequestBody  RoomOrderBookParam roomOrderBookParam) {
        return roomOrderService.bookRoom(roomOrderBookParam);
    }

    @GetMapping("/user")
    @ResponseBody
    public List<RoomOrderResult> getCurrentUserOrders() {
        return roomOrderService.getCurrentUserOrders();
    }

    @GetMapping("/admin")
    @ResponseBody
    public List<RoomOrderResult> getOrdersByAuditor() {
        UmsMember umsMember = umsMemberService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return roomOrderService.getAllOrdersByAuditorId(umsMember.getId());
    }

    @PostMapping("/review")
    @ResponseBody
    public CommonResult adminReviewOrder(@RequestBody @Valid RoomOrderReviewParam roomOrderReviewParam) {
        return roomOrderService.adminReview(roomOrderReviewParam.getOrderId(), roomOrderReviewParam.getMessage());
    }

    @PostMapping("/finish")
    @ResponseBody
    // 字段较少的请求，直接用 Map 来接收
    public CommonResult finishOrder(@RequestBody Map<String, Long> requestParams) {
        return roomOrderService.finishOrder((Long) requestParams.get("orderId"));
    }
}
