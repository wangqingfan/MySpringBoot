package com.wqf.learn.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wqf.learn.domain.AdminInfo;

@Mapper
public interface TestDao {

	@Select(value = { "SELECT * FROM AdminInfo WHERE adminId <10" })
	List<Map<String,Object>> findAllAdmins(); 
	
	@Select(value = {"SELECT * FROM AdminInfo WHERE adminId = #{id} and adminCode = #{adminCode}"})
	AdminInfo findAdminBy(@Param("id")Integer id,@Param("adminCode") String adminCode);
	
	AdminInfo findById(Integer id);
}
