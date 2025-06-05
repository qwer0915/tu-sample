package com.framework.register.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
	//중복회원 조회
	int selectMemberDuplicateCount(Map<String,Object> params);
	
	int insertMember(Map<String,Object> params);
	
	Map<String,Object> selectMemberInfo(Map<String,Object> params);
	
	int updateMember(Map<String,Object> params);
	
	int removeMember(Map<String,Object> params);
}
