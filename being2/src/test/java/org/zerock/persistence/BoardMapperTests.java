package org.zerock.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// @ContextConfiguration(classes = { org.zerock.config.RootConfig.class })
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;

	//@Test
	public void selectBoardList() throws Exception {
		log.info("/* "+boardMapper.getClass().getName());
		log.info("/* boardMapper.selectBoardList()="+boardMapper.selectBoardList());
	}
	
	//@Test
		public void testDelete() throws Exception {

		boardMapper.delete(18);

	}
	
	@Test
		public void testPaging() throws Exception {
		//1 10
		Criteria cri = new Criteria();
		
		List<BoardVO> list = boardMapper.getLisWithPaging(cri);
		
		log.info("BoardVO");
	}
}