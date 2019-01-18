package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.bo.UmsMemberDetails;
import com.drelang.smartlock.repository.UmsMemberLoginLogRepository;
import com.drelang.smartlock.repository.UmsMemberRepository;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.UmsMemberRegisterParam;
import com.drelang.smartlock.domain.UmsMember;
import com.drelang.smartlock.domain.UmsMemberLoginLog;
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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
    private UmsMemberLoginLogRepository umsMemberLoginLogRepository;

    @Autowired
    public UmsMemberServiceImpl(UmsMemberRepository umsMemberRepository,
                                                            PasswordEncoder passwordEncoder,
                                                            AuthenticationManager authenticationManager,
                                                            UserDetailsService userDetailsService,
                                                            JwtTokenUtil jwtTokenUtil,
                                                            UmsMemberLoginLogRepository umsMemberLoginLogRepository) {

        this.umsMemberRepository = umsMemberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.umsMemberLoginLogRepository = umsMemberLoginLogRepository;
    }

    @Override
    public UmsMember getById(Long id) {
        return umsMemberRepository.findById(id).get();
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
        // 检测用户名是否被注册
        UmsMember umsMemberExample = umsMemberRepository.findByUsername(umsMemberRegisterParam.getUsername());
        if (umsMemberExample != null) {
            LOGGER.warn("用户名已被注册:{}", umsMemberRegisterParam.getUsername());
            return null;
        }

        // 将密码进行加密处理
        String bcryptedPassword = passwordEncoder.encode(umsMemberRegisterParam.getPassword());
        umsMember.setPassword(bcryptedPassword);

        umsMemberRepository.save(umsMember);
        LOGGER.info("注册成功:{}", umsMemberRegisterParam.getUsername());
        return umsMember;
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
            updateLoginTimeByUsername(username);
            insertLoginLog(username);

        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常: {}", e.getMessage());
        }
        return jwtToken;
    }

    /**
     *  根据用户名更新用户最后一次登录时间
     * @param username  用户名
     */
    private void updateLoginTimeByUsername(String username) {
        UmsMember umsMember = umsMemberRepository.findByUsername(username);
        umsMember.setLastLoginTime(new Date());
        umsMemberRepository.save(umsMember);
    }

    private void insertLoginLog(String username) {
        UmsMember umsMember = umsMemberRepository.findByUsername(username);
        UmsMemberLoginLog umsMemberLoginLog = new UmsMemberLoginLog();
        umsMemberLoginLog.setMemberId(umsMember.getId());
        umsMemberLoginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        umsMemberLoginLog.setIp(request.getRemoteAddr());
        umsMemberLoginLogRepository.save(umsMemberLoginLog);
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
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UmsMemberDetails umsMemberDetails = (UmsMemberDetails) authentication.getPrincipal();
        return umsMemberDetails.getUmsMember();
    }

}
