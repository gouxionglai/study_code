import com.cn.springdemo.annotation.DemoController;
import com.cn.springdemo.annotation.DemoService;
import com.cn.springdemo.aop.Mathic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author weilai
 * @description
 * @since 2019/10/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/applicationContext.xml")
public class AnnotationTest {
//    @Autowired
//    private DemoController controller;
//    @Autowired
//    private DemoService service;
    @Autowired
    private Mathic mathic;

    @Test
    public void testAnnotation(){
        ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
        DemoController controller = (DemoController) context.getBean("demoController");
        System.out.println(controller);

        DemoService service = (DemoService) context.getBean("demoService");
        System.out.println(service);
    }

    @Test
    public void testAop(){
        //从容器取
//        ApplicationContext context = new ClassPathXmlApplicationContext("config/applicationContext.xml");
//        Mathic mathic = (Mathic) context.getBean("mathic");
        int result = mathic.add(3,5);
        System.out.println(result);

//        try {
//            mathic.div(3,0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
