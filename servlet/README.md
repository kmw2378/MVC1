`@ServletComponentScan`
- 스프링 부트에서 `@ComponentScan`과 비슷한 기능
- WAS 안에 서블릿 컨테이너에 해당 어노테이션이 붙은 패키지 하위에 `@WebServlet`이 붙은 객체들을 서블릿 객체로 자동 등록

### 서블릿 객체 생성 방법
- `HttpServlet`을 상속 받아야 한다.
- `@WebServlet` 어노테이션을 클래스 단위에 붙여야 한다.
  - `name` : 서블릿 객체 이름
  - `urlPatterns` : 도메인 뒤 해당 `URL` 입력시 호출
- 서블릿 객체를 생성 후 보안 수준이 `protected`인 `service` 메소드 구현 (절대 `public` 수준 `service` 메소드 선택 X)
>__참고)__ IntelliJ에서 메소드 오바라이딩 단축키
> 
> `protect` 수준 이상의 메소드는 별도로 `control` + `o` 사용
> 
> `public` 수준은 `option` + `enter` 사용

>__참고)__ HTTP 요청 로그 확인
> 
> `application.properties`에 다음을 추가
>
>```
>logging.level.org.apache.coyote.http11=debug
>```
>운영 서버에 이를 추가하면 성능 저하가 발생할 수 있으니 개발 단계 수준에서만 적용하자.

### HTTP 요청 메시지의 임시 저장소
- 저장 : `request.setAttribute(name, value)`
- 조회 : `request.getAttribute(name)`

추후에 MVC 패턴에서도 이것이 사용된다.

### 세션 관리 기능
어떤 사용자의 로그인 여부를 유지할 때 사용
- `requset.getSession(create : true)`

>__중요__
>
>`HttpServletRequset`, `HttpServletResponse` 객체는 요청/응답 메시지를 편리하게 사용해주는 객체이기에 HTTP 스펙이 제공하는 요청, 응답 메시지를 정확히 이해해야 한다.

### HTTP 요청 데이터
1. `GET` 방식의 쿼리 파라미터
   - message body 없이 쿼리 파라미터에 직접 데이터를 담아 전달
   - ex) 검색
2. `POST` 방식의 HTML-Form
   - 사용자가 지정된 Form(양식)에 값을 넣고 전송하는 경우
   - 메시지 바디에 쿼리 파라미터 형식으로 전달 -> 쿼리 파라미터 방식과 형태가 같음
   - ex) 회원 가입, 물품 구매 등
3. HTTP message body 에 직접 데이터를 담아서 요청
   - HTTP API에서 주로 사용 (ex. JSON, XML, TEXT)
   - `POST`, `PUT`, `PATCH` 다 사용 가능

- HTTP 요청 데이터는 이 세가지에서 벗어나지 않는다! 하나씩 알아보자.

#### 1. `GET` 방식의 쿼리 파라미터
- 단일 파라미터
  - `request.getParameter(name)`
  - 제일 많이 사용
- 복수 파라미터
  - `request.getParameterValues(name)`
  - 파라미터를 중복으로 보내는 경우는 드물다.

#### 2. `POST` HTML Form
- 특징
  - `content-type=application/x-www-form-urlencoded`
  - 메시지 바디의 내용은 쿼리 파라미터 형태와 동일
    - `request.getParameter(name)` 으로 파라미터 값을 꺼낼 수 있다.

>__참고__
>
> `content-type`은 HTTP 메시지 바디의 데이터 형식을 지정한다.
> 이를 통해 쿼리 파라미터와 HTML Form을 구분한다.
> - 쿼리 파라미터 : X
> - HTML Form : `application/x-www-form-urlencoded`

### HTTP 응답 메시지
- `HttpServletResponse`를 사용하여 생성
  - 응답 코드 (200, 300, 400 등) 지정
  - 헤더 생성
  - 바디 생성
  - `Content-Type`
  - 쿠키
  - Redirect (리다이렉트)
- HTTP 응답 데이터
  - 단순 텍스트
    - `response.getWriter().write("ok");`
  - HTML
  - HTTP API - JSON 응답

