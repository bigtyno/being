package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.InteriorVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.InteriorMapper;

@Service
public class InteriorServiceImpl implements InteriorService {

	@Autowired
	private InteriorMapper interiorMapper;

	@Override
	@Transactional
	public void create(InteriorVO interior) throws Exception {
		interiorMapper.create(interior);
	}

	@Override
	@Transactional
	public List<InteriorVO> selectInteriorList() throws Exception {
		return interiorMapper.selectInteriorList();
	}

	@Override
	public List<InteriorVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return interiorMapper.listPage(page);
	}

	@Override
	public List<InteriorVO> listCriteria(Criteria cri) throws Exception {
		return interiorMapper.listCriteria(cri);
	}

	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		return interiorMapper.countPaging(cri);
	}

	@Override
	@Transactional
	public InteriorVO read(Integer num) throws Exception {
		return interiorMapper.read(num);
	}

	@Override
	public void modify(InteriorVO interior) throws Exception {
		interiorMapper.update(interior);
	}

	@Override
	public void remove(Integer num) throws Exception {
		interiorMapper.delete(num);
	}

}
