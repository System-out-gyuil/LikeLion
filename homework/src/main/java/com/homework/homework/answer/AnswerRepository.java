package com.homework.homework.answer;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository를 상속받아서 다양한 메소드를 사용할 수 있게함
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
