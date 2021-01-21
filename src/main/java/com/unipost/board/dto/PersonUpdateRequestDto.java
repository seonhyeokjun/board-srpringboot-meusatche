package com.unipost.board.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PersonUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public PersonUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
