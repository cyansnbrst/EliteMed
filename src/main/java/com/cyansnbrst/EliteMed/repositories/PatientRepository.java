package com.cyansnbrst.EliteMed.repositories;

import com.cyansnbrst.EliteMed.entities.Patient;
import com.cyansnbrst.EliteMed.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, JpaSpecificationExecutor<Patient> {
    Patient findByName(String name);
    boolean existsByName(String name);
    Patient findByUserId(User userId);
}
