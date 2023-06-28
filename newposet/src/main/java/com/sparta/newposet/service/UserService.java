package com.sparta.newposet.service;

import com.sparta.newposet.dto.LoginRequestDto;
import com.sparta.newposet.dto.SignupRequestDto;
import com.sparta.newposet.entity.User;
import com.sparta.newposet.entity.UserRoleEnum;
import com.sparta.newposet.jwt.JwtUtil;
import com.sparta.newposet.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC"; //어드민 토큰
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
    // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password,role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password  = requestDto.getPassword();
        //사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(// 유저네임을 확인한후 있으면 트루를 리턴
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        //비밀번호 확인

        if (!passwordEncoder.matches(password,user.getPassword())){ //비번이 틀리면 밑에가 실행
            throw new IllegalArgumentException("비밀번호가 일치 하지 않습니다.");
        }
        // 인증 완료 JWT생성 후 쿠키에 저장 후 Response객체에 추가
        String token = jwtUtil.createToken(user.getUsername(), user.getRole());
        jwtUtil.addJwtToCookie(token,res);
    }
}