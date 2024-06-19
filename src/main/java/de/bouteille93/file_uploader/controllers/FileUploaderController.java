package de.bouteille93.file_uploader.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.models.ApiError;
import de.bouteille93.file_uploader.models.FileData;
import de.bouteille93.file_uploader.models.FileInfo;
import de.bouteille93.file_uploader.services.LocalStorageService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class FileUploaderController {

    StorageInterface storage;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        storage = new LocalStorageService();

        String uid = UUID.randomUUID().toString();

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
            ApiError apiError = new ApiError("Erreur lors de l'enregistrement du fichier");
            return ResponseEntity.badRequest().body(apiError);
        }

        System.out.println(file);

        System.out.println(fileToUpload.getFileInfo().toString());

        return ResponseEntity.ok("Fichier reçu : " + file.getOriginalFilename());
    }
}
