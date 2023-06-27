package com.sparta.newposet.entity;

import com.sparta.newposet.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false)
    private String password;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.content = postRequestDto.getContent();
        this.password = postRequestDto.getPassword();
    }

    // Setter
    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // 서비스 함수

    // 비밀번호 체크하는 함수
    public void checkPassword(String inputPassword) {
        if (!password.equals(inputPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
