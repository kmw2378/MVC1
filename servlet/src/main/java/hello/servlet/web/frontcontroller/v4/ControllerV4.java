package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     * @param paramMap 파라미터 정보를 담은 객체
     * @param model 뷰에 필요한 정보를 담은 객체
     * @return view의 논리적 이름
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
