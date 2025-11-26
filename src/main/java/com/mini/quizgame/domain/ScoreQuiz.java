package com.mini.quizgame.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "score_quiz")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScoreQuiz {

    @Id
    @Column(name = "score_quiz_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "answer_id", nullable = false)
    private SubmittedAnswer submittedAnswer;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "score", nullable = false)
    private Integer score;
}
