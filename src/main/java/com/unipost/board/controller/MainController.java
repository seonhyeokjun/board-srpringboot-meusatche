package com.unipost.board.controller;

import com.unipost.board.dto.PersonResponseDto;
import com.unipost.board.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PersonService personService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("person", personService.findAllDesc());

        return "index";
    }

    @GetMapping("/boardList")
    public String boardList(){
        return "boardList";
    }

    @GetMapping("/person/update/{id}")
    public String update(@PathVariable Long id, Model model){

        PersonResponseDto dto = personService.findById(id);
        model.addAttribute("person", dto);

        return "update";
    }
}
