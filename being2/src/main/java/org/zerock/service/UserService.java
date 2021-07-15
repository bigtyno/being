package org.zerock.service;

import java.util.Date;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

public interface UserService {
	UserVO login(LoginDTO dto) throws Exception;

	void keepLogin(String email, String sessionId, Date next) throws Exception;

	UserVO checkLoginBefore(String value);
}