package com.drelang.smartlock.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "door_info")
public class DoorInfo extends Auditable<String> {

    @Id
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "BLT_MAC")
    private String BLTMAC;

    private Long ownerId;

    private String prePwd;

    private String nextPwd;

    private String description;

    private String tag;

}
