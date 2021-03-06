package com.yb.sso.center.config;

import com.yb.sso.center.filter.WebSsoFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring web filter过滤器配置
 * @author yebing
 */
@Configuration
public class FilterRegistrationConfig {

    @Bean
    public WebSsoFilter webSsoFilter() {
        return new WebSsoFilter();
    }

    @Bean
    public FilterRegistrationBean filterProxy() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy httpBasicFilter = new DelegatingFilterProxy();
        registrationBean.setFilter(httpBasicFilter);
        Map<String, String> m = new HashMap<String, String>();
        m.put("targetBeanName", "webSsoFilter");
        m.put("targetFilterLifecycle", "true");
        registrationBean.setInitParameters(m);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}