package com.drelang.smartlock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "door_open_log")
public class DoorOpenLog extends Auditable<String> {

    @Id
    private Long id;

    private Long userId;

    private Long doorId;

}
