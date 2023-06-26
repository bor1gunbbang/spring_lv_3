package com.sparta.newposet.controller;

import com.sparta.newposet.dto.PostRequestDto;
import com.sparta.newposet.dto.PostResponseDto;
import com.sparta.newposet.entity.Post;
import com.sparta.newposet.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/aai")
public class Controller {

    private final PostService postService;

    public Controller(PostService postService){
        this.postService = postService;
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
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto){
        return postService.createPost(postRequestDto);
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
