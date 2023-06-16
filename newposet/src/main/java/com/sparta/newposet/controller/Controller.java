package com.sparta.newposet.controller;

import com.sparta.newposet.dto.PostRequestDto;
import com.sparta.newposet.dto.PostResponseDto;
import com.sparta.newposet.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {
    Scanner scan = new Scanner(System.in);

    private final Map<Long, Post> postList = new HashMap<>();// 일단 DB역할을 함

    @PostMapping("/posts") //게시글 작성
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {


        //RequestDto -> Entity
        Post post = new Post(requestDto);
        //Post Max ID check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);
        //DB에 저장
        postList.put(post.getId(), post);
        //Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    @GetMapping("/posts")
//    @ResponseBody//html같은 파일이아니라 메서드 문자열을 반환하는것을 말한다.
    public List<PostResponseDto> getPosts() {
        //조회하기
        //Map To List
        List<PostResponseDto> responseList = postList.values().stream(). // values()내부의모든 데이터를 가져온다.  stream for문과 비슷
                map(PostResponseDto::new).toList();// new 미리 생성해둔 생성자 타입으로 만들어진다.
        return responseList;
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto choosePostView(@PathVariable Long id ) {
        //선택한 게시글 조회

//        if (postList.containsKey(id)){
//         return postList.get(id).getTitle() + " | " + postList.get(id).getContents() + " | " + postList.get(id).getUsername() + " | " + postList.get(id).getPassword();
//        }else {
//               throw new IllegalArgumentException("선택한 게시글이 없습니다.!");
//           }
        Post post = checkedId(id);

        return new PostResponseDto(post);


    }

    @PutMapping("/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        //해당 게시글이 존재하는지 확인

//        if (postList.containsKey(id)) {
//            Post post = postList.get(id);
//            System.out.println("해당 게시글의 password를 입력하세요 >");
//            Double psw = scan.nextDouble();
//            if(psw.equals(postList.get(id).getPassword())){
//                post.update(requestDto);
//                    result = "수정되었습니다.";
//            }else {
//                throw new IllegalArgumentException("페스워드가 틀립니다.");
//            }
//            post.update(requestDto);
//
//            return result;
//        } else {
//            throw new IllegalArgumentException("게시글이 없습니다.");
//        }

        checkedId(id);
        checkedPassword(id);
        Post post = postList.get(id);
        post.update(requestDto);
        return new PostResponseDto(post);
    }

    @DeleteMapping("/posts/{id}")
    public String deletePost(@PathVariable Long id) {
        //선택 게시글 삭제
//        if (postList.containsKey(id)) {
//            System.out.println("해당 게시글의 암호를 입력하세요");
//            Double psw = scan.nextDouble();
//            if (psw.equals(postList.get(id).getPassword())){
//                postList.remove(id);
//                result = "삭제되었습니다.";
//            }
//            return result;
//        } else {
//            throw new IllegalArgumentException("게시글이 없습니다.");
//        }
        checkedId(id);
        checkedPassword(id);
        postList.remove(id);
        return "해당 게시글을 삭제 하였습니다.";
    }


    public Post checkedId(Long id){
        if (!postList.containsKey(id)){
            throw new IllegalArgumentException(id + "의 게시글이 없습니다. ");
        }
        return postList.get(id);
    }
    public Post checkedPassword(Long id){
        System.out.println("해당 게시물의 암호를 입력하세요 >");
        Double psw = scan.nextDouble();
        if (!psw.equals(postList.get(id).getPassword())){
            throw  new IllegalArgumentException("암호가 틀렸습니다.");
        }
        return postList.get(id);
    }
}
