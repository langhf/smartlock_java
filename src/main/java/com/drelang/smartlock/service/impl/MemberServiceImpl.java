package com.drelang.smartlock.service.impl;

import com.drelang.smartlock.bo.MemberDetails;
import com.drelang.smartlock.domain.Member;
import com.drelang.smartlock.domain.MemberLoginLog;
import com.drelang.smartlock.dto.CommonResult;
import com.drelang.smartlock.dto.MemberInfoResult;
import com.drelang.smartlock.dto.MemberRegisterParam;
import com.drelang.smartlock.repository.MemberLoginLogRepository;
import com.drelang.smartlock.repository.MemberRepository;
import com.drelang.smartlock.service.MemberService;
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
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
    private MemberRepository memberRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private MemberLoginLogRepository memberLoginLogRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository,
                             PasswordEncoder passwordEncoder,
                             AuthenticationManager authenticationManager,
                             UserDetailsService userDetailsService,
                             JwtTokenUtil jwtTokenUtil,
                             MemberLoginLogRepository memberLoginLogRepository) {

        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.memberLoginLogRepository = memberLoginLogRepository;
    }

    @Override
    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public MemberInfoResult getById(Long id) {
        Member member = memberRepository.findById(id).get();
        return  member2MemberInfoResult(member);
    }

    @Override
    public MemberInfoResult getByUsername(String username) {
        Member member =  memberRepository.findByUsername(username);
        return member2MemberInfoResult(member);
    }

    @Override
    public MemberInfoResult register(MemberRegisterParam memberRegisterParam) {
        //TODO: 增加验证码验证
        Member member = new Member();
        BeanUtils.copyProperties(memberRegisterParam, member);
        member.setStatus(1);
        member.setCreateTime(new Date());
        // 检测用户名是否被注册
        Member memberExample = memberRepository.findByUsername(memberRegisterParam.getUsername());
        if (memberExample != null) {
            LOGGER.warn("用户名已被注册:{}", memberRegisterParam.getUsername());
            return null;
        }

        // 将密码进行加密处理
        String bcryptedPassword = passwordEncoder.encode(memberRegisterParam.getPassword());
        member.setPassword(bcryptedPassword);

        memberRepository.save(member);
        LOGGER.info("注册成功:{}", memberRegisterParam.getUsername());
        return member2MemberInfoResult(member);
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
        Member member = memberRepository.findByUsername(username);
        member.setLastLoginTime(new Date());
        memberRepository.save(member);
    }

    private void insertLoginLog(String username) {
        Member member = memberRepository.findByUsername(username);
        MemberLoginLog memberLoginLog = new MemberLoginLog();
        memberLoginLog.setMemberId(member.getId());
        memberLoginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        memberLoginLog.setIp(request.getRemoteAddr());
        memberLoginLogRepository.save(memberLoginLog);
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
    public MemberInfoResult getCurrentMember() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
        return member2MemberInfoResult(memberDetails.getMember());
    }

    private MemberInfoResult member2MemberInfoResult(Member member) {
        MemberInfoResult memberInfoResult = new MemberInfoResult();
        BeanUtils.copyProperties(member, memberInfoResult);
        return memberInfoResult;
    }
}
