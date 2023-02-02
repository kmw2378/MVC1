package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 요청 객체의 여러 가지 값들
        System.out.println("request Method = " + request.getMethod());
        String username = request.getParameter("username");
        System.out.println("request username parameter = " + username);

        // 응답 객체의 여러 가지 기능
        response.setContentType("text/plain");  // Content-Type 설정
        response.setCharacterEncoding("utf-8"); // charset 설정
        response.getWriter().write("hello " + username);    // 응답 메시지 설정
    }
}
