package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 비지니스 로직 수행
        List<Member> members = memberRepository.findAll();

        // 뷰에 필요한 값을 모델에 저장
        model.put("members", members);

        // 논리적 뷰 이름 반환
        return "members";
    }
}
