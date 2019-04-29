# spring-cloud-center
用户中心，如有其它中心可copy此中心作为模板

此工程仅做API逻辑处理，已引入dao、service、controller测试。
访问：http://localhost:8080/user/selectByPrimaryKey

各中心工程调用在service层使用，
参考类：UserServiceImpl
调用方法：
@HystrixCommand(fallbackMethod = "serviceError")
    public UserDTO selectByPrimaryKey(Long id) {
        String forObject = restTemplate.getForObject("http://"+userCenter+"/user/selectByPrimaryKey", String.class);
        logger.info("user-center返回结果：{}",forObject);
        UserDTO userDTO = JSONObject.parseObject(forObject,UserDTO.class);
        return userDTO;
    }
    
/**
     * 断路器，保持入参、返回参数类型与原方法一致
     * @param name
     * @return
     */
    public UserDTO serviceError(Long name) {
        logger.info("hi,"+name+",sorry,error!");
        return null;
    }


