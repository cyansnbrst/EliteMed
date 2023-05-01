package com.cyansnbrst.pr15.controllers;

import com.cyansnbrst.pr15.entities.Game;
import com.cyansnbrst.pr15.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @GetMapping("/list")
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        if (!games.isEmpty()) {
            return new ResponseEntity<>(games, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        gameService.addGame(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity<Void> deleteGame(@PathVariable("name") String name) {
        gameService.deleteGame(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{criteria}")
    public ResponseEntity<List<Game>> findGames(@PathVariable("criteria") String criteria) {
        List<Game> games = gameService.findGames(criteria);
        if (!games.isEmpty()) {
            return new ResponseEntity<>(games, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
