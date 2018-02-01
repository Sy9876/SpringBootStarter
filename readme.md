# curl模拟ajax请求

curl -i  -H "Accept: application/json" -H "X-Requested-With: XMLHttpRequest"

# spring security and angular

https://spring.io/guides/tutorials/spring-security-and-angular-js/

# spring security API doc

https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/apidocs/

# references and guides

https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/reference/htmlsingle

https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/guides/html5/


# schema

``` sql
create table user (
  id varchar(50), 
  name varchar(50), 
  password varchar(50), 
  status char(1),
  primary key (id)
);

insert into user values ('1', 'sy', 'shenyue', '1');


```


``` bat
set OPTS=-v 
set C=curl -i --cookie cookiejar.txt --cookie-jar cookiejar.txt -H "Accept: application/json" -H "X-Requested-With: XMLHttpRequest"

%C% "http://localhost:8080/void.do"

%C% "http://localhost:8080/login.do?name=sy&password=shenyue"

%C% "http://localhost:8080/count.do"

%C%  "http://localhost:8080/user.do?name=sy"


```

