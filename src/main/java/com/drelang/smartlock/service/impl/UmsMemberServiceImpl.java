package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.domain.CommonResult;
import com.drelang.smartlock.pojo.entity.UmsMember;
import com.drelang.smartlock.repository.UmsMemberRepository;
import com.drelang.smartlock.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 会员管理 Service 实现类
 *  Created by Drelang on 2019/1/9
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    private UmsMemberRepository umsMemberRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UmsMemberServiceImpl(UmsMemberRepository umsMemberRepository,
                                                            PasswordEncoder passwordEncoder) {
        this.umsMemberRepository = umsMemberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UmsMember getById(Long id) {
        return null;
    }

    @Override
    public UmsMember getByUsername(String username) {
        return umsMemberRepository.findByUsername(username);
    }

    @Override
    public CommonResult register(String username, String password, String telephone) {
        //TODO: 增加验证码验证
        //TODO: 增加用户唯一性检测
        UmsMember umsMember = new UmsMember();
        umsMember.setUsername(username);
        umsMember.setPassword(passwordEncoder.encode(password));
        umsMember.setTelephone(telephone);
        umsMember.setCreateTime(new Date());
        umsMember.setStatus(1);

        umsMemberRepository.save(umsMember);
        return new CommonResult().success("注册成功", null);
    }

    @Override
    public CommonResult generateAuthCode(String telephone) {
        return null;
    }

    @Override
    public CommonResult updatePassword(String telephone, String password, String authCode) {
        return null;
    }

    @Override
    public UmsMember getCurrentMember() {
        return null;
    }
}
