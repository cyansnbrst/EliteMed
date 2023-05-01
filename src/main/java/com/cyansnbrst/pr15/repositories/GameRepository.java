package com.cyansnbrst.pr15.repositories;

import com.cyansnbrst.pr15.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>, JpaSpecificationExecutor<Game> {
    Game findByName(String name);
    boolean existsByName(String name);
}
