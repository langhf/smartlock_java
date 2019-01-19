package com.drelang.smartlock.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "member_login_log")
public class MemberLoginLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @CreatedDate
    private Date createTime;

    private String ip;
    private String address;
    private String userAgent;
}
