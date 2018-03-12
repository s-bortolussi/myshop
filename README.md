# My Shop

A sample app with OAuth 2.0 protected resources.

# Architecture details

Basically, MyShop :

- hosts an OAuth 2.0 Authorization Server and thus can provide OAuth 2.0 access tokens. 
- exposes an OAuth 2.0 protected resource : ```/order```

See [Spring boot OAuth 2.0](https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/htmlsingle/) for details.

# Running My Shop
```bash

#Set OAuth 2.0 Authorization Server basic authentication
$ export SPRING_SECURITY_USER_NAME: <?>
$ export SPRING_SECURITY_USER_PASSWORD: <?>

#Register OAuth 2.0 client in the Authorization Server
$ export SECURITY_OAUTH2_CLIENT_CLIENT_ID: <?>
$ export SECURITY_OAUTH2_CLIENT_CLIENT_SECRET: <?>
$ export SECURITY_OAUTH2_CLIENT_SCOPE: all

$ ../mvnw package
$ java -jar target/*.jar

#configuration can be validated using
$curl http://<client-id>:<client-secret>@localhost:8080/oauth/token -d grant_type=password -d username=<basic-auth-user-name> -d password=<basic-auth-user-password>

```




 
