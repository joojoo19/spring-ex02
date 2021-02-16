package org.zerock.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class BoardControllerTests {
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx;

	private MockMvc mockMvc; // 서버를 실행시키지 않고 디스패처서블릿을 실행시키기 위해..

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mockMvc);
	}

	@Test
	public void testList() throws Exception {
		ResultActions res = mockMvc.perform(MockMvcRequestBuilders.get("/board/list"));
		MvcResult rs = res.andReturn();
		ModelAndView mv = rs.getModelAndView();

		log.info("*************" + mv.getView() + "**************");
		log.info("*************" + mv.getModel().get("list") + "**************");

		Object o = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModel()
				.get("list"); // list의 attr가 있는지 확인

		assertNotNull(o);
		assertTrue(o instanceof List);
		assertNotEquals(((List) o).size(), 0);

	}

	@Test
	public void testListPaging() throws Exception {

		MvcResult res = mockMvc
				.perform(MockMvcRequestBuilders.get("/board/list").param("pageNo", "2").param("amount", "10"))
				.andReturn();
		Map<String, Object> model = res.getModelAndView().getModel();
		List list = (List) model.get("list");

		assertEquals(10, list.size());

	}

	@Test
	public void testRegister() throws Exception {
		int before = mapper.getList().size();
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/board/register").param("title", "test new title")
				.param("content", "test new content").param("writer", "user00")).andReturn();
		ModelAndView mv = res.getModelAndView();
		FlashMap map = res.getFlashMap();

		int after = mapper.getList().size();

		assertEquals(before + 1, after);
		assertEquals("redirect:/board/list", mv.getViewName());
		assertNotNull(map.get("result"));

		log.info(map.get("result") + "*****************************");

	}

	@Test
	public void testGet() throws Exception {
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("bno", "27")).andReturn();
		String viewName = res.getModelAndView().getViewName();
		Map<String, Object> modelMap = res.getModelAndView().getModelMap();
		assertEquals("board/read", viewName);
		assertNotNull(modelMap.get("board"));
		assertEquals(new Long(27), ((BoardVO) modelMap.get("board")).getBno());
	}

	@Test
	public void testModify() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("new title");
		board.setContent("new content");
		board.setWriter("user00");

		mapper.insertSelectKey(board);

		Long key = board.getBno();
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify").param("bno", key + "")
				.param("title", "test modify new title").param("content", "test modify new content")
				.param("writer", "user00")).andReturn();
		FlashMap map = res.getFlashMap();
		String viewName = res.getModelAndView().getViewName();
		BoardVO mod = mapper.read(key);

		assertEquals("test modify new title", mod.getTitle());
		assertEquals("test modify new content", mod.getContent());
		assertEquals("success", map.get("result"));
		assertEquals("redirect:/board/list", viewName);
	}

	@Test
	public void testRemove() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("new title");
		board.setContent("new content");
		board.setWriter("user00");

		mapper.insertSelectKey(board);
		Long key = board.getBno();

		int before = mapper.getList().size();
		MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", key + ""))
				.andReturn();
		int after = mapper.getList().size();

		FlashMap map = res.getFlashMap();
		String viewName = res.getModelAndView().getViewName();

		assertEquals(before - 1, after);
		assertEquals("success", map.get("result"));
		assertEquals("redirect:/board/list", viewName);
	}
}
