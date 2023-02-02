package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // 비즈니스 로직은 뷰만 레더링 하면 되므로 단순히 논리적 뷰 이름 반환
        return "new-form";
    }
}