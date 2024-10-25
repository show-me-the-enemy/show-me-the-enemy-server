package com.mse.showmetheenemyserver.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 완전한 객체만 생성할 수 있게 가능하다.
@DynamicInsert
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @ColumnDefault("0")
    private Integer maxRound;

    @ColumnDefault("0")
    private Integer crystal;

    @ColumnDefault("0")
    private Integer numWins;

    @Builder
    public User(Long id, String username, String password, Integer maxRound, Integer crystal, Integer numWins) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.maxRound = maxRound;
        this.crystal = crystal;
        this.numWins = numWins;
    }

    public void addNumWins(Boolean won) {
        if (won) this.numWins++;
    }

    public void addCrystals(Integer crystal, Boolean won) {
        this.crystal += (won) ? crystal : 0;
    }

    public void updateMaxRound(Integer round) {
        this.maxRound = (round > this.maxRound) ? round : this.maxRound;
    }
}
