package com.example.springdemo.service;

import com.example.springdemo.entities.Business;
import java.util.List;

public interface BusinessService {
	List<Business> findAllBusiness();

	int addBusiness(Business business);
	void updateBusiness(Integer businessId, Business business);
	Business getBusinessById(Integer businessId);
	boolean deleteBusinessById(Integer businessId);
}
