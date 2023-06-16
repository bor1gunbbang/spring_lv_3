package com.sparta.newposet.dto;

import com.sparta.newposet.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String username;
    private String contents;
//    private Double password;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.username = post.getUsername();
        this.contents = post.getContents();
//        this.password = post.getPassword();
        this.title = post.getTitle();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }


}
