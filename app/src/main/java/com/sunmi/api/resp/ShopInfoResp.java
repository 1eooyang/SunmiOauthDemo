package com.sunmi.api.resp;

/**
 * 作者：杨柳 on 2019/5/24 0024 15:05
 * <p>
 * 邮箱：yangliu@sunmi.com
 */
public class ShopInfoResp {

    private String name;
    private String contact_person;
    private String tel;
    private String address;
    private String type_name;
    private String business_hours;
    private String logo;
    private String status;
    private String business_status;

    private String shopid;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getBusiness_hours() {
        return business_hours;
    }

    public void setBusiness_hours(String business_hours) {
        this.business_hours = business_hours;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBusiness_status() {
        return business_status;
    }

    public void setBusiness_status(String business_status) {
        this.business_status = business_status;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }


    @Override
    public String toString() {
        return "ShopInfoResp{" +
                "name='" + name + '\'' +
                ", contact_person='" + contact_person + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", type_name='" + type_name + '\'' +
                ", business_hours='" + business_hours + '\'' +
                ", logo='" + logo + '\'' +
                ", status='" + status + '\'' +
                ", business_status='" + business_status + '\'' +
                ", shopid='" + shopid + '\'' +
                '}';
    }
}
