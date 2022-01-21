package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

//Application 전체를 설정하고 구성하는 파일
public class AppConfig {
    //refactoring : 각각이 하나의 역할만을 담당할 수 있도록 변환됨. (역할에 대한 구현이 드러남)
    public MemberService memberService(){ //AppConfig는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해 주입(연결) 해준다 고 표현
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
