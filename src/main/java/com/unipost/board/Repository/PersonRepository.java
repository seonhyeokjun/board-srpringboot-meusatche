package com.unipost.board.Repository;

import com.unipost.board.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p From Person p ORDER BY p.id DESC")
    List<Person> findAllDesc();

    List<Person> findByTitleContaining(String keyword);
}
