package com.sparta.newposet.controller;


import com.sparta.newposet.dto.SignupRequestDto;
import com.sparta.newposet.service.UserService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto){
        userService.signup(requestDto);
        return "회원가입이 완료되었습니다.";
    }
}