package com.drelang.smartlock.pojo.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@ApiModel("用户对象")
@Data
@Entity
public class UmsMember implements Serializable {

  @Id
  @GeneratedValue
  private long id;
  private String username;
  private String password;
  private String telephone;
  private Integer status;
  private Date createTime;

}
