package com.cyansnbrst.pr15.repositories;

import com.cyansnbrst.pr15.entities.GameAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameAuthorRepository extends JpaRepository<GameAuthor, Integer> {
}
