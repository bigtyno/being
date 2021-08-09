package org.zerock.persistence;

import java.util.Date;

import com.bigtyno.being.domain.LoginDTO;
import com.bigtyno.being.domain.UserVO;

public interface UserDAO {
	public UserVO login(LoginDTO dto) throws Exception;

	public void keepLogin(String uid, String sessionId, Date next);

	public UserVO checkUserWithSessionKey(String value);
}