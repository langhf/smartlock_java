package com.drelang.smartlock.service;

/**
  *  短信服务
  * Created by Drelang on 2019/01/18
  */
public interface SmsService {

    /**
     * 生成注册验证码
     * @param telephone 手机号
     * @return 六位验证码
     */
    String generateRegisterCode(String telephone);

    /**
     * 生成更新密码的验证码
     * @param telephone 手机号
     * @return 六位验证码
     */
    String generateUpdatePasswordCode(String telephone);

    /**
     * 生成门禁 help 申请的验证码
     * @param telephone 手机号
     * @return 六位验证码
     */
    String generateDoorRequestHelpCode(String telephone);
}
