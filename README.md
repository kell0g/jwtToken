# jwtToken

config
---

1. h2 memory DB 사용, 다른 DB 사용 시에 application.yml 수정

sign up
----

1. http://localhost:8080/swagger-ui.html#/SignUp/signUpUsingPOST
2. username, email(optional), password 전달

sign in
---

1. http://localhost:8080/swagger-ui.html#/SignIn/signInUsingPOST
2. response에서 message에 jwt 값을 응답받음

test(test, whoami, refresh)
---

1. http://localhost:8080/swagger-ui.html#/Test/refreshUsingGET
2. Authorization header (Bearer jwt) 응답받은 jwt를 입력하여 전송

