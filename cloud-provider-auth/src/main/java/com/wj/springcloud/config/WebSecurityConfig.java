package com.wj.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ：actor
 * @date ：Created in 2021/7/26 17:12
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * create by: actor
     * description: 密码 编码器
     * create time:
     *
      * @Param: null
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    /**
     * create by: actor
     * description:授权规则配置
     * create time:
     *
      * @Param: null
     * @return
     */
    @Override
     protected void configure(HttpSecurity http) throws Exception {
            //屏蔽跨域防护
        http.csrf().disable()
                //对请求做授权处理
                .authorizeRequests()
                //登录路径放行
                .antMatchers("/login").permitAll()
                //其他路径都要拦截
                .anyRequest().authenticated()
                //允许表单登录， 设置登陆页
                .and().formLogin()
                //登出
                .and().logout().permitAll();
    }


    /**
     * create by: actor
     * description:配置认证管理器，授权模式为“poassword”时会用到
     * create time:
     *
      * @Param: null
     * @return
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
