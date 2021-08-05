package com.jojoldu.book.springboot.web;


import com.jojoldu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)//스프링 실행자로, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    // 웹 API를 테스트할 때 사용, 스프링 MVC 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))//MockMvc를 통해 /hello 주소로 HTTP HET 요청
                .andExpect(status().isOk())//mvc.perform의 결과를 검증 / 200,404,500등의 상태를 검증 그 중 200을 검증
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto").param("name", name)
                        .param("amount", String.valueOf(amount)))
                        // param : API 테스트할 때 사용될 요청 파라미터를 설정하며 값은 String만 허용(숫자/날짜 등의 데이터 등록시 문자열로 변경)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                            //  jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드로, $를 기준으로 필드명을 명시
    }
}
