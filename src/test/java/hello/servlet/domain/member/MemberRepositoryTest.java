package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void save() {
        Member member = new Member("hello", 20);

        Member saveMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(saveMember.getId());

        Assertions.assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("hello", 20);
        Member member2 = new Member("hello", 20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);
    }



}
