package com.drelang.smartlock.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: 房间信息作为热点数据应该放在 redis 里面
@Data
@Entity
@Table(name = "room_info")
public class RoomInfo extends Auditable<String> {
    @Id
    private long id;
    private String serialNumber;
    private String description;
    private Long owner;
    private String tag;

}
