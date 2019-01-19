package com.drelang.smartlock.service;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.RoomOrderBookParam;
import com.drelang.smartlock.dto.RoomOrderResult;

import java.util.List;

/**
  *  管理房间订单服务
  * Created by Drelang on 2019/01/18
  */
public interface RoomOrderService {

    /**
     * 预定可用房间
     * @param roomOrderBookParam 预定参数
     * @return true | false
     */
    Boolean bookRoom(RoomOrderBookParam roomOrderBookParam);

    /**
     * 管理员审核用户的申请
     * @param orderId  订单编号
     * @param message 1：同意； 0：拒绝
     * @return CommonResult
     */
    CommonResult adminReview(Long orderId, Integer message);

    /**
     * 获取当前登录用户的所有订单信息
     * @return List<RoomOrderResult>
     */
    List<RoomOrderResult> getCurrentUserOrders();

    /**
     * 根据审核员的id来获取该审核员管辖范围内的所有用户申请
     * @param auditorId 审核员id
     * @return List<RoomOrderResult>
     */
    List<RoomOrderResult> getAllOrdersByAuditorId(Long auditorId);

    /**
     * 结束已申请到的订单
     * @param orderId 订单编号
     * @return true | false
     */
    CommonResult finishOrder(Long orderId);
}
