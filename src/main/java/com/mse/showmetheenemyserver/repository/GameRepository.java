package com.mse.showmetheenemyserver.repository;

import com.mse.showmetheenemyserver.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.status = com.mse.showmetheenemyserver.domain.GameStatus.NEW")
    Optional<List<Game>> findAllNewGames();
}
