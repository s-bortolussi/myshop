package com.example.myshop;

import java.util.List;

public class OAuth2Client {

    private String clientId;
    private String clientSecret;
    private List<String> authorizedGrantTypes;
    private List<String> scopes;
    private List<String> registeredRedirectUris;

    public OAuth2Client() {
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(List<String> authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }


    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public List<String> getRegisteredRedirectUris() {
        return registeredRedirectUris;
    }

    public void setRegisteredRedirectUris(List<String> registeredRedirectUris) {
        this.registeredRedirectUris = registeredRedirectUris;
    }
}
