package com.cyansnbrst.EliteMed.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctors")
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "position")
    private String position;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name +
                ", position='" + position +
                '}';
    }
}
