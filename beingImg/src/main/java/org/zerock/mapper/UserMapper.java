package org.zerock.mapper;

import java.util.Date;
import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.UserVO;
import org.zerock.dto.LoginDTO;

public interface UserMapper {

	UserVO login(LoginDTO dto) throws Exception;

	void keepLogin(String email, String sessionId, Date next) throws Exception;

	UserVO checkUserWithSessionKey(String value);

	public void create(UserVO userVO) throws Exception;

	public UserVO read(String email) throws Exception;

	public List<UserVO> listPage(int page) throws Exception;

	public List<UserVO> listCriteria(Criteria cri) throws Exception;

	public int countPaging(Criteria cri) throws Exception;

	public void update(UserVO vo) throws Exception;
}
