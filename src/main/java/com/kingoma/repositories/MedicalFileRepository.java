package com.kingoma.repositories;

import com.kingoma.entities.MedicalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalFileRepository extends JpaRepository<MedicalFile, Long> {
    MedicalFile findBymedicalFileId(String medicalFileId);

}
