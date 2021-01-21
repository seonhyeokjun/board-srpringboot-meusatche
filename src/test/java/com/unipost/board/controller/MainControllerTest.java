package com.unipost.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("메인 페이지 로딩")
    public void mainPageLoading(){
        String body = this.testRestTemplate.getForObject("/", String.class);

        assertThat(body).contains("board practice");
    }
}