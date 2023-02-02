package hello.servlet.basic;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * JSON 형식으로 파싱할 수 있는 객체
 */
@Getter
@Setter
public class HelloData {

    private String username;
    private int age;

    public HelloData(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
