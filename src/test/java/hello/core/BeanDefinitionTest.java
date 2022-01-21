package hello.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); //factory method(자바 내 클래스) 방식
    //ac를 ApplicationContext로 설정하는 경우는 getBeanDefinition을 못한다.
    //보통은 BeanDefinition을 확인하는 일이 없기 때문에 ApplicationContext에는 기능을 제공하지 않음.
    //GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml"); //XML에 설정 했을 경우
    @Test @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                " beanDefinition = " + beanDefinition);
            }
        }
    }
}
