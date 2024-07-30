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
    private TestService testService;

    @Autowired
    private TestRepository testRepository;

    @GetMapping("/test")
    public String test() {
        return testService.test();
    }

    @GetMapping("/db")
    public List<Test> dbTest() {
        List<Test> tests = testRepository.findAll();

        return tests;
    }
}
