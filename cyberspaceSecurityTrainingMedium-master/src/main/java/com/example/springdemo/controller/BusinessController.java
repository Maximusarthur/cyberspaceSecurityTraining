package com.example.springdemo.controller;

import com.example.springdemo.entities.Business;
import com.example.springdemo.service.BusinessService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/BusinessController")
public class BusinessController {

	@Resource
	private BusinessService businessService;

	//查看所有商家
	@GetMapping("/findAllBusiness")
	List<Business> TestFindAll() {
		return businessService.findAllBusiness();
	}

	// 增加商家
	@RequestMapping(value = "/addBusiness")
	public int addBusiness(@RequestBody Business business) {
		return businessService.addBusiness(business);
	}

	// 根据ID更新商家信息
	@RequestMapping(value = "/updateBusiness/{businessId}")
	public void UpdateBusinessById(@PathVariable Integer businessId,
								   @RequestBody Business business){
		businessService.updateBusiness(businessId, business);
	}

	// 根据ID删除商家
	@DeleteMapping ("/deleteBusinessById")
	public boolean deleteBusinessById(@RequestParam Integer businessId) {
		return businessService.deleteBusinessById(businessId);
	}

	//根据id查找商家
	@GetMapping("/getBusinessById")
	Business getBusinessById(@RequestParam Integer businessId){
		return businessService.getBusinessById(businessId);
	}
	//http://localhost:9527/BusinessController/getBusinessById?businessId=202
}
