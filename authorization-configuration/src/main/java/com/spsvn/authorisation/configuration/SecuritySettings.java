package com.spsvn.authorisation.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by npkhanh on 6/11/2018.
 */
@Component
@ConfigurationProperties(prefix = "security")
public class SecuritySettings {

    /**
     * Key Store
     */
    @NotNull
    private KeyStore keystore;

    public KeyStore getKeystore() {
        return keystore;
    }

    public void setKeystore(KeyStore keystore) {
        this.keystore = keystore;
    }

    /**
     * Flag to indicate if access token refresh is supported when it expires.
     */
    @NotNull
    private boolean refreshAccessToken;

    /**
     * Flag to indicate if refresh tokens are recreated on refresh of access token.
     */
    @NotNull
    private boolean reuseRefreshToken;

    /**
     * Period in seconds for access token expiry.
     */
    @NotNull
    private int accessTokenValiditySeconds;

    /**
     * Scope
     */
    @NotNull
    private String authserverScopenameAllAccess;

    public boolean isRefreshAccessToken() {
        return refreshAccessToken;
    }

    public void setRefreshAccessToken(boolean refreshAccessToken) {
        this.refreshAccessToken = refreshAccessToken;
    }

    public boolean isReuseRefreshToken() {
        return reuseRefreshToken;
    }

    public void setReuseRefreshToken(boolean reuseRefreshToken) {
        this.reuseRefreshToken = reuseRefreshToken;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public String getAuthserverScopenameAllAccess() {
        return authserverScopenameAllAccess;
    }

    public void setAuthserverScopenameAllAccess(String authserverScopenameAllAccess) {
        this.authserverScopenameAllAccess = authserverScopenameAllAccess;
    }

    public static final class KeyStore {
        /**
         * Key password.
         */
        @NotNull
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Minimum 8 character and at least 1 Upper case alphabet and 1 number are required")
        private String keypass;
        /**
         * Key store password.
         */
        @NotNull
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Minimum 8 character and at least 1 Upper case alphabet and 1 number are required")
        private String storepass;
        /**
         * keystore file name.
         */
        @NotNull
        @Pattern(regexp = "^(.*)(\\.jks)$", message = "Must be JKS file")
        private String location;
        /**
         * Key alias.
         */
        @NotNull
        private String alias;

        public String getKeypass() {
            return keypass;
        }

        public void setKeypass(String keypass) {
            this.keypass = keypass;
        }

        public String getStorepass() {
            return storepass;
        }

        public void setStorepass(String storepass) {
            this.storepass = storepass;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String keystoreFileName) {
            this.location = keystoreFileName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String keyAlias) {
            this.alias = keyAlias;
        }
    }
}
