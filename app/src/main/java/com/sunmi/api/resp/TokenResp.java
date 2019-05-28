package com.sunmi.api.resp;

/**
 * 作者：杨柳 on 2019/5/24 0024 10:52
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class TokenResp {

    /**
     * access_token : 640d91f89f4d1433ae98129cfb21f75c
     * expires_in : 7200
     * refresh_token : 3c0afedb1bbed1ad5fe9cc53bba3d2bc
     * openid : 7c4f8fbd352142ac833a260f572f904d
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }


    @Override
    public String toString() {
        return "  access_token : " + access_token + "\n" +
                "  expires_in : " + expires_in + "\n" +
                "  refresh_token :  " + refresh_token + "\n" +
                "  openid :  " + openid;
    }

}
