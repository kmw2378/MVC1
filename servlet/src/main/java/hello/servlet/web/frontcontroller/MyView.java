package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * View를 담당하는 클래스, JSP 외에 다른 뷰를 렌더링하는 경우엔 이를 인터페이스로 만들어 다형성을 활용할 수도 있다.
 */
public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 뷰를 호출하는 행위 자체를 렌더링이라 한다. 즉, 화면으로 제어권을 넘겨준다.
    // JSP forward
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        modelToRequestAttribute(model, request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    // 모델에 담긴 정보를 request 저장 공간에 옮긴다. 참고로, 이는 뷰 템플릿이 jsp 인 경우에만 해당한다.
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
