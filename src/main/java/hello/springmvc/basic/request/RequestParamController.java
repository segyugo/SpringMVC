package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@ResponseBody
@Controller
public class RequestParamController {

    @RequestMapping("/request-param")
    public String requestParamV1(@RequestParam String username,
                               @RequestParam int age){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v1")
    public String requestParamV2(@RequestParam String username,
                                 @RequestParam(required = false) Integer age){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v2")
    public String requestParamV3(@RequestParam(defaultValue = "guest") String username,
                                 @RequestParam(defaultValue = "20") int age){

        log.info("username={}, age={}", username, age);

        return "ok";
    }

    @RequestMapping("/request-param-v3")
    public String requestParamV4(@RequestParam Map<String, String> params ){

        String username = (String) params.get("username");
        log.info("username={}", username);

        return "ok";
    }


    @RequestMapping("/request-param-v4")
    public String requestParamV5(@RequestParam MultiValueMap<String, String> params ){

        List<String> usernames = params.get("username");

        log.info("username={}", usernames.get(0));


        return "ok";
    }



}
