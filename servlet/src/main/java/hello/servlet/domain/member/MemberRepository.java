package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려,
 * 싱글톤 패턴 사용
 */
public class MemberRepository {

    // 공유 필드
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);  // 안전하게 sequence 가 아닌 member.getId() 사용

        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 기존 store에 저장된 값을 건들지 않기 위해 new 연산자를 통해 별도의 리스트를 만든다.
    }

    public void clearStore() {
        store.clear();  // 테스트 코드 단계에서 사용
    }
}