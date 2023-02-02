package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

/**
 * 요청 파라미터의 정보를 자바의 Map 타입으로 넘기면서 서블릿 종속성을 제거한 컨트롤러,
 *  1. 덕분에 테스트가 유리하고 단순하다.
 *  2. Model 객체를 별도로 만들어 request.get(set)Attribute 사용 X
 *  3. View 객체의 viewPath의 중복을 제거하고 단순히 논리 이름(members, save-result, new-form)만 사용하고 프론트 컨트롤러에서 물리 이름 대입
 */
public interface ControllerV3 {
    // 서블릿 관련 파라미터가 없다. 서블릿 종속성 X
    ModelView process(Map<String, String> paramMap);    // 파라미터 : 뷰 렌더링에 필요한 것들
}
