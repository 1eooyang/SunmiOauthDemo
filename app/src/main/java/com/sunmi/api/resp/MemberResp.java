package com.sunmi.api.resp;

/**
 * 作者：杨柳 on 2019/5/24 0024 16:13
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class MemberResp {
        private String role;
        private String status;
        private String username;
        private String mobile;
        private String email;
        private String last_login_time;
        private String icon;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(String last_login_time) {
        this.last_login_time = last_login_time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    public String toString() {
        return "成员  {" +
                "role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", last_login_time='" + last_login_time + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
