package com.drelang.smartlock.pojo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class UmsMember implements Serializable {

  @Id
  @GeneratedValue
  private long id;

  @NotEmpty(message = "用户名不能为空")
  private String username;

  @NotEmpty(message = "密码不能为空")
  private String password;

  @NotEmpty(message = "手机号不能为空")
  private String telephone;

  private Integer status;
  private Date createTime;

}
