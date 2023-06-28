package com.sparta.newposet.controller;


import com.sparta.newposet.dto.LoginRequestDto;
import com.sparta.newposet.dto.SignupRequestDto;
import com.sparta.newposet.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
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
    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto, res); //로그인검증 끝나면jwt토큰 쿠키에 넣고 그 쿠키 담을것 res에
        } catch (Exception e) {
           return "로그인 실패 아이디와 암호를 확인하세요";
        }


        return "로그인 완료";
    }
}