package com.cyansnbrst.pr15.services;

import com.cyansnbrst.pr15.entities.Game;
import com.cyansnbrst.pr15.repositories.GameRepository;
import com.cyansnbrst.pr15.specifications.GameSpecification;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GameService {
    private final GameRepository gameRepository;
    private final EmailService emailService;

    @Autowired
    public GameService(GameRepository gameRepository, EmailService emailService) {
        this.gameRepository = gameRepository;
        this.emailService = emailService;
    }

    @Transactional
    public void addGame(Game game) {
        gameRepository.save(game);
        log.info("Game {} was added", game);
        emailService.sendMessage("litra1122@gmail.com", "Game was added to the database", "id=" + game.getId());
    }

    @Transactional
    public Game getGame(String name) {
        return gameRepository.findByName(name);
    }

    @Transactional
    public void deleteGame(String name) {
        Game game = gameRepository.findByName(name);
        if (game != null) {
            log.info("Game {} was deleted", game);
            gameRepository.delete(game);
            emailService.sendMessage("litra1122@gmail.com", "Game was deleted from the database", "id=" + game.getId());
        }
    }

    @Transactional
    public List<Game> getAllGames() {
        log.info("The list of games was displayed");
        return gameRepository.findAll();
    }

    @Transactional
    public boolean existsGame(String name) {
        return gameRepository.existsByName(name);
    }

    @Transactional
    public List<Game> findGames(String criteria) {
        log.info("Filtering by the line {} was performed", criteria);
        return gameRepository.findAll(new GameSpecification(criteria));
    }
}
