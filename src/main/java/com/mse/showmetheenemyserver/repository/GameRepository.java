package com.mse.showmetheenemyserver.repository;

import com.mse.showmetheenemyserver.domain.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameRoom, String> {

}
