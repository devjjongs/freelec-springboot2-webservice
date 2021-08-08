package com.jojoldu.book.springboot.domain.posts;

//롬복은 코드를 단순화시켜 주지만 필수 어노테이션은 아니다

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 6 : lombok의 어노테이션, 클래스 내 모든 필드의 Getter 메소드를 자동 생성
@NoArgsConstructor // 5 : lombok의 어노테이션, 기본 생성자 자동 추가, public Posts(){}와 같은 효과
@Entity // 1 : JPA의 어노테이션, 테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스커어 네이밍(_)으로 테이블 이름을 매칭
public class Posts extends BaseTimeEntity {

    @Id // 2 : 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 3 : PK의 생성 규칙을 나타냄, 스프링 부트 2.0 에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다
    private Long id;

    @Column(length = 500, nullable = false)
    // 4 : 테이블의 컬럼을 나타내며 굳이 선언하지 안더라도 해당 클래스의 필드는 모두 컬럼이된다, 기본값 이외 추가로 변경이 필요한 옵션이 있으면 사용, 문자열 타입을 변경하고 싶은 등의 경우에 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    // columnDefinition : SQL 테이블 정의에서 기본값을 설정하기 위해 columnDefinition 매개변수를 설정
    private String content;

    private String author;

    @Builder // 7 : 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함7
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
