package com.unipost.board.controller;

import com.unipost.board.Repository.PersonRepository;
import com.unipost.board.domain.Person;
import com.unipost.board.dto.PersonSaveRequestDto;
import com.unipost.board.dto.PersonUpdateRequestDto;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PersonRepository personRepository;

    @After
    public void after(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("등록")
    public void create(){
        String title = "테스트 제목";
        String content = "테스트 내용";
        PersonSaveRequestDto personSaveRequestDto = PersonSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/data/board";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, personSaveRequestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Person>  personList = personRepository.findAll();
        assertThat(personList.get(0).getTitle()).isEqualTo(title);
        assertThat(personList.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("수정")
    void update(){
        Person savePerson = personRepository.save(Person.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savePerson.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";
        PersonUpdateRequestDto requestDto = PersonUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/data/board/" + updateId;

        HttpEntity<PersonUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Person> personList = personRepository.findAll();
        assertThat(personList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(personList.get(0).getContent()).isEqualTo(expectedContent);
    }
}