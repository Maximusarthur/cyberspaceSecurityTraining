package com.example.springdemo.service.impl;

import com.example.springdemo.mapper.BusinessMapper;
import com.example.springdemo.entities.Business;
import com.example.springdemo.service.BusinessService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Resource
	BusinessMapper businessMapper;

	@Autowired
	public BusinessServiceImpl(BusinessMapper businessMapper) {
		this.businessMapper = businessMapper;
	}

	@Override
	public List<Business> findAllBusiness() {
		return businessMapper.findAllBusiness();
	}

	@Override
	public int addBusiness(Business business) {
		return businessMapper.addBusiness(business);
	}

	@Override
	public void updateBusiness(Integer businessId, Business business) {
		businessMapper.updateBusiness(businessId, business);
	}

	@Override
	public Business getBusinessById(Integer businessId) {
		return businessMapper.getBusinessById(businessId);
	}

	@Override
	public boolean deleteBusinessById(Integer businessId) {
        return businessMapper.deleteBusinessById(businessId);
	}
}
