package com.sparta.newposet.controller;

import com.sparta.newposet.jwt.JwtUtil;
import com.sparta.newposet.repository.CommentRepository;
import com.sparta.newposet.service.CommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    public CommentController(CommentService commentService, CommentRepository commentRepository, JwtUtil jwtUtil) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.jwtUtil = jwtUtil;
    }

}
