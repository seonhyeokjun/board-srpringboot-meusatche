package com.unipost.board.service;

import com.unipost.board.Repository.PersonRepository;
import com.unipost.board.domain.Person;
import com.unipost.board.dto.PersonListResponseDto;
import com.unipost.board.dto.PersonResponseDto;
import com.unipost.board.dto.PersonSaveRequestDto;
import com.unipost.board.dto.PersonUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Transactional
    public Long save(PersonSaveRequestDto personSaveRequestDto){
        return personRepository.save(personSaveRequestDto.toEntity()).getId();
    }

    // 수정
    @Transactional
    public Long update(Long id, PersonUpdateRequestDto personUpdateRequestDto) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + id));

        person.update(personUpdateRequestDto.getTitle(), personUpdateRequestDto.getContent());

        return id;
    }

    // 조회
    public PersonResponseDto findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + id));

        return new PersonResponseDto(person);
    }

    @Transactional(readOnly = true)
    public List<PersonListResponseDto> findAllDesc(){
        return personRepository.findAllDesc().stream()
                .map(PersonListResponseDto::new)
                .collect(Collectors.toList());
    }

    // 삭제
    @Transactional
    public void delete (Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

        personRepository.delete(person);
    }
}
