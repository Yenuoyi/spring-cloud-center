# spring-cloud-center
用户中心，如有其它中心可copy此中心作为模板

此工程仅做用户中心逻辑处理，已引入dao、service、controller测试。
访问：http://localhost:8761/user/selectByPrimaryKey

已实现基础增删查改，实现请按以下方式继承基础功能：
controller层继承BasicController
service继承BasicService
serviceImpl继承BasicServiceImpl
dao继承BasicDao


