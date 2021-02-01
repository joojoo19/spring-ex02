package org.zerock.mapper;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.SampleTxService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class SampleTxServiceTests {
	@Setter(onMethod_ = @Autowired)
	private SampleTxService service;
	
	@Test
	public void testLong() {
		
		String str = "012345678901234567890123456789012345678901234567890123456789";
		service.addData(str);
	}


}
