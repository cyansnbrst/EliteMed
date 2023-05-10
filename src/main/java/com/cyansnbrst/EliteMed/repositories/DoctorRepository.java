package com.cyansnbrst.EliteMed.repositories;

import com.cyansnbrst.EliteMed.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor<Doctor> {
    boolean existsByName(String name);
}
