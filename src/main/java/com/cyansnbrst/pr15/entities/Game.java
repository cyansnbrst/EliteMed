package com.cyansnbrst.pr15.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "game")
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "creation_date")
    private String creationDate;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private GameAuthor author;

    public GameAuthor getAuthor() {
        return author;
    }

    public void setAuthor(GameAuthor author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
