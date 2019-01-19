package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "订单号", example = "8", required = true)
    @NotNull(message = "订单号不能为空")
    private Long orderId;

    @ApiModelProperty(value = "消息", example = "1", required = true)
    @NotNull(message = "消息不能为空")
    private Integer message;
}
