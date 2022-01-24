package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor //생성자에 Autowired 자동 주입!
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final ObjectProvider<MyLogger> myLoggerProvider; //MyLogger를 주입 받는게 아니라 MyLogger을 찾을 수 있는 (Dependency Lookup 할 수 있는 것이 됨)

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){ //자바에서 제공하는 표준 서블렛 규약으로 고객 요청 정보를 받을 수 있음!
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = myLoggerProvider.getObject(); //http request가 온 시점 이후에 객체 요청 -> MyLogger 의존 관계 주입 가능
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
