# spring-cloud-center
用户中心，如有其它中心可copy此中心作为模板

此工程仅做zuul路由处理，已引入dao、service、controller测试。
访问：http://localhost:8080/user/selectByPrimaryKey

#配置类似Nginx一样的路由
zuul.routes.api-a.path=/api-a/**
zuul.routes.api-a.serviceId=api-center
zuul.routes.api-b.path=/api-b/**
zuul.routes.api-b.serviceId=user-center