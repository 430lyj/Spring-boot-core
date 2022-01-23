package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository; //필드 주입 -> 권장하지 않음. 순수 자바 코드로는 외부에서 변경이 불가능해서 setter를 또 만들어줘야함. (수정자로 돌아감)
    private final DiscountPolicy discountPolicy;

    @Autowired //생성자가 하나인 경우는 @Autowired 생략해도 똑같은 효과 (자동 의존관계 주입)
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } //순수 인터페이스에만 의존. 남자 배우(OrderServiceImpl) 입장에서는 여자 배우가 존재한다는 것만 알고, 어떤 배우가 캐스팅되었는지는 모르는 것!

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
