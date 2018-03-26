# MyShop

A sample app with OAuth 2.0 protected resources.

# Architecture details

Basically, MyShop :

- hosts an OAuth 2.0 Authorization Server and thus can provide OAuth 2.0 access tokens. 
- exposes an OAuth 2.0 protected resource : ```/order```

See [Spring boot OAuth 2.0](https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/htmlsingle/) for details.

# Running MyShop
```bash

#Set OAuth 2.0 Authorization Server basic authentication
$ export SPRING_SECURITY_USER_NAME: <?>
$ export SPRING_SECURITY_USER_PASSWORD: <?>

#Register an OAuth 2.0 client in the Authorization Server
$ export OAUTH2_CLIENTS[0]_CLIENT_ID: <?>
$ export OAUTH2_CLIENTS[0]_CLIENT_SECRET: <?>
$ export OAUTH2_CLIENTS[0]_AUTHORIZED-GRANT-TYPES: <?>
$ export OAUTH2_CLIENTS[0]_SCOPES: <?>
$ export OAUTH2_CLIENTS[0]_REGISTERED-REDIRECT-URIS: <?>

#Register another OAuth 2.0 client in the Authorization Server
$ export OAUTH2_CLIENTS[1]_CLIENT_ID: <?>
$ export OAUTH2_CLIENTS[1]_CLIENT_SECRET: <?>
$ export OAUTH2_CLIENTS[1]_AUTHORIZED-GRANT-TYPES: <?>
$ export OAUTH2_CLIENTS[1]_SCOPES: <?>
$ export OAUTH2_CLIENTS[1]_REGISTERED-REDIRECT-URIS: <?>

$ ../mvnw package
$ java -jar target/*.jar

#configuration can be validated using
$curl http://<client-id>:<client-secret>@localhost:8080/oauth/token -d grant_type=password -d username=<basic-auth-user-name> -d password=<basic-auth-user-password>

```




 
