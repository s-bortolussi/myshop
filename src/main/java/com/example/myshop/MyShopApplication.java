package com.example.myshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class MyShopApplication {

    Logger logger = LoggerFactory.getLogger(MyShopApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MyShopApplication.class, args);
    }

    @PostMapping("/order")
    public void placeOrder() {
        logger.debug("a request to place an order has been received !!!!");
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


    @Bean
    public AuthorizationServerConfigurer AuthorizationServerConfigurer(OAuth2ClientProperties clientProperties) {
        return new AuthorizationServerConfigurer() {
            @Override
            public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {
                authorizationServerSecurityConfigurer
                        .allowFormAuthenticationForClients();
            }

            @Override
            public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
                clientDetailsServiceConfigurer.inMemory()
                        .withClient(clientProperties.getClientId())
                        .secret("{noop}" + clientProperties.getClientSecret())//https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-security-oauth2-authorization-server
                        .scopes("all");
            }

            @Override
            public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {

            }
        };
    }

}