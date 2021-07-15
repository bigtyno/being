package org.zerock.mapper;

import java.util.Date;

import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

public interface UserMapper {
	
	UserVO login(LoginDTO dto) throws Exception;

	void keepLogin(String email, String sessionId, Date next) throws Exception;

	UserVO checkUserWithSessionKey(String value);
}
