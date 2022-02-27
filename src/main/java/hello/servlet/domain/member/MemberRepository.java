package hello.servlet.domain.member;

import java.util.*;

/**
 * 동시성 문제 고려되어있지 않음, 실무에서 ConcurrentHashHap, AtomicLong 사용고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 원본의 조작을 방어하기 위해 복사본 List를 리턴함
    }

    public void clearStore() {
        store.clear();
    }
}
