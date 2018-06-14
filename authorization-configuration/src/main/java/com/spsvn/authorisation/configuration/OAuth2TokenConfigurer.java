package com.spsvn.authorisation.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * Created by npkhanh on 6/12/2018.
 */
@Configuration
public class OAuth2TokenConfigurer {

    @Autowired
    private SecuritySettings securitySettings;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * Converts the token into readable format and vice versa.
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
                resourceLoader.getResource(securitySettings.getKeystore().getLocation()),
                securitySettings.getKeystore().getStorepass().toCharArray())
                .getKeyPair(securitySettings.getKeystore().getAlias(), securitySettings.getKeystore().getKeypass().toCharArray());
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    @Bean
    @Primary
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }
}
