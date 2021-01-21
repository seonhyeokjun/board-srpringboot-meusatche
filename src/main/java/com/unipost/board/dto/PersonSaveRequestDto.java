package com.unipost.board.dto;

import com.unipost.board.domain.Person;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonSaveRequestDto {

    private String title;

    private String content;

    private String author;

    @Builder
    public PersonSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Person toEntity(){
        return Person.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
