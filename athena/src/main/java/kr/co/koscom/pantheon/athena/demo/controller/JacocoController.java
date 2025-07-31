package kr.co.koscom.pantheon.athena.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JacocoController {

    @GetMapping("/test")
    public String test(@RequestParam int n) {
        return n >= 0 ? "hello" : "world";
    }
}
