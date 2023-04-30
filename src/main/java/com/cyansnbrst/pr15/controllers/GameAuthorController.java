package com.cyansnbrst.pr15.controllers;

import com.cyansnbrst.pr15.entities.Game;
import com.cyansnbrst.pr15.entities.GameAuthor;
import com.cyansnbrst.pr15.repositories.GameAuthorRepository;
import com.cyansnbrst.pr15.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/game_author")
public class GameAuthorController {

    private final GameAuthorRepository gameAuthorRepository;

    @GetMapping("/list")
    public ResponseEntity<List<GameAuthor>> getAllGameAuthors() {
        List<GameAuthor> authors = gameAuthorRepository.findAll();
        if (!authors.isEmpty()) {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<GameAuthor> addGameAuthor(@RequestBody GameAuthor gameAuthor) {
        GameAuthor savedGameAuthor = gameAuthorRepository.save(gameAuthor);
        return new ResponseEntity<>(savedGameAuthor, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGameAuthor(@PathVariable("id") Integer id) {
        gameAuthorRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
