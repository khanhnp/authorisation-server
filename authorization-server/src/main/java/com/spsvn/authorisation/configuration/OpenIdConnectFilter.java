package com.spsvn.authorisation.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

/**
 * Created by npkhanh on 6/14/2018.
 */
public class OpenIdConnectFilter extends OAuth2ClientAuthenticationProcessingFilter {

    protected OpenIdConnectFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        OAuth2AccessToken accessToken;
        try {
            accessToken = this.restTemplate.getAccessToken();
        } catch (UserRedirectRequiredException e) {
            throw new BadCredentialsException("Could not obtain access token", e);
        }
        try {
            String idToken = accessToken.getAdditionalInformation().get("login").toString();
            User user = new User(idToken, "$2a$06$d8F2VsBciQ8DnXS4Yi0F3uCndclmK7Duua4dccYmhGygI0DOgluPa", new HashSet<>());
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        } catch (Exception e) {
            throw new BadCredentialsException("Could not obtain user details from token", e);
        }
    }

    private static class NoopAuthenticationManager implements AuthenticationManager {
        private NoopAuthenticationManager() {
        }

        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            throw new UnsupportedOperationException("No authentication should be done with this AuthenticationManager");
        }
    }
}

