package com.drelang.smartlock.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "room_order")
public class RoomOrder extends Auditable<String> {

    public static final String TAG_MEETING = "meeting"; // 会议室
    public static final String TAG_LAB = "lab"; // 实验室
    public static final String TAG_FLOOR = "floor"; // 楼层

    public static final Integer STATUS_PENDING = 0; // 待审核
    public static final Integer STATUS_ACCEPTED = 1; // 审核通过
    public static final Integer STATUS_DECLINED = 2; // 审核未过
    public static final Integer STATUS_EXPIRED = 3; // 已过期

    @Id
    private long id;
    private Long roomId;
    private Long userId;
    private String theme;
    private int status;
    private Long auditorId;
    private Date startTime;
    private Date endTime;
//
//    @CreatedDate
//    private Date createdAt;
//
//    @LastModifiedDate
//    private Date updatedAt;

}
