package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //MemberServiceImpl은 추상화(MemberRepository)와 구체화(MemoryMemberRepository) 모두에 의존하고 있음. -> DIP 위반!

    @Override
    public void join(Member member){
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
