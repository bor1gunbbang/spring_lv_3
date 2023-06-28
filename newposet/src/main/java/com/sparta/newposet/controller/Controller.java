package com.sparta.newposet.controller;

import com.sparta.newposet.dto.PostRequestDto;
import com.sparta.newposet.dto.PostResponseDto;
import com.sparta.newposet.entity.UserRoleEnum;
import com.sparta.newposet.jwt.JwtUtil;
import com.sparta.newposet.service.PostService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {
    private final PostService postService;

    private final JwtUtil jwtUtil;

    public Controller(PostService postService, JwtUtil jwtUtil){
        this.postService = postService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPostList() {
        return postService.getPostListV2();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PostMapping("/posts")
    public PostResponseDto createPost(@RequestHeader("Authorization") String token, @RequestBody PostRequestDto postRequestDto){
        return postService.createPost(/*token,*/postRequestDto);
    }
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/posts/{id}")
    public PostResponseDto deletePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        postService.deletePost(id, postRequestDto.getPassword());
        return new PostResponseDto(true);
    }
    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse res) {
        // Jwt 생성
        String token = jwtUtil.createToken("Robbie", UserRoleEnum.USER);

        // Jwt 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);

        return "createJwt : " + token;
    }

    @GetMapping("/get-jwt")
    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // 사용자 username
        String username = info.getSubject();
        System.out.println("username = " + username);
        // 사용자 권한
        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
        System.out.println("authority = " + authority);

        return "getJwt : " + username + ", " + authority;
    }
}
