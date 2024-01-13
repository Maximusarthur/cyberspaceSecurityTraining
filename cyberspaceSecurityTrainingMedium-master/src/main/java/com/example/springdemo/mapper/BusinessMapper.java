package com.example.springdemo.mapper;
import com.example.springdemo.entities.Business;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BusinessMapper {
	List<Business> findAllBusiness();

	int addBusiness(Business business);

	Business getBusinessById(Integer businessId);

	boolean deleteBusinessById(Integer businessId);

	void updateBusiness(Integer businessId, Business business);
}
