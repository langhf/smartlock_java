package com.drelang.smartlock.service;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.UmsMemberRegisterParam;
import com.drelang.smartlock.pojo.entity.UmsMember;

public interface UmsMemberService {

    /**
     *  根据会员编号获取会员
     * @param id 编号
     * @return UmsMember
     */
    UmsMember getById(Long id);

    /**
     *  根据用户名获取会员
     * @param username 用户名
     * @return UmsMember
     */
    UmsMember getByUsername(String username);


    /**
     *  用户注册
     * @param umsMemberRegisterParam  DTO 层对象
     * @return POJO 对象
     */
    UmsMember register(UmsMemberRegisterParam umsMemberRegisterParam);


    /**
     *  用户登录
     * @param username  用户名
     * @param password 密码
     * @return jwtToken
     */
    String login(String username, String password);
    /**
     *  生成验证码
     * @param telephone 手机号
     * @return CommonResult
     */
    CommonResult generateAuthCode(String telephone);

    /**
     *  修改密码
     * @param telephone 手机号
     * @param password 新密码
     * @param authCode 验证码
     * @return CommonResult
     */
    CommonResult updatePassword(String telephone, String password, String authCode);

    /**
     *  获取当前登录会员
     * @return UmsMember
     */
    UmsMember getCurrentMember();
}
