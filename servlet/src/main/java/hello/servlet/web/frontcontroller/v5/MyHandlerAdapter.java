package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    /**
     * 어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메소드
     * @param handler 컨트롤러
     * @return 해당 어댑터가 컨트롤러를 처리할 수 있는지 여부
     */
    boolean supports(Object handler);

    /**
     * 실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환하는 메소드
     * @param request
     * @param response
     * @return 실제 컨트롤러가 반환하는 ModelView
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
