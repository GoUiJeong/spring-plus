# SPRING PLUS

과제 내용
1. “@Transactional” readOnly(true, false) 읽기전용인지

2. JWT의 이해, nickname 추가하기

3. AOP의 이해 (”@After” ,”@Before”)

4. 테스트 코드  HttpStatus.BAD_REQUEST
  -  처음 코드
  `*status*().isok()`   
  실행
  기대값 : 200
  실제값 : 400
  -  수정 코드
  `*status*().isBadRequest()`

5. JPA의 이해
  - 할 일 검색 시 `weather` 조건으로도 검색할 수 있어야해요.
      - `weather` 조건은 있을 수도 있고, 없을 수도 있어요!
  - 할 일 검색 시 수정일 기준으로 기간 검색이 가능해야해요.
      - 기간의 시작과 끝 조건은 있을 수도 있고, 없을 수도 있어요!
  - JPQL을 사용하고, 쿼리 메소드명은 자유롭게 지정하되 너무 길지 않게 해주세요.
    ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/721cfb3f-4a05-4553-9398-899247ade5fa/0b4c4a5f-18a8-43b7-8955-69556254b7be/image.png)

6.JPA Cascade / cascade = CascadeType.PERSIST

7. N+1 문제 수정
  - `CommentController` 클래스의 `getComments()` API를 호출할 때 N+1 문제가 발생하고 있어요. N+1 문제란, 데이터베이스 쿼리 성능 저하를 일으키는 대표적인 문제 중 하나로, 특히 연관된 엔티티를 조회할 때 발생해요.
  - 해당 문제가 발생하지 않도록 코드를 수정해주세요.

  수정 전 코드
  ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/721cfb3f-4a05-4553-9398-899247ade5fa/bf4c49d0-2f0a-4884-888a-953483b8bf62/image.png)
  
  수정 후 코드 ( FETCH 추가 해줌)
  ![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/721cfb3f-4a05-4553-9398-899247ade5fa/a01b0d85-b4e5-43c8-bcb0-9ae771390c61/image.png)

8. QueryDSL
  ![TodoService.getTodo 메소드](https://prod-files-secure.s3.us-west-2.amazonaws.com/721cfb3f-4a05-4553-9398-899247ade5fa/21df06d6-a203-4ca0-ab92-88d1e79aad11/image.png)
TodoService.getTodo 메소드
- JPQL로 작성된 `findByIdWithUser` 를 QueryDSL로 변경합니다.
- 7번과 마찬가지로 N+1 문제가 발생하지 않도록 유의해 주세요!

**JDBC** (Java Database Connectivity) : 
JDBC는 Java에서 데이터베이스와 연결하고 쿼리를 실행할 수 있도록 해주는 표준 API
SQL 쿼리를 문자열로 직접 작성하고 실행(수동적)
- String sql = "SELECT * FROM users WHERE id = ?";

**JPA** (Java Persistence API) : 
JPA는 자바 애플리케이션에서 데이터베이스와의 상호작용을 ORM(Object-Relational Mapping) 방식으로 처리할 수 있도록 도와주는 표준 API
객체-관계  자동으로 매핑, Entity 관리

**JPQL** (Java Persistence Query Language) : 
JPQL은 JPA에서 사용하는 객체 지향 쿼리 언어입니다. SQL과 유사하지만, 데이터베이스의 테이블이 아닌 **엔티티 객체**를 대상으로 쿼리를 작성
SQL과 유사하고 앤티티 객체를 사용하면서 필드와 속성이름을 사용
- String jpql = "SELECT u FROM User u WHERE u.username = :username";
**QueryDSL** :
QueryDSL은 타입 안전한 동적 쿼리 작성을 지원하는 Java 라이브러리입니다. 주로 JPA와 함께 사용되며, **Java 코드**로 SQL-like 쿼리를 생성
쿼리를 작성할 때 타입 오류를 컴파일 시점에서 확인
조건에 따라 동적으로 쿼리를 생성하고 실행
Q타입 사용 - 엔티티 클래스마다 자동으로 생성되는 Q타입 클래스를 사용하여, SQL을 Java 코드처럼 작성
![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/721cfb3f-4a05-4553-9398-899247ade5fa/547551c3-66e4-4f89-a0e5-113aed7b8558/image.png)

고민했던 점
JPAQueryFactory를 사용할 때, @PersistenceContext로 entityManager를 주입 받아야줘야 한다.
이때, TodoRepositoryCustomImpl 에 바로 주입받아서 쓸지, 클래스를 따로 만들어서 사용하는게 좋은지 생각을 해봤다.
**단순한 애플리케이션**이나 **하나의 레포지토리에서만** 사용하는 경우, `@PersistenceContext`로 `entityManager`를 주입받고, `JPAQueryFactory`를 레포지토리에서 직접 사용하는 것이 더 간단하고 직관적이다. 여러 곳에서 `JPAQueryFactory`를 **공유하거나 재사용**하려면 **`@Configuration` 클래스를 이용해 빈으로 등록**하는 것이 더 좋고, 이 방법은 `JPAQueryFactory`를 한 곳에서 설정하고 관리할 수 있어, 코드 중복을 줄이고 **확장성**을 높일 수 있다.


