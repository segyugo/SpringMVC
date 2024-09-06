package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());


    // 다른 url을 같은 요청으로 매핑한다.
    @GetMapping(value = {"/hello-basic", "/hello-basic/"})
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    //경로 변수 사용
    @GetMapping(value = {"/mapping/{userId}"})
    // public String helloBasicV2(@PathVariable String userId)
    // 위와 같이 경로 변수와 매개 변수명이 같다면 경로 변수 생략 가능
    public String mappingPath(@PathVariable("userId") String userId){
        log.info("userID = {}", userId);
        return "ok";
    }

    // 다증 경로 파라미터
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("userId = {}, orderId = {}", userId, orderId);
        return "ok";
    }

    // 쿼리 파라미터로 특정 값에만 매핑하기
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(@RequestParam String mode){
        log.info("mappingParam {}", mode);
        return "ok";
    }

    // 헤더가 특정 값에만 매핑하기
    @GetMapping(value = "/mapping-headers", headers = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-consume", produces = "application/json")
    public String mappingConsume(){
        log.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-accept", produces = "text/plain")
    public String mappingAccept(){
        log.info("mappingParam");
        return "ok";
    }


}
