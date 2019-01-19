package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
  *  用户结束房间使用权限
  * Created by Drelang on 2019/01/19
  */
@Getter
@Setter
public class RoomRequestFinishParam {

    @ApiModelProperty(value = "订单编号", example = "9", required = true, dataType = "number")
    private Long orderId;
}
