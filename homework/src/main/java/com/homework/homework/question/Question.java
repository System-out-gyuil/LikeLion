package com.homework.homework.question;

import com.homework.homework.answer.Answer;
import com.homework.homework.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id // id 속성을 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increase
    private Integer id;

    @Column(length = 200) // Column 애노테이션은 열의 세부 설정이 가능
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // 1:N 관계 설정
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;
}
