package com.mini.quizgame.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "score_game")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScoreGame {

    @Id
    @Column(name = "score_game_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "score", nullable = false)
    private Integer score;
}
