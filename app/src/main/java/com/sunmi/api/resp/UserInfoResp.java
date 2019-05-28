package com.sunmi.api.resp;

/**
 * 作者：杨柳 on 2019/5/24 0024 15:05
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class UserInfoResp {

    private String username;
    private String mobile;
    private String email;
    private String openid;
    private String icon;
    private String unionid;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }


    @Override
    public String toString() {
        return "UserInfoResp{" +
                "username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", openid='" + openid + '\'' +
                ", icon='" + icon + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
