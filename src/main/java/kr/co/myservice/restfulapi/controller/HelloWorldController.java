package kr.co.myservice.restfulapi.controller;

import kr.co.myservice.restfulapi.bean.HelloWorldBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        // return을 bean타입으로 전달하게 되면 자동적으로 response body에 json형식의 데이터로 전달함
        return new HelloWorldBean("Hello World!!");
    }
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World ,%s", name));
    }


}
