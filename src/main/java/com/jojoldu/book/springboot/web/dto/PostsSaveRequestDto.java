package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() { // 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안 됨
                                //  Entity 클래스는 ** 데이터베이스와 맞닿는 핵심 클래스 **
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
