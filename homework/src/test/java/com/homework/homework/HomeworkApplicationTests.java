package com.homework.homework;

import com.homework.homework.answer.Answer;
import com.homework.homework.answer.AnswerRepository;
import com.homework.homework.question.Question;
import com.homework.homework.question.QuestionRepository;
import com.homework.homework.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class HomeworkApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void insertTest() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	void findAllTest() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(4, all.size());

		Question q = all.get(0);
		assertEquals("수정된 제목", q.getSubject());

		System.out.println(q.getSubject());
	}

	@Test
	void findByIdTest() {
		Optional<Question> oq = this.questionRepository.findById(1);

		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());

			System.out.println(q.getSubject());
		}
	}

	@Test
	void findBySubjectTest() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());

		System.out.println(q.getId());
	}

//	@Test
//	void findBySubjectAndContentTest() {
//		Question q = this.questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
//		assertEquals(1, q.getId());
//
//		System.out.println(q.getId());
//	}

	@Test
	void findBySubjectLikeTest() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());

		System.out.println(q.getSubject());
	}

	@Test
	void updateTest() {
		Optional<Question> oq = this.questionRepository.findById(1);

		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목");
		this.questionRepository.save(q);
	}

	@Test
	void deleteTest() {
		assertEquals(3, this.questionRepository.count());
		Optional<Question> oq = this.questionRepository.findById(3);
		assertTrue(oq.isPresent());

		Question q = oq.get();
		this.questionRepository.delete(q);
		assertEquals(2, this.questionRepository.count());
	}

	@Test
	void countTest() {
		assertEquals(3, this.questionRepository.count());
	}

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void insertToAnswerTest() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());

		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);

		this.answerRepository.save(a);
	}

	@Autowired
	private QuestionService questionService;

	@Test
	void dummyTestJpa() {
		for (int i =0; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content);
		}
	}

}
