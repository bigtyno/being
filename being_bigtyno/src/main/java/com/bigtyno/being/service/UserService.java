package com.bigtyno.being.service;

import java.util.Date;
import java.util.List;

import com.bigtyno.being.common.Criteria;
import com.bigtyno.being.domain.BoardVO;
import com.bigtyno.being.domain.LoginDTO;
import com.bigtyno.being.domain.UserVO;

public interface UserService {
	UserVO login(LoginDTO dto) throws Exception;

	void keepLogin(String email, String sessionId, Date next) throws Exception;

	UserVO checkLoginBefore(String value);

	public void create(UserVO userVO) throws Exception;

	public UserVO read(String email) throws Exception;

	public List<UserVO> listPage(int page) throws Exception;

	public List<UserVO> listCriteria(Criteria cri) throws Exception;

	public int listCountCriteria(Criteria cri) throws Exception;

	public void modify(UserVO user) throws Exception;

	public UserVO kakaoLogin(UserVO userVO) throws Exception;

	public void confirmEmail(String email);

	public UserVO findById(String email);

	public void updatePassword(UserVO userVO);

	public void changePassword(String email, String name, String curPwd, String newPwd) throws Exception;

	public void deleteMember(String email) throws Exception;
}