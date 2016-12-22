package main.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import main.domain.Parameter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParamServiceTest {

	@Resource
	private ParamService paramService;

	@Test
	public void findFileParameterTest() {
		Long envId = 1L;
		List<Parameter> params = paramService.findFileParameter(envId);
		Assert.assertNotNull(params);
	}
}
