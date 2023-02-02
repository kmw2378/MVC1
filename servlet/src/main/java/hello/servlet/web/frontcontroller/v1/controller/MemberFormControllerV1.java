package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 제어권 넘겨주기
        String viewPath = "/WEB-INF/views/new-form.jsp";    // 제어권을 넘겨줄 대상(JSP 파일, 뷰)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);  // viewPath에 있는 경로의 JSP 파일을 찾는다.
        dispatcher.forward(request, response);  // 서버 내부에서 해당 JSP를 실행한다. (제어권을 넘겨준다.) 리다이렉트 X
    }
}
