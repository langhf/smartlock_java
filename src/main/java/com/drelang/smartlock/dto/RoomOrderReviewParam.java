package com.drelang.smartlock.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
  *  管理员审核用户订单的参数
  * Created by Drelang on 2019/01/17
  */
@Getter
@Setter
public class RoomOrderReviewParam {

    /**
     * 订单 id
     */
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    /**
     * 消息， 0代表拒绝，1代表同意
     */
    @NotNull(message = "消息不能为空")
    private Integer message;
}
