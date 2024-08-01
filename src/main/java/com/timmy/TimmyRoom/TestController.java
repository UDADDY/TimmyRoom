package com.timmy.TimmyRoom;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/api/v1/member/**")
    public List<Test> dbTest() {
        List<Test> tests = testRepository.findAll();

        return tests;
    }

    @GetMapping("/tokenTest")
    public String tokenTest(){
        return "OK";
    }

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "OK";
    }
}
