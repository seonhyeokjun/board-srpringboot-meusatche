package com.unipost.board.dto;

import com.unipost.board.domain.Person;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PersonListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PersonListResponseDto(Person person){
        this.id = person.getId();
        this.title = person.getTitle();
        this.author = person.getAuthor();
        this.modifiedDate = person.getModifiedDate();
    }

}
