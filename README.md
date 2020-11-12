# Spring-Boot-MySQL-Demo

#### This is a sample spring boot project work with Mysql database.

##### To get started :


1) Create MySQL database **demo**

2) Change followings in application.properties file according to your MySQL configurations

    spring.datasource.username=###
    
    spring.datasource.password=###
  
3) To run project _**_**./gradlew bootRun -Penv=local**_**_ (Profiling not 
done yet project will run with default 
application property file)

4) Then go to http://localhost:8080/



##### Work with APIs using Postman

1) Postman API collection link

    https://www.getpostman.com/collections/052e3155f8ef65511db5
    
2) Create simple post man environment as follows

    base_url:http://localhost:8080/
    
    token:xxxx
    
3) Get access token

    Call **Demo auth get token** API using followings
    
    username = admin@super.com
    
    password = admin
    
    you will get access token String as response copy it and paste it in 
    token value filed in environment.
    
    Now you can access all User, Role and Policy APIs 
    



##### Policy Convention 

1)  Create policy request body

    `{
        "name": "Roles GET only",
        "description": "Access GET APIs only",
        "type": "GET",
        "policy": "[{\"resource\":\"role\",\"access\":1}]"
    }`

2) How to create policy JSON

    If you want to access user get APIs your policy JSON will be like this,
    
    `"policy":"[{\"resource\":\"user\",\"access\":1}]"`
    
    resource is **user**, access only get APIs access will be **1** like wise,
    
    GET APIs only access = 1
    
    GET & POST APIs only access = 2
    
    GET, POST & DELETE APIs only access = 3  
    
    GET, POST, DELETE & PUT APIs only access = 4
    
    According to this if you need to access ROLE APIs with full 
    access your policy JSON will be like follows
    
    `"policy":"[{\"resource\":\"role\",\"access\":4}]"`
    

##### Create user work flow

1) Create user using **Demo user create** API
2) Create role using **Demo role create** API
3) Add role to user using **Demo user add role** API
4) Create policy using **Demo policy create** API
5) Add policy to role using **Demo role add policy** API

Note : A role can have multiple policies and user can have multiple roles

