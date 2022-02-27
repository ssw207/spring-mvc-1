package hello.servlet.domain;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberRepositoryTest {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 각테스트가 끝나면 무조건 실행됨
    void after() {
        memberRepository.clearStore();
    }

    @Test
    public void save() throws Exception {
        //given
        Member hello = new Member("hello", 20);

        //when
        Member save = memberRepository.save(hello);

        //then
        Member byId = memberRepository.findById(save.getId());
        assertEquals(byId, save);
    }

    @Test
    public void findAll() throws Exception {
        //given
        Member m1 = new Member("m1", 20);
        Member m2 = new Member("m2", 30);

        Member save = memberRepository.save(m1);
        Member save1 = memberRepository.save(m2);

        //when
        List<Member> all = memberRepository.findAll();

        //then
        assertEquals(all.size(), 2);
        assertThat(all).contains(save, save1);
    }
}