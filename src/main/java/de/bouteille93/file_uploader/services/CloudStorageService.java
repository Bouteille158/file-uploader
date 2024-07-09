package de.bouteille93.file_uploader.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.models.FileData;
import de.bouteille93.file_uploader.models.FileInfo;

@Service
public class CloudStorageService implements StorageInterface {

    private static final Logger logger = LoggerFactory.getLogger(LocalStorageService.class);

    @Override
    public String upload(FileData file) {
        System.out.println("Upload using cloud storage");
        // TODO Store file in cloud storage service

        return "";
    }

    @Override
    public FileData download(FileInfo fileInfo) {
        System.out.println("Download using cloud storage");
        // TODO Generate download link

        logger.info("Executing cloud download method");

        return null;
    }
}
