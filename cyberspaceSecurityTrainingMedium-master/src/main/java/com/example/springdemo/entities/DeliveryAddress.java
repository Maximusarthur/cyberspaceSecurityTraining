package com.example.springdemo.entities;
import lombok.Data;
@Data
public class DeliveryAddress {
	private Integer daId; // 送货地址ID
	private String contactName; // 联系人姓名
	private Integer contactSex; // 联系人性别
	private String contactTel; // 联系人电话号码
	private String address; // 送货地址
	private String userId; // 与送货地址相关的用户ID
}
