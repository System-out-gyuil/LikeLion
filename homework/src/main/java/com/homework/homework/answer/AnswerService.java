package com.homework.homework.answer;

import com.homework.homework.DataNotFoundException;
import com.homework.homework.question.Question;
import com.homework.homework.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service // 해당 클래스를 서비스로 사용하겠다는 어노테이션
@RequiredArgsConstructor // 생성자 자동 생성 (lombok)
public class AnswerService {
    // 생성자 초기화
    private final AnswerRepository answerRepository;

    // 답변 생성
    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer(); // 답변 객체 생성
        answer.setContent(content); // 답변 내용 설정
        answer.setQuestion(question); // 질문 설정
        answer.setCreateDate(LocalDateTime.now()); // 생성 날짜 설정
        answer.setAuthor(author); // 작성자 설정

        // repository 의 save 메소드를 통해 저장
        this.answerRepository.save(answer);

        // 저장된 답변 리턴
        return answer;
    }

    // 답변 조회
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);

        // answer(답변)가 비워져있지 않다면 True
        if (answer.isPresent()) {
            return answer.get();

        // answer가 비워져있다면 exception 발생
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    // 답변 수정
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now()); // 수정 날짜 설정
        this.answerRepository.save(answer);
    }

    // 답변 삭제
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    // 추천하기
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}
