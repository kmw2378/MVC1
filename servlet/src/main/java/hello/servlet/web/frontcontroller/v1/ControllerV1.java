package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 프론트 컨트롤러 V1, 다형성을 활용하기 위해 인터페이스로 작성
 * 1. 회원 폼
 * 2. 회원 저장
 * 3. 회원 목록
 */
public interface ControllerV1 {

    // HttpServlet의 service 메소드와 동일한 구조
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
