# Auth Service

## Application Name:
`auth-service`

use to run the project, also check your docker auth cred, if any issue while running below command. You have to remove credentials.
```
docker-compose up
```

## JWT Expiration:
The JWT expiration time is set as this in milliseconds (2 min) :
```properties
app.jwtExpirationInMs=120000
```


## Service Health curl

```
curl --location 'localhost:8070/public/health' \
--header 'Cookie: JSESSIONID=AF0CD517102C81C0742E26E4FF02DE7F'

```

## User Sign-Up
To sign up a new user, use the following curl command:


```
curl --location 'localhost:8070/user/sign-up' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=AF0CD517102C81C0742E26E4FF02DE7F' \
--data-raw '{
    "emailId":"koustav.k@cars24.com",
    "password":"hhhh"
}'

```


## User Sign-In
To sign in as an existing user, use the following curl command:
```
curl --location 'http://localhost:8070/user/sign-in' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=AF0CD517102C81C0742E26E4FF02DE7F' \
--data-raw '{
    "emailId":"koustav.k@cars24.com",
    "password":"hhhh"
}'

```

## Get user info
To get user information, pass the generated `JWT_TOKEN` from the sign-in API:
```

curl --location 'http://localhost:8070/user/6785501a8e92e72142ed8448' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <JWT_TOKEN>' \
--header 'Cookie: JSESSIONID=EFE90D198F795E52B3496A8A94E10706'


```

## Token Refresh

To refresh the token, use the token generated `JWT_TOKEN` from the sign-in API:
```

curl --location --request POST 'http://localhost:8070/auth/refresh' \
--header 'Authorization: Bearer <JWT_TOKEN>'

```

## Token Revoke
To revoke a token, use the token generated `JWT_TOKEN` from the sign-in API:

```

curl --location --request POST 'http://localhost:8070/auth/revoke' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <JWT_TOKEN>' \
--header 'Cookie: JSESSIONID=AF0CD517102C81C0742E26E4FF02DE7F'


```
