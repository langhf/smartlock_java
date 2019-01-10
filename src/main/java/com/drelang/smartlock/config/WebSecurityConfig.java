package com.drelang.smartlock.config;

import com.drelang.smartlock.component.GoAuthenticationFailureHandler;
import com.drelang.smartlock.component.GoLogoutSuccessHandler;
import com.drelang.smartlock.domain.MemberDetails;
import com.drelang.smartlock.pojo.entity.UmsMember;
import com.drelang.smartlock.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        http.authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .antMatchers("/sso/**").permitAll() // 登录注册要允许匿名访问
                    .and()
                .formLogin()
                    .loginPage("/sso/login")
                    .failureHandler(new GoAuthenticationFailureHandler())
                    .permitAll()
                    .and()
                .logout()
                    .logoutUrl("/sso/logout")
                    .logoutSuccessHandler(new GoLogoutSuccessHandler())
                    .and()
                .csrf()
                    .disable(); // 关闭 csrf 功能，不然不能用 POST 方法
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
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UmsMember umsMember = umsMemberService.getByUsername(username);
                if(umsMember != null) {
                    return new MemberDetails(umsMember);
                }
                throw new UsernameNotFoundException("用户名或密码错误");
            }
        };
    }
}
