package com.yb.api.center.common.config.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.annotation.Resource;

/**
 * Spring Security 基础配置
 * @author yebing
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Resource
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().maximumSessions(1).expiredUrl("/front/html/login.html");
        http
                .cors().and()
                .authorizeRequests()                         //授权配置
                .antMatchers("/login").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/front/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/teacher/**").hasRole("TEACHER")
                .antMatchers("/admin/languageClassificationController/**").hasAnyRole("STUDENT","TEACHER","ADMIN")
                .anyRequest()     // 所有请求
                .authenticated(); // 所有请求都进行权限

        http
                .formLogin()
                .loginPage("/login")
                /*处理登录post请求接口*/
                .loginProcessingUrl("/login")
                .and()
                .csrf().disable();
        http.httpBasic();                                //必不可少，否则自定义UsernamePasswordAuthenticationFilter无效

        http
                .logout()
                //自定义登出api，无需自己实现
                .logoutUrl("/logout")
                .logoutSuccessUrl("/front/html/login.html")
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .authenticationProvider(this.authenticationProvider());
        http
                .addFilterAt(this.customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // 创建DaoAuthenticationProvider实例
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    /**
     * 注册自定义的UsernamePasswordAuthenticationFilter
     * @return
     * @throws Exception
     */
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        return mappingJackson2HttpMessageConverter;
    }
}
