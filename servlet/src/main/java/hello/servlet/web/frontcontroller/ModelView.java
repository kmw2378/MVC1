package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {

    private String viewName;    // 논리 뷰 이름
    private Map<String, Object> model = new HashMap<>();    // HttpServletRequest 객체의 저장공간을 대체할 별도의 공간

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
