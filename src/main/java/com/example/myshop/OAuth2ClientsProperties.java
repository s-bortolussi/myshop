package com.example.myshop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties
public class OAuth2ClientsProperties {

    private List<OAuth2Client> oauth2Clients;

    public OAuth2ClientsProperties() {
    }

    public List<OAuth2Client> getOauth2Clients() {
        return oauth2Clients;
    }

    public void setOauth2Clients(List<OAuth2Client> oauth2Clients) {
        this.oauth2Clients = oauth2Clients;
    }
}
