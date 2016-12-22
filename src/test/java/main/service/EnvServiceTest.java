package main.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnvServiceTest {

	@Resource
	private EnvService envService;

	@Test
	public void findEnvId() {
		Long appId = 2L;
		String appenv = "test";
		Long actual = envService.findEnvId(appId, appenv);
		Long expected = 15L;
		Assert.assertEquals(expected, actual);
	}
}
