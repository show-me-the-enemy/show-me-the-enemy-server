package com.mse.showmetheenemyserver.repository;

import com.mse.showmetheenemyserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    String GET_RANKINGS = "SELECT username, num_wins, max_round, " +
            "RANK() OVER (" +
            "ORDER BY " +
            "num_wins DESC," +
            "max_round DESC" + // ORDER BY 칼럼 배치에 따라 우선 순위가 달라진다.
            ") rank " +
            "FROM `user`";

    User findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query(value = GET_RANKINGS, nativeQuery = true)
    List<Object[]> findUsersByNumWinsDesc();
}