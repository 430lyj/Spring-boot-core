package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@Component 애노테이션이 있는 클래스를 찾아서 다 자동으로 스프링 빈으로 등록해줌.
@ComponentScan(
        // basePackages = "hello.core.member", //이 위치에서부터 하위 패키지로 찾아 들어감. 여러 개 둘 수도 있음.
        // 지정하지 않으면 해당 설정정보 클래스의 패키지가 시작위치
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
 ) //AppConfig는 수동으로 등록하는 거기 때문에 AppConfig를 제외해줌.
public class AutoAppConfig {
}
