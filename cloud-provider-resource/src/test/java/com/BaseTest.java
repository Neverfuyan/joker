package com;


import com.wj.ResourceApplication;
import com.wj.springcloud.AppApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyselfApplicationTests.class)
public class BaseTest {
}
