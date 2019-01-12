package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.UmsMemberRegisterParam;
import com.drelang.smartlock.pojo.entity.UmsMember;
import com.drelang.smartlock.repository.UmsMemberRepository;
import com.drelang.smartlock.service.UmsMemberService;
import com.drelang.smartlock.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 会员管理 Service 实现类
 *  Created by Drelang on 2019/1/9
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMemberServiceImpl.class);
    private UmsMemberRepository umsMemberRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UmsMemberServiceImpl(UmsMemberRepository umsMemberRepository,
                                                            PasswordEncoder passwordEncoder,
                                                            AuthenticationManager authenticationManager,
                                                            UserDetailsService userDetailsService,
                                                            JwtTokenUtil jwtTokenUtil) {

        this.umsMemberRepository = umsMemberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
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
    public UmsMember register(UmsMemberRegisterParam umsMemberRegisterParam) {
        //TODO: 增加验证码验证
        UmsMember umsMember = new UmsMember();
        BeanUtils.copyProperties(umsMemberRegisterParam, umsMember);
        umsMember.setStatus(1);
        umsMember.setCreateTime(new Date());
        //TODO: 增加用户唯一性检测

        // 将密码进行加密处理
        String bcryptedPassword = passwordEncoder.encode(umsMemberRegisterParam.getPassword());
        umsMember.setPassword(bcryptedPassword);

        umsMemberRepository.save(umsMember);
        return umsMember;
//        return new CommonResult().success("注册成功", null);
    }

    @Override
    public String login(String username, String password) {
        String jwtToken = null;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, passwordEncoder.encode(password));
        try{
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            jwtToken = jwtTokenUtil.generateToken(userDetails);
            //TODO: 更新用户登录时间，插入一条用户登录记录

        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常: {}", e.getMessage());
        }
        return jwtToken;
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
