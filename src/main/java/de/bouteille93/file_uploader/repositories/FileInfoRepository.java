package de.bouteille93.file_uploader.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.bouteille93.file_uploader.models.FileInfo;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, String> {

}
