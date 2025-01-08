package com.homework.homework.answer;

import com.homework.homework.question.Question;
import com.homework.homework.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;


// lobok 을 사용하여 getter(접근자), setter(설정자)를 자동으로 생성
@Getter
@Setter
// JPA 가 해당 클래스를 관리하여 테이블과 매핑하게 된다.
@Entity
public class Answer {

    @Id // 해당 필드가 기본키임을 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 DB에 위임함, index 를 자동으로 하나씩 늘려준다. (auto_increment)
    private Integer id;

    @Column(columnDefinition = "TEXT") // columnDefinition 은 해당 필드의 컬럼 정보를 직접 설정한다.
    private String content;

    // LocalDateTime 을 통해 날짜와 시간을 저장한다.
    private LocalDateTime createDate;

    @ManyToOne // N:1 관계 설정
    private Question question;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany // N:M 관계 설정
    Set<SiteUser> voter;
}
