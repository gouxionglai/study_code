import com.cn.springdemo.bean.Helloworld;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author weilai
 * @description
 * @since 2019/10/14
 */
public class HelloworldTest {

    @Test
    public void testHello(){


        //原始写法
//        Helloworld helloworld = new Helloworld();
//        helloworld.setName2("aha spring test");
//        helloworld.hello();

        //1 获取容器: 自动将配置文件中定义的bean全部初始化
        //已经打印了constructor init...  set name...
        //ClassPathXmlApplicationContext从类路径下获取配置
        //FileSystemXmlApplicationContext从文件路径下获取配置
        //实现关系：ApplicationContext-- ConfigurableApplicationContext -- ClassPath/FileSystemXmlApplicationContext
//        ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");

        //2 从容器中取出bean
        Helloworld helloworld = (Helloworld) context.getBean("helloworld");

        //3 调用方法
        helloworld.hello();

        //4 销毁
        context.close();
    }
}
