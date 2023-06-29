# SPRING

+++_lv2 api 명세
|api명 | method | url | ReauestHeader| Request | Response | ResponseHeader

회원가입 | POST | api/auth/signup | .. |{ "username" : "abc123","password":"abcd1234"}| "회원가입이 완료되었습니다." |

로그인   | POST | api/auth/login  | .. |{"username" : "abc123"."passwrod":"abcd1234"}| "로그인완료"|

전체 게시글 조회 | GET| api/posts| .. | .. | {"id" : 1 ,"title": "titlename", "username": "abc123", "contents": "content", "createdAt": "2023-06-29T10:00:04.032185", "modifiedAt": "2023-06-29T10:00:04.032185"}...|

선택 게시글 조회 | GET | api/posts/{id}| .. | .. |{"id" : 1 ,title": "titlename", "username": "abc123", "contents": "content", "createdAt": "2023-06-29T10:00:04.032185", "modifiedAt": "2023-06-29T10:00:04.032185"}|..|

게시글 생성 | POST | api/posts | Header:{Authorization: Bearer 토큰} | {title": "titlename", "username": "abc123", "contents": "content"} | .. |

게시글 수정 | PUT | api/posts/{id} | Header:{Authorization: Bearer 토큰} | {title": "수정한titlename", "contents": "수정한content"}| {"id" : 1 ,title": "수정titlename", "username": "abc123", "contents": "수정content", "createdAt": "2023-06-29T10:00:04.032185", "modifiedAt": "2023-06-29T10:00:04.032185"}

게시글 삭제 | DELETE | /api/posts/{id} | Header:{Authorization: Bearer 토큰} | .. |{"게시글 삭제 성공"}|

강의를 보면서 진행하였지만 post메서드들에 토큰 검증을 추가하는것이 어려웠고 제대로 작동하지도 않았다.

솔직히 제대로 구현한것인지도 모르겠다. 



+++_ lv3 api명세
댓글작성 | /api/comment | POST | Header:{Authorization: Bearer 토큰}|{"postId:1,"comment" :"댓글 1"}|{"id":1,"comment" : "댓글내용","createdAt": "2022-12-01T12:54:57.049359","modifiedAt": "2022-12-01T12:54:57.049359","username": "abc123"} | ..

댓글수정 | /api/comment/{id} | PUT | Header:{Authorization: Bearer 토큰}|{"comment" :"수정 댓글 1"}| {"id":1,"comment" : "수정된 댓글내용","createdAt": "2022-12-01T12:54:57.049359","modifiedAt": "2022-12-01T12:54:57.049359","username": "abc123"} | ..

댓글삭제 | /api/comment/{id} | DELETE | Header:{Authorization: Bearer 토큰} | .. | {"댓글 삭제 완료" }| ..

이제 시작하였다.

