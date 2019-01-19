package com.drelang.smartlock.service;

import com.drelang.smartlock.domain.Member;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.MemberInfoResult;
import com.drelang.smartlock.dto.MemberRegisterParam;

public interface MemberService {


    /**
     * 根据用户名获取会员
     * @param username 用户名
     * @return Member
     */
    Member getMemberByUsername(String username);

    /**
     *  根据会员编号获取会员
     * @param id 编号
     * @return MemberInfoResult
     */
    MemberInfoResult getById(Long id);

    /**
     *  根据用户名获取会员
     * @param username 用户名
     * @return MemberInfoResult
     */
    MemberInfoResult getByUsername(String username);

    /**
     *  用户注册
     * @param umsMemberRegisterParam  DTO 层对象
     * @return MemberInfoResult
     */
    MemberInfoResult register(MemberRegisterParam umsMemberRegisterParam);

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
     * @return MemberInfoResult
     */
    MemberInfoResult getCurrentMember();
}
