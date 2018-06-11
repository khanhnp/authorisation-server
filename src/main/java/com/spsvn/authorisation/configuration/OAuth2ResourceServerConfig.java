package com.spsvn.authorisation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by npkhanh on 6/11/2018.
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "auth_serv";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private SecuritySettings securitySettings;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID)
                .tokenStore(tokenStore);
        // can set a new token extractor
        // or set a new authentication manager to verify the token
    }

    /**
     * security configurer.
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/","/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .and().httpBasic().and()
                .csrf().disable();
    }

}
