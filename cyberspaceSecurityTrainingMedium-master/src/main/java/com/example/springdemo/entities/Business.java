package com.example.springdemo.entities;
import lombok.Data;

@Data
public class Business {
    private Integer businessId;         // 业务ID
    private String businessName;        // 业务名称
    private String businessAddress;     // 业务地址
    private String businessExplain;     // 业务说明
    private Integer orderTypeId;        // 订单类型ID
    private Double starPrice;           // 起送价格
    private Double deliveryPrice;       // 配送价格
    private String remarks;             // 备注信息
}