### 서블릿과 JSP의 한계
- JSP 덕분에 HTML 작업은 깔끔해졌다.
- 비지니스 로직과 뷰 영역이 한 곳에 있다.
  - UI와 비지니스 로직의 라이프 사이클은 완전 다르다.
    - __라이프 사이클이 다르면 반드시 분리해야 한다.__
  - 비지니스 로직과 화면(뷰 렌더링)을 한 번에 처리해야 하므로 유지보수가 매우 어려워진다.
  - 화면을 바꾸기 위해선 JSP 자체를 건들여야 하는데 이렇게 되면 비지니스 로직을 건드릴 가능성이 크다.
- __MVC 패턴을 사용하면 이 한계를 극복할 수 있다.__

### MVC 패턴
- Model
  - 컨트롤러와 뷰 사이에 데이터 전달 객체
  - 가방 정도로 생각하자.
  - 덕분에 비즈니스 로직과 화면이 완전히 분리된다.
  - ex) 서블릿에서 `request.setAttribute()`, `request.getsAttribute()`
- View
  - 화면을 담당
  - ex) `.jsp`
- Controller
  - HTTP 요청을 받아서 파라미터 검증
  - 필요한 것들 처리
  - 비즈니스 로직 수행
    - 엄밀히 말하면 비즈니스 로직을 호출
    - 컨트롤러가 `Service` 호출 (여기서 오류 발생시 상태 코드) -> `Repository` 호출식 이다.
  - 뷰에 전달할 데이터를 조회한 후 모델에 담아준다.
  - ex) `HttpServlet`을 상속한 클래스들

`WEB-INF`
- 외부에서 JSP 호출을 원하지 않을 때는 이 경로에 `.jsp` 파일을 넣어주면 된다.
- 이는 WAS의 관례이다.
- 항상 컨트롤러를 거쳐서 `forward()`를 호출할 때만 이 경로내에 파일들을 사용한다.

`dispatcher.forward()`
- 서버에서 로직 실행시 하나의 메소드가 다른 메소드를 호출하는 방식
- 클라이언트 입장에선 이를 알 수 없다.

### `Redirect` vs `forward`
  - `Redirect` : 클라이언트에 응답이 나갔다가 클라이언트가 `redirect` 경로로 다시 요청
    - 클라이언트가 인지

> 참고
> 
> `JSP`의 `jstl` 을 사용하면 반복문 같은 번거로운 작업을 쉽게 수행할 수 있다.

### MVC 패턴의 한계
- `forward()` 메소드 중복
- `viewPath` 중복
  - prefix : `/WEB-INF/views/`
  - suffix : `.jsp`
- 사용되지 않는 객체
  - `HttpServletResponse`
- 결론) 공통 처리가 어렵다.
  - 공통 부분을 메소드로 뽑아도 그것을 항상 호출해야 되는 번거러움 존재

### MVC 패턴의 공통 처리를 하는 방법?
최상단에 컨트롤러를 두어서 공통 처리를 여기서 하자!
-  프론트 컨트롤러
   - 입구를 하나로 만든다!
   - 필터나 인터셉터와는 상이한 개념

스프링 MVC 패턴의 핵심은 바로 이 프론트 컨트롤러(`DispatcherServlet`)이다.
이렇게 __프론트 컨트롤러를 도입하면 나머지 컨트롤러는 서블릿을 사용하지 않아도 된다.__

### 프론트 컨트롤러의 역할
- `URL` 매핑 정보를 통해 알맞는 컨트롤러를 호출

### 어댑터 패턴
- 다른 타입의 프론트 컨트롤러를 동시에 사용할 수 있게 해주는 매개체
- 즉, 어떠한 값이 입력되던 간에 리턴되는 타입은 항상 같다!
  - ex) 110V, 220V
- 핸들러(컨트롤러의 더 넓은 범위)의 매핑 정보를 조회하고 이에 맞는 어댑터를 조회하여 가져온다.
- 이 어댑터가 알맞는 프론트 컨트롤러를 호출해준다.
- 이후, 동일한 과정 실행

>__참고__
> 
> 어댑터가 존재하면 어떠한 핸들러든 다 처리할 수 있다.