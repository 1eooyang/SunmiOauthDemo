package com.sunmi.oauth;

/**
 * 作者：杨柳 on 2019/5/21 0021 16:46
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class OauthBean {

    private String name;
    private String phone;
    private String email;
    private String icon;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public OauthBean(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
