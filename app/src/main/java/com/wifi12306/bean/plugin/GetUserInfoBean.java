package com.wifi12306.bean.plugin;

/**
 * Created by issuser on 16/5/16.
 */
public class GetUserInfoBean {
    public String userId;// 用户ID
    public String headImgUrl;// 用户头像小图地址
    public String largeHeadImgUrl;// 用户头像大图地址
    public String name;//姓名
    public String sex;//性别
    public String mobilePhone;// 手机号码
    public String telphone;// 座机号码
    public String email;// 电子邮件
    public String sign;// 个性签名
    public String employeeNum;// 员工号
    public String company;// 员工所在公司名
    public String department;// 员工所在部门
    public String post;// 员工职务

//    @Override
//    public String toString() {
//        return "{" +
//                "userId:'" + userId + '\'' +
//                ", headImgUrl:'" + headImgUrl + '\'' +
//                ", largeHeadImgUrl:'" + largeHeadImgUrl + '\'' +
//                ", name:'" + name + '\'' +
//                ", sex:'" + sex + '\'' +
//                ", mobilePhone:'" + mobilePhone + '\'' +
//                ", telphone:'" + telphone + '\'' +
//                ", email:'" + email + '\'' +
//                ", sign:'" + sign + '\'' +
//                ", employeeNum:'" + employeeNum + '\'' +
//                ", company:'" + company + '\'' +
//                ", department:'" + department + '\'' +
//                ", post:'" + post + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", largeHeadImgUrl='" + largeHeadImgUrl + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", telphone='" + telphone + '\'' +
                ", email='" + email + '\'' +
                ", sign='" + sign + '\'' +
                ", employeeNum='" + employeeNum + '\'' +
                ", company='" + company + '\'' +
                ", department='" + department + '\'' +
                ", post='" + post + '\'' +
                '}';
    }
}
