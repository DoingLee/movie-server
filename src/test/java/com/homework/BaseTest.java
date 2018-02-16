package com.homework;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author：ldy on 16/02/2018 13:19
 */
@RunWith(SpringRunner.class) //1.4版本之前用的是SpringJUnit4ClassRunner.class
@SpringBootTest(classes = Application.class) //1.4版本之前用的是@SpringApplicationConfiguration(classes = Application.class)
//@ActiveProfiles("dev") //使用的spring boot配置文件为application-dev.xml
public class BaseTest {

}

