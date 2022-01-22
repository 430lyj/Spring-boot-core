package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Application 전체를 설정하고 구성하는 파일 (공연 기획자와 같은 역할) > 이제 클라이언트 코드를 수정할 필요가 없음.
@Configuration //Application의 설정정보를 담은 클래스다 라고 명시해주는 것. (singleton을 위해 존재 -> 없으면 스프링 빈 등록은 되지만 싱글톤x)
public class AppConfig {
    //refactoring : 각각이 하나의 역할만을 담당할 수 있도록 변환됨. (역할에 대한 구현이 드러남)

    //@Bean memberService -> new MemoryMemberRepository
    //@Bean orderService -> new MemoryMemberRepository, new RateDiscountPolicy -> 과연 싱글톤 패턴이 깨진 걸까?
    @Bean
    public MemberService memberService(){ //AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결) 해준다 고 표현
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
