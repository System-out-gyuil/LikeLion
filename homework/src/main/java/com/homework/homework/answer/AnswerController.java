package com.homework.homework.answer;

import com.homework.homework.question.Question;
import com.homework.homework.question.QuestionService;
import com.homework.homework.user.SiteUser;
import com.homework.homework.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller // 해당 클래스가 컨트롤러임을 나타낸다.
@RequiredArgsConstructor // lombok의 어노테이션이며, final로 선언된 변수, @NotNull이 붙은 필드의 생성자를 자동으로 생성해준다.
@RequestMapping("/answer") // 해당 컨트롤러의 기본 URL을 설정한다.
public class AnswerController {
    // 서비스 객체를 final로 선언하여 RequiredArgsConstructor 어노테이션을 사용하여 생성자를 자동으로 생성한다.
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()") // 해당 메소드에 접근하기 위한 권한을 설정한다. ex) 로그인 된 사람인지 여부 등
    @PostMapping("/create/{id}") // post 방식으로 요청이 들어왔을 때의 URL을 설정한다.
    public String createAnswer(
            Model model, // 뷰에 전달할 데이터를 담는 객체
            @PathVariable("id") Integer id, // URL에서 {id}로 받은 값을 매개변수로 받는다.
            @Valid AnswerForm answerForm, // AnswerForm 객체를 받고 Valid 어노테이션으로 유효성 검사를 한다.
            BindingResult bindingResult, // 유효성 검사 결과를 담는 객체
            Principal principal // 로그인한 사용자의 정보를 담는 객체
    ) {
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        // bindingResult에 에러가 담겼는지 확인한다. (에러가 있으면 True)
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        Answer answer = this.answerService.create(question,
                answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, BindingResult bindingResult,
                               @PathVariable("id") Integer id, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }

        Answer answer = this.answerService.getAnswer(id);

        // 로그인된 name과 답변의 name이 다르면 수정할 수 없다. (본인 확인)
        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.answerService.modify(answer, answerForm.getContent());

        // 수정에 성공하면 해당 질문의 상세 페이지로 리다이랙트.
        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);

        if (!answer.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        this.answerService.delete(answer);

        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
        Answer answer = this.answerService.getAnswer(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());

        this.answerService.vote(answer, siteUser);

        return String.format("redirect:/question/detail/%s#answer_%s",
                answer.getQuestion().getId(), answer.getId());
    }
}
