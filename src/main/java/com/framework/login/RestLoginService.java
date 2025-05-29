package com.framework.login;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.login.mapper.RestLoginMapper;
import com.framework.util.EncryptUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RestLoginService {
	RestLoginMapper restLoginMapper;
	EncryptUtil encryptUtil;
	public RestLoginService(RestLoginMapper restLoginMapper,EncryptUtil encryptUtil) {
		this.restLoginMapper =restLoginMapper;
		this.encryptUtil=encryptUtil;
	}
	@Transactional(readOnly = true)
	public Map<String, Object> requestLogin(Map<String, Object> param){
		Map<String, Object> result = new HashMap<>();
		try {
			result.put("REPL_CD", "0000");
			result.put("REPL_MSG", "GOOD");
			result.put("REPL_PAGE_MSG", "LOGIN.");
			
			String userId =(String) param.get("userId");

			String userPw =(String) param.get("userPw");
			
			if ( userId ==null || userId.isEmpty()) {
				//Error Happen
				throw new RestLoginException("1001","NULL ID","ENTER ID");
			}
			if ( userPw ==null || userPw.isEmpty()) {
				//Error Happen
				throw new RestLoginException("1002","NULL PW","ENTER PW");
			}
			// SELECT USER
			Map<String,Object> info = restLoginMapper.selectMemberInfo(param);
			// CHECK ID
			if (info ==null) {
				throw new RestLoginException("1003", "No Data", "i dont have your data");
			}
			// CHECK PASSWORD
			String dbPw = (String) info.get("PW"); // HASH sha2()
			
			String encPw = (String) encryptUtil.encryptSha256(userPw);
			if (!dbPw.equals(encPw)) {
				throw new RestLoginException("1004", "Wrong Data", "wrong your pw");
			}
			// TRUE -> DELIVER PAGE
			result.put("memberinfo", info);
			
		}
		catch (RestLoginException rex) {
			result.put("REPL_CD", rex.getReplCd());
			result.put("REPL_MSG", rex.getReplMsg());
			result.put("REPL_PAGE_MSG", rex.getReplPageMsg());
		}
		catch (Exception e) {
			// TODO: handle exception
			result.put("REPL_CD", "9999");
			result.put("REPL_MSG", e);
			result.put("REPL_PAGE_MSG", "에러가 발생했습니다.");
		}
		return result;
	}
}
