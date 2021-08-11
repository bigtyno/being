package com.bigtyno.being.service;

import java.util.HashMap;
import java.util.List;

import com.bigtyno.being.domain.Criteria;
import com.bigtyno.being.domain.InteriorAskVO;

public interface InteriorAskService {
	
	public void create (InteriorAskVO interiorAsk) throws Exception;
	
	public List<InteriorAskVO> selectInteriorAskList() throws Exception;
	
	 public InteriorAskVO read(Integer num) throws Exception;
	 
	 public List<InteriorAskVO> listPage(HashMap<String, Integer> param) throws Exception;

	 public List<InteriorAskVO> listCriteria(Criteria cri) throws Exception;

	 public int listCountCriteria(Criteria cri) throws Exception;
	 
	 public void modify(InteriorAskVO interiorAsk) throws Exception;
	 
	 public void modify2(InteriorAskVO interiorAsk) throws Exception;

	 public void remove(Integer num) throws Exception;


}
