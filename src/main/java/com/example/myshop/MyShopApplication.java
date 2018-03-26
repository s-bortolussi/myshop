package com.example.myshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class MyShopApplication {

    Logger logger = LoggerFactory.getLogger(MyShopApplication.class);

    @Autowired
    OAuth2ClientsProperties oAuth2ClientsProperties;

    public static void main(String[] args) {
        SpringApplication.run(MyShopApplication.class, args);
    }

    @GetMapping({"/user", "/me"})
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;
    }

    @PostMapping("/order")
    public void placeOrder() {
        logger.debug("a request to place an order has been received !!!!");
    }

    @Bean
    public AuthorizationServerConfigurer AuthorizationServerConfigurer(OAuth2ClientsProperties clientsProperties) {
        return new AuthorizationServerConfigurer() {
            @Override
            public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
                authorizationServerSecurityConfigurer
                        .allowFormAuthenticationForClients();
            }

            @Override
            public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
                InMemoryClientDetailsServiceBuilder builder = clientDetailsServiceConfigurer.inMemory();
                clientsProperties.getOauth2Clients().forEach(
                        client -> builder.withClient(client.getClientId())
                                .secret("{noop}" + client.getClientSecret())
                                .authorizedGrantTypes(client.getAuthorizedGrantTypes().stream().toArray(String[]::new))
                                .scopes(client.getScopes().stream().toArray(String[]::new))
                                .redirectUris(client.getRegisteredRedirectUris().stream().toArray(String[]::new))
                                .and());
            }

            @Override
            public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
            }
        };
    }

    @Configuration
    @Order(-20)
    protected static class LoginConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            http
                    .formLogin().permitAll()
                    .and()
                    .requestMatchers().antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
                    .and()
                    .authorizeRequests().anyRequest().authenticated();
            // @formatter:on
        }


    }

}