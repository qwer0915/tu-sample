package com.framework.register;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.register.mapper.RegisterMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RegisterService {
	RegisterMapper registerMapper;
	public RegisterService(RegisterMapper registerMapper) {
		this.registerMapper=registerMapper;
	}
	@Transactional(readOnly = false)
	public Map<String,Object> requestRegister(Map<String,Object> param){
		Map<String,Object> resultMap=new HashMap<>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "GOOD");
			resultMap.put("REPL_PAGE_MSG", "REGISTER SERVICE.");
			log.info("CHECK PARAMS {}",param);
			// Params check * 이름 검증만
			String userName = (String) param.get("userName");
			if(userName ==null || userName.isEmpty()) {throw new RegisterException("2001", "Error2001", "Enter userName");}
			// 중복된 아이디 확인
			int duplCnt =registerMapper.selectMemberDuplicateCount(param);
			if(duplCnt >0) {
				throw new RegisterException("2002", "DUPL", " DUPL");
			}
			// 회원가입 처리 
			if( registerMapper.insertMember(param)<0) {
				throw new RegisterException("2003", "registerMapper", " Happe");
			}
			// -원래 불필요- 정상 등록 확인하기 위해 db 조회 설정
			Map<String,Object> memberInfo =registerMapper.selectMemberInfo(param);
			resultMap.put("MEMBER_INFO", memberInfo);
			
		}catch(RegisterException rex) {
			resultMap.put("REPL_CD", rex.getReplCd());
			resultMap.put("REPL_MSG", rex.getReplMsg());
			resultMap.put("REPL_PAGE_MSG", rex.getReplPageMsg());
		}catch(Exception ex) {
			resultMap.put("REPL_CD", "9999");
			resultMap.put("REPL_MSG", ex);
			resultMap.put("REPL_PAGE_MSG", "에러가 발생했습니다.");
		}
		return resultMap;
	}
	@Transactional(readOnly = true)
	public Map<String,Object> getMember(String userId){
		Map<String,Object> resultMap=new HashMap<>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "GOOD");
			resultMap.put("REPL_PAGE_MSG", "REGISTER SERVICE.");
			
			// 초기화
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("userId", userId);
			
			// 조회
			Map<String,Object> memberInfo =registerMapper.selectMemberInfo(paramMap);
			resultMap.put("MEMBER_INFO", memberInfo);
			
		}catch(RegisterException rex) {
			resultMap.put("REPL_CD", rex.getReplCd());
			resultMap.put("REPL_MSG", rex.getReplMsg());
			resultMap.put("REPL_PAGE_MSG", rex.getReplPageMsg());
		}catch(Exception ex) {
			resultMap.put("REPL_CD", "9999");
			resultMap.put("REPL_MSG", ex);
			resultMap.put("REPL_PAGE_MSG", "에러가 발생했습니다.");
		}
		return resultMap;
	}
	@Transactional(readOnly = false)
	public Map<String,Object> requestModify(Map<String,Object> param){
		Map<String,Object> resultMap=new HashMap<>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "GOOD");
			resultMap.put("REPL_PAGE_MSG", "REGISTER SERVICE.");
			log.info("CHECK PARAMS {}",param);
			
			// 회원가입 처리 
			if( registerMapper.updateMember(param) <0) {
				throw new RegisterException("2003", "registerMapper", " Happe");
			}
			// -원래 불필요- 정상 등록 확인하기 위해 db 조회 설정
			Map<String,Object> memberInfo =registerMapper.selectMemberInfo(param);
			resultMap.put("MEMBER_INFO", memberInfo);
			
		}catch(RegisterException rex) {
			resultMap.put("REPL_CD", rex.getReplCd());
			resultMap.put("REPL_MSG", rex.getReplMsg());
			resultMap.put("REPL_PAGE_MSG", rex.getReplPageMsg());
		}catch(Exception ex) {
			resultMap.put("REPL_CD", "9999");
			resultMap.put("REPL_MSG", ex);
			resultMap.put("REPL_PAGE_MSG", "에러가 발생했습니다.");
		}
		return resultMap;
	}

	@Transactional(readOnly = false)
	public Map<String,Object> requestRemove(Map<String,Object> param){
		Map<String,Object> resultMap=new HashMap<>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "GOOD");
			resultMap.put("REPL_PAGE_MSG", "REGISTER SERVICE.");
			log.info("CHECK PARAMS {}",param);
			
			// 회원가입 처리 
			if( registerMapper.removeMember(param) <0) {
				throw new RegisterException("2003", "registerMapper", " Happe");
			}
			// -원래 불필요- 정상 등록 확인하기 위해 db 조회 설정
			Map<String,Object> memberInfo =registerMapper.selectMemberInfo(param);
			resultMap.put("MEMBER_INFO", memberInfo);
			
		}catch(RegisterException rex) {
			resultMap.put("REPL_CD", rex.getReplCd());
			resultMap.put("REPL_MSG", rex.getReplMsg());
			resultMap.put("REPL_PAGE_MSG", rex.getReplPageMsg());
		}catch(Exception ex) {
			resultMap.put("REPL_CD", "9999");
			resultMap.put("REPL_MSG", ex);
			resultMap.put("REPL_PAGE_MSG", "에러가 발생했습니다.");
		}
		return resultMap;
	}
}
