package com.cyansnbrst.pr15.repositories;

import com.cyansnbrst.pr15.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor<Doctor> {
    Doctor findByName(String name);
    boolean existsByName(String name);
}
