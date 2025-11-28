package com.mini.quizgame.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "quiz_detail")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuizDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;
}
