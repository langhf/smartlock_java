package com.drelang.smartlock.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
// TODO: 根据 data schemea 生成 jpa 实体类
public class UmsMember {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty(message = "用户名不能为空")
  private String username;

  @NotEmpty(message = "密码不能为空")
  private String password;

  @NotEmpty(message = "手机号不能为空")
  private String telephone;

  private Integer status;

  @CreatedDate
  private Date createTime;
  private Date lastLoginTime;

}
