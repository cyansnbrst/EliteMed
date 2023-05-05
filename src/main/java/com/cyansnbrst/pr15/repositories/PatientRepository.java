package com.cyansnbrst.pr15.repositories;

import com.cyansnbrst.pr15.entities.Patient;
import com.cyansnbrst.pr15.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>, JpaSpecificationExecutor<Patient> {
    Patient findByName(String name);
    boolean existsByName(String name);
    Patient findByUserId(User userId);
}
