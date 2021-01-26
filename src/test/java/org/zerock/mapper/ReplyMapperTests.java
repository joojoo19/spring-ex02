package org.zerock.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {

	private Long[] bnoArr = { 230L, 229L, 227L, 226L, 225L };
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

	@Test
	public void testExist() {
		assertNotNull(mapper);
	}

	@Test
	public void tesCreate1() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			log.info(i + ", " + (i % 5));

			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("reply test" + i);
			vo.setReplyer("replyer" + i);

			mapper.insert(vo);
		});
	}

	@Test
	public void testRead() {
		Long rno = 8L;

		ReplyVO vo = mapper.read(rno);

		assertEquals("reply test5", vo.getReply());
	}

	@Test
	public void tesCreate2() {

		ReplyVO vo = new ReplyVO();
		vo.setBno(230L);
		vo.setReply("reply test");
		vo.setReplyer("newbie01");

		mapper.insert(vo);

		try {
			vo.setBno(228L);
			mapper.insert(vo);
			fail();
		} catch (Exception e) {

		}
	}

	@Test
	public void testDelete() {
		Long rno = 1L;

		mapper.delete(rno);
	}

	@Test
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(8L);
		vo.setReply("reply update test");

		mapper.update(vo);

		vo = mapper.read(8L);

		assertEquals("reply update test", vo.getReply());
	}

	@Test
	public void testList() {
		List<ReplyVO> list = mapper.getListWithPaging(null, 229L);

		assertNotEquals(0, list.size());
	}
}
