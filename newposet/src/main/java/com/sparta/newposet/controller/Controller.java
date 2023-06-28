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

}
