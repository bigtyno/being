package org.zerock.test;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.SearchCriteria;
import org.zerock.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

	@Inject
	private BoardDAO dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	
	 // @Test
	
	public void testCreate() throws Exception {
		  BoardVO board = new BoardVO();
		  board.setType("테스트");
		  board.setAcreage("테스트");
		  board.setBudget("테스트");
		  board.setField("테스트");
		  board.setSpace("테스트");
		  board.setContentOf("테스트");
		  dao.create(board); 
		  }
		 
	
	//@Test
	public void testRead() throws Exception {

		logger.info(dao.read(1).toString());
	}

	//@Test
	public void testUpdate() throws Exception {

		BoardVO board = new BoardVO();
		board.setNum(6);
		board.setType("소파");
		board.setAcreage("JJ");
		board.setBudget("1000만원");
		board.setField("ss");
		board.setSpace("ss");
		board.setTitle("글쓰기");
		board.setContentOf("ㅎㅎ");
		dao.update(board);
	}

	//@Test
	public void testDelete() throws Exception {

		dao.delete(1);
	}

	//@Test
	public void testListAll() throws Exception {

		logger.info(dao.listAll().toString());

	}

	@Test
	public void testListPage() throws Exception {

		int page = 2;

		List<BoardVO> list = dao.listPage(page);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getNum() + ":" + boardVO.getTitle());
		}
	}

	//@Test
	public void testListCriteria() throws Exception {

		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);

		List<BoardVO> list = dao.listCriteria(cri);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getNum() + ":" + boardVO.getTitle());
		}
	}

	//@Test
	public void testURI() throws Exception {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/board/read").queryParam("bno", 12)
				.queryParam("perPageNum", 20).build();

		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());

	}

	//@Test
	public void testURI2() throws Exception {

		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/{module}/{page}").queryParam("bno", 12)
				.queryParam("perPageNum", 20).build().expand("board", "read").encode();

		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}

	//@Test
	public void testDynamic1() throws Exception {

		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("타입");
		cri.setSearchType("t");

		logger.info("=====================================");

		List<BoardVO> list = dao.listSearch(cri);

		for (BoardVO boardVO : list) {
			logger.info(boardVO.getNum() + ": " + boardVO.getTitle());
		}

		logger.info("=====================================");

		logger.info("COUNT: " + dao.listSearchCount(cri));
	}

}
