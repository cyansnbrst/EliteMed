package com.cyansnbrst.EliteMed.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "appointments")
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor")
    private Doctor doctor;

    @Column(name = "date")
    private String date;

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient='" + patient +
                ", doctor='" + doctor +
                ", date='" + date +
                '}';
    }
}
