# SPRING
  
q. 수정 삭제 api의 request를 어떤 방식으로 사용하였나.
a. body방식으로 html을 통해서 반환하는 것이아닌 스트링문자열을 반환하도록 하였습니다.

q. 어떤 상황에 어떤 방식의 request 를 써야하나
a.
q. RESTful한 API를 설계하였나 어떤 부분이 그러한가 어떤부분이 그러하지 않나
a. 게시글 작성 = post 게시글 조회 = get  게시글 수정= put  게시글 삭제 = delete 각각 필요한 메서드로 구분한 후에 
/posts 란 api로 통일하여 조회에는 조회에 필요한 {id}를 뒤에 붙여줌으로 써 구분하였습니다. 
q. 적절한 관심사 분리를 적용하였나.
a. 적용하지 못하였습니다. Controller와 Repository Service의 개념과 해당 클래스들이 담당하는 기능들의 개념만 이해하였고
실제 코드에 적용하지 못하였습니다.
