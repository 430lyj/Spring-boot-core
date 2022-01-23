package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //필수 필드인 final이 붙은 값들을 가지고 생성자를 만들어주는 annotation (@Autowired 붙은 생성사 불필요!)
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; //필드 주입 -> 권장하지 않음. 순수 자바 코드로는 외부에서 변경이 불가능해서 setter를 또 만들어줘야함. (수정자로 돌아감)
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) { //@Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        int discountPrice = discountPolicy.discount(memberRepository.findById(memberId), itemPrice);
        //단일 책임 원칙이 잘 지켜진 것. 할인 관련한 건 난 모르겠고, discountPolicy에서 해결해줘 와 같은 것
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
