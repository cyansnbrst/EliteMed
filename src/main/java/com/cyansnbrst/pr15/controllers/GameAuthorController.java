package com.cyansnbrst.pr15.controllers;

import com.cyansnbrst.pr15.entities.GameAuthor;
import com.cyansnbrst.pr15.services.GameAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/game_author")
public class GameAuthorController {

    private final GameAuthorService gameAuthorService;

    @GetMapping("/list")
    public ResponseEntity<List<GameAuthor>> getAllGameAuthors() {
        List<GameAuthor> authors = gameAuthorService.getAllGameAuthors();
        if (!authors.isEmpty()) {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<GameAuthor> addGameAuthor(@RequestBody GameAuthor gameAuthor) {
        gameAuthorService.addGameAuthor(gameAuthor);
        return new ResponseEntity<>(gameAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{nickname}")
    public ResponseEntity<Void> deleteGameAuthor(@PathVariable("nickname") String nickname) {
        gameAuthorService.deleteGameAuthor(nickname);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{criteria}")
    public ResponseEntity<List<GameAuthor>> findGameAuthors(@PathVariable("criteria") String criteria) {
        List<GameAuthor> gameAuthors = gameAuthorService.findGameAuthors(criteria);
        if (!gameAuthors.isEmpty()) {
            return new ResponseEntity<>(gameAuthors, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
