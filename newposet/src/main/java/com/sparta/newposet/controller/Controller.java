package com.sparta.newposet.controller;

import com.sparta.newposet.dto.PostRequestDto;
import com.sparta.newposet.dto.PostResponseDto;
import com.sparta.newposet.entity.User;
import com.sparta.newposet.entity.UserRoleEnum;
import com.sparta.newposet.jwt.JwtUtil;
import com.sparta.newposet.security.UserDetailsImpl;
import com.sparta.newposet.service.PostService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    //게시글 생성하면서 토큰 인증
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto,userDetails.getUser());
    }
    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        return postService.updatePost(id, postRequestDto,userDetails.getUser());
    }

    @DeleteMapping("/posts/{id}")
    public PostResponseDto deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,@PathVariable Long id, @RequestBody PostRequestDto postRequestDto){
        postService.deletePost(id, userDetails.getUser());
        return new PostResponseDto(true);
    }

}
