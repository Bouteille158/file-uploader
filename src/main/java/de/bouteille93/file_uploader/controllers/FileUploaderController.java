package de.bouteille93.file_uploader.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.interfaces.StorageServiceSelector;
import de.bouteille93.file_uploader.models.DefaultResponse;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class FileUploaderController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploaderController.class);

    private final StorageServiceSelector storageServiceSelector;

    public FileUploaderController(StorageServiceSelector storageServiceSelector) {
        this.storageServiceSelector = storageServiceSelector;
    }

    @GetMapping("/")
    public ResponseEntity<String> defaultRoute() {
        return ResponseEntity.ok("File upload application launched !");
    }

    @PostMapping("/upload")
    public ResponseEntity<DefaultResponse> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("storageType") String storageType) throws IOException {

        // TODO check for multiple files in param
        // TODO maximum upload size
        String uid = UUID.randomUUID().toString();

        StorageInterface storage = storageServiceSelector.selectStorage(storageType);

        FileData fileToUpload = new FileData(
                new FileInfo(
                        uid,
                        file.getOriginalFilename(),
                        file.getSize(),
                        file.getContentType(),
                        LocalDateTime.now(),
                        "",
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

        DefaultResponse response = new DefaultResponse("Fichier reçu : " + file.getOriginalFilename());

        return ResponseEntity.ok(response);
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

    @GetMapping("/download/{fileId:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String fileId) {
        logger.info("Downloading file : " + fileId);

        FileInfo fileInfo = fileRegistrationServiceImpl.getFileInfoFromDatabase(fileId);

        logger.info("File found : " + fileInfo);

        StorageInterface storage = storageServiceSelector.selectStorage(fileInfo.getStorageType());

        logger.info("Getting file bytes");

        FileData fileData = storage.download(fileInfo);

        try {
            byte[] fileContent = fileData.getData();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileInfo.getName() + "\"")
                    .body(fileContent);
        } catch (Exception e) {
            logger.error("Erreur lors du téléchargement du fichier : " + fileId, e);
            throw e;
        }

    }

    @GetMapping("/remove/{fileId:.+}")
    public ResponseEntity<?> removeFile(@PathVariable String fileId) {
        class RemoveResponse extends DefaultResponse {

            public RemoveResponse(String message) {
                super(message);
            }

            private FileInfo fileInfo;

            public void setFileInfo(FileInfo fileInfo) {
                this.fileInfo = fileInfo;
            }

            public FileInfo getFileInfo() {
                return fileInfo;
            }

        }
        RemoveResponse response = new RemoveResponse("Fichier supprimé : " + fileId);

        FileInfo fileInfo = fileRegistrationServiceImpl.getFileInfoFromDatabase(fileId);

        logger.info("File to remove found : " + fileInfo);

        response.setFileInfo(fileInfo);

        logger.info(response.getFileInfo().toString());

        StorageInterface storage = storageServiceSelector.selectStorage(fileInfo.getStorageType());

        try {
            response.setMessage(storage.remove(fileInfo));
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du fichier : " + fileId, e);
            throw e;
        }
    }

}
