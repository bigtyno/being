package org.zerock.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;
import org.zerock.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional
	public UserVO login(LoginDTO dto) throws Exception {
		return userMapper.login(dto);
	}

	@Override
	@Transactional
	public void keepLogin(String email, String sessionId, Date next) throws Exception {
		userMapper.keepLogin(email, sessionId, next);

	}

	@Override
	@Transactional
	public UserVO checkLoginBefore(String value) {
		return userMapper.checkUserWithSessionKey(value);
	}
}