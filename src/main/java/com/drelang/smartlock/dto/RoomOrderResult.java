package com.drelang.smartlock.dto;

import lombok.Data;

import java.util.Date;

/**
  *  返回房间订单信息，不只是 room_order 表的字段
  * Created by Drelang on 2019/01/17
  */
@Data
public class RoomOrderResult {

    private Long id;

    /**
     * 房间编号
     */
    private String roomSerialNumber;

    /**
     * 房间描述
     */
    private String roomDescription;

    /**
     * 房间类型
     */
    private String roomTag;

    /**
     * 用户名
     */
    private String username;

    /**
     * 订单主题
     */
    private String theme;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 审核员姓名
     */
    private String auditorName;

    /**
     * 订单有效开始时间
     */
    private Date startTime;

    /**
     * 订单有效结束时间
     */
    private Date endTime;

    /**
     * 订单创建日期
     */
    private Date createdAt;

    /**
     * 订单最后更新时间
     */
    private Date updatedAt;

}
