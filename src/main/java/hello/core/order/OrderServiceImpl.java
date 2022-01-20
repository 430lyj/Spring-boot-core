package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 여기서는 DIP(의존성 역전 원칙) 위반 > 추상화는 세부 사항에 의존해서는 안된다. (여기서는 추상화인 인터페이스가 구체화된 클래스에 의존 중)
    // + OCP도 위반. (클라이언트 코드인 서비스 코드의 변경 발생!)

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        int discountPrice = discountPolicy.discount(memberRepository.findById(memberId), itemPrice);
        //단일 책임 원칙이 잘 지켜진 것. 할인 관련한 건 난 모르겠고, discountPolicy에서 해결해줘 와 같은 것
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
