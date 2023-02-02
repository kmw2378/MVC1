package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 1. 파라미터에서 필요한 값을 꺼낸다.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // 2. 비지니스 로직 수행
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 3. 뷰에서 사용할 값을 모델에 저장
        model.put("member", member);

        // 4. 논리적 뷰 이름 반환
        return "save-result";
    }
}
