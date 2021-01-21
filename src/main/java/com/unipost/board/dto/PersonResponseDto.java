package com.unipost.board.dto;

import com.unipost.board.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class PersonResponseDto {

    private Long id;

    private String title;

    private String content;

    private String author;

    public PersonResponseDto(Person person) {
        this.id = person.getId();
        this.title = person.getTitle();
        this.content = person.getContent();
        this.author = person.getAuthor();
    }
}
