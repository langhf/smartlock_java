package com.drelang.smartlock.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "door_help_order")
public class DoorHelpOrder extends Auditable<String> {

    @Id
    private Long id;

    private String helper;

    private String reason;

    private String contact;

    private String helpToken;

    private Long auditorId;

    private Integer status;

    private String getuiCid;

}
