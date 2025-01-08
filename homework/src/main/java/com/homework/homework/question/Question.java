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

    @Column(length = 200) // Column 에노테이션의 length 속성을 통해 길이 제한
    private String subject;

    @Column(columnDefinition = "TEXT") // TEXT 타입으로 지정
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
