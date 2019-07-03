package com.yb.sso.center;

/**
 * 登录对象domain
 * @author yebing
 */
public class LoginDomain {
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DemoDomain{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
