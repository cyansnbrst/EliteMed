package com.cyansnbrst.pr15.services;

import com.cyansnbrst.pr15.entities.GameAuthor;
import com.cyansnbrst.pr15.repositories.GameAuthorRepository;
import com.cyansnbrst.pr15.specifications.GameAuthorSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GameAuthorService {
    private final GameAuthorRepository gameAuthorRepository;
    private final EmailService emailService;

    @Autowired
    public GameAuthorService(GameAuthorRepository gameAuthorRepository, EmailService emailService) {
        this.gameAuthorRepository = gameAuthorRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void addGameAuthor(GameAuthor gameAuthor) {
        gameAuthorRepository.save(gameAuthor);
        log.info("Game author {} was added", gameAuthor);
        emailService.sendMessage("litra1122@gmail.com", "Game author was added to the database", "id=" + gameAuthor.getId());
    }

    @Transactional
    public GameAuthor getGameAuthor(String nickname) {
        return gameAuthorRepository.findByNickname(nickname);
    }

    @Transactional
    public void deleteGameAuthor(String nickname) {
        GameAuthor gameAuthor = gameAuthorRepository.findByNickname(nickname);
        if (gameAuthor != null) {
            log.info("Game author {} was deleted", gameAuthor);
            gameAuthorRepository.delete(gameAuthor);
            emailService.sendMessage("litra1122@gmail.com", "Game author was deleted from the database", "id=" + gameAuthor.getId());
        }
    }

    @Transactional
    public List<GameAuthor> getAllGameAuthors() {
        log.info("The list of game authors was displayed");
        return gameAuthorRepository.findAll();
    }

    @Transactional
    public boolean existsGameAuthor(String nickname) {
        return gameAuthorRepository.existsByNickname(nickname);
    }

    @Transactional
    public List<GameAuthor> findGameAuthors(String criteria) {
        log.info("Filtering by the line {} was performed", criteria);
        return gameAuthorRepository.findAll(new GameAuthorSpecification(criteria));
    }
}
