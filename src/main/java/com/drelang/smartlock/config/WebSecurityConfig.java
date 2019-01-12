package com.drelang.smartlock.config;

import com.drelang.smartlock.bo.UmsMemberDetails;
import com.drelang.smartlock.component.JwtAuthenticationTokenFilter;
import com.drelang.smartlock.pojo.entity.UmsMember;
import com.drelang.smartlock.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsMemberService umsMemberService;

//    @Autowired
//    public WebSecurityConfig(UmsMemberService umsMemberService) {
//        this.umsMemberService = umsMemberService;
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                    .disable() // 由于使用的是 JWT, 因此不需要 csrf
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // 基于 token, 所以不需要 session
                    .and()
                .authorizeRequests()
                        .antMatchers("/", "/home").permitAll()
                        .antMatchers("/sso/register", "/sso/login").permitAll() // 登录注册要允许匿名访问
                        .antMatchers(HttpMethod.OPTIONS).permitAll()  // 跨域请求会先进行一次 options 请求
                        .anyRequest().authenticated() //除上面的请求外，全部都需要认证
                        ;
        // 禁用缓存
        http.headers().cacheControl();
        // 添加 JWT Filter, 在 UsernamePasswordAuthenticationFilter 之前添加
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // TODO: 添加自定义未登录和未授权返回结果

    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder ) throws Exception {
        builder.userDetailsService(userDetailsService())
                    .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            UmsMember umsMember = umsMemberService.getByUsername(username);
            if (umsMember != null) {
                // TODO: 增加用户权限关系表
                return new  UmsMemberDetails(umsMember);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager(){
        return new AuthenticationManager() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return null;
            }
        };
    }
}
