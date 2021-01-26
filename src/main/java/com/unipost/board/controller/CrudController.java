package com.unipost.board.controller;

import com.unipost.board.dto.PersonResponseDto;
import com.unipost.board.dto.PersonSaveRequestDto;
import com.unipost.board.dto.PersonUpdateRequestDto;
import com.unipost.board.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CrudController {

    private final PersonService personService;

    // 저장
    @PostMapping("/data/board")
    public Long save(@RequestBody PersonSaveRequestDto personSaveRequestDto){
        return personService.save(personSaveRequestDto);
    }

    // 조회
    @GetMapping("/data/board/{id}")
    public PersonResponseDto findById(@PathVariable Long id){
        return personService.findById(id);
    }

    // 수정
    @PutMapping("/data/board/{id}")
    public Long update(@PathVariable Long id, @RequestBody PersonUpdateRequestDto personUpdateRequestDto){
        return personService.update(id, personUpdateRequestDto);
    }

    // 삭제
    @DeleteMapping("/data/board/{id}")
    public Long delete(@PathVariable Long id) {
        personService.delete(id);
        return id;
    }

}