package com.drelang.smartlock.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
  *  返回房间订单信息，不只是 room_order 表的字段
  * Created by Drelang on 2019/01/17
  */
@Data
public class RoomOrderResult {
    @ApiModelProperty(value = "订单编号", example = "1", position = 1)
    private Long id;

    @ApiModelProperty(value = "房间序列号", example = "Q-408", position = 2)
    private String roomSerialNumber;

    @ApiModelProperty(value = "房间描述", example = "前工院408", position = 3)
    private String roomDescription;

    @ApiModelProperty(value = "房间类型", example = "lab", position = 4)
    private String roomTag;

    @ApiModelProperty(value = "用户名", example = "drelang", position = 5)
    private String username;

    @ApiModelProperty(value = "订单主题", example = "需要在该房间学习", position = 6)
    private String theme;

    @ApiModelProperty(value = "订单状态", example = "1", position = 7)
    private Integer status;

    @ApiModelProperty(value = "审核员姓名", example = "drelang", position = 8)
    private String auditorName;

    @ApiModelProperty(value = "订单有效开始时间", example = "2019-1-12 14:22:22", position = 9)
    private Date startTime;

    @ApiModelProperty(value = "订单有效结束时间", example = "2019-2-12 14:22:22", position = 10)
    private Date endTime;

    @ApiModelProperty(value = "订单创建日期", example = "2019-1-12 14:00:00", position = 11)
    private Date createdAt;

    @ApiModelProperty(value = "订单最后更新时间", example = "2019-1-12 14:01:00", position = 12)
    private Date updatedAt;

    // TODO: 增加门对应的MAC地址等
}
