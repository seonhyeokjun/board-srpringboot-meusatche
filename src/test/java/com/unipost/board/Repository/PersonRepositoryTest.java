package com.unipost.board.Repository;

import com.unipost.board.domain.Person;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @After
    public void after(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장")
    void board_create(){
        String title = "테스트 제목";
        String content = "테스트 내용";

        personRepository.save(Person.builder()
                .title(title)
                .content(content)
                .author("test@test.com")
                .build());

        List<Person> personList = personRepository.findAll();

        Person person = personList.get(0);
        assertThat(person.getTitle()).isEqualTo(title);
        assertThat(person.getContent()).isEqualTo(content);
    }

    @Test
    void Time_create(){
        LocalDateTime localDateTime = LocalDateTime.of(2012, 1, 21, 0, 0);
        personRepository.save(Person.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Person> personList = personRepository.findAll();

        Person person = personList.get(0);

        System.out.println("------------------------- create=" + person.getCreateDate() + ", modeifeidDate= " + person.getModifiedDate());

        assertThat(person.getCreateDate()).isAfter(localDateTime);
        assertThat(person.getModifiedDate()).isAfter(localDateTime);
    }
}