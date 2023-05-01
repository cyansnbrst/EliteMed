package com.cyansnbrst.pr15.services;


import com.cyansnbrst.pr15.entities.Game;
import com.cyansnbrst.pr15.entities.GameAuthor;
import com.cyansnbrst.pr15.repositories.GameAuthorRepository;
import com.cyansnbrst.pr15.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.jmx.export.annotation.ManagedOperation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleService {

    private final GameRepository gameRepository;
    private final GameAuthorRepository gameAuthorRepository;

    @Scheduled(fixedRate = 1800000)
    public void updateData() throws IOException {
        File dir = new File("C:\\Users\\PC\\IdeaProjects\\java-4-semester\\src\\pr15\\src\\main\\resources\\entityData");
        FileUtils.cleanDirectory(dir);
        List<Game> games = gameRepository.findAll();
        List<GameAuthor> gameAuthors = gameAuthorRepository.findAll();
        createFileForEntity(dir, games, "games");
        createFileForEntity(dir, gameAuthors, "gameAuthors");
    }

    @ManagedOperation()
    public void updateDataJmx() throws IOException {
        updateData();
    }

    private <E> void createFileForEntity(File dir, List<E> entities, String nameClass) throws IOException {
        File file = new File(dir + "\\" + nameClass + ".txt");
        FileWriter writer = new FileWriter(file);
        for (E entity : entities) {
            writer.write(entity.toString()+"\n");
        }
        writer.close();
    }
}