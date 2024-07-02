package de.bouteille93.file_uploader.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.interfaces.StorageServiceSelector;
import de.bouteille93.file_uploader.models.FileData;
import de.bouteille93.file_uploader.models.FileInfo;
import de.bouteille93.file_uploader.services.FileRegistrationServiceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class FileUploaderController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploaderController.class);

    private final StorageServiceSelector storageServiceSelector;

    public FileUploaderController(StorageServiceSelector storageServiceSelector) {
        this.storageServiceSelector = storageServiceSelector;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("storageType") String storageType) throws IOException {

        String uid = UUID.randomUUID().toString();

        StorageInterface storage = storageServiceSelector.selectStorage(storageType);

        FileData fileToUpload = new FileData(
                new FileInfo(
                        uid,
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType(),
                        LocalDateTime.now(),
                        ""),
                file.getBytes());

        try {
            storage.upload(fileToUpload);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }

        System.out.println(file);

        System.out.println(fileToUpload.getFileInfo().toString());

        return ResponseEntity.ok("Fichier reçu : " + file.getOriginalFilename());
    }

    @Autowired
    private FileRegistrationServiceImpl fileRegistrationServiceImpl;

    @GetMapping("/getAllFiles")
    public ResponseEntity<List<FileInfo>> getAllFiles() {
        List<FileInfo> fileList = new ArrayList<>();
        try {
            fileList = fileRegistrationServiceImpl.getFileListFromDatabase();
            return ResponseEntity.ok(fileList);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des info des fichiers : " + e.getMessage(), e);
            throw e;
        }
    }

}
