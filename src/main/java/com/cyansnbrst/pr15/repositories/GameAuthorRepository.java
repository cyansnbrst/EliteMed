package com.cyansnbrst.pr15.repositories;

import com.cyansnbrst.pr15.entities.GameAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameAuthorRepository extends JpaRepository<GameAuthor, Integer>, JpaSpecificationExecutor<GameAuthor> {
    GameAuthor findByNickname(String nickname);
    boolean existsByNickname(String nickname);
}
