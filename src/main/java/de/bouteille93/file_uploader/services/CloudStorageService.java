package de.bouteille93.file_uploader.services;

import org.springframework.stereotype.Service;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.models.FileData;

@Service
public class CloudStorageService implements StorageInterface {

    @Override
    public String upload(FileData file) {
        System.out.println("Upload using cloud storage");
        // TODO Store file in cloud storage service

        return "";
    }

    @Override
    public FileData download(String attachment_id) {
        System.out.println("Download using cloud storage");
        // TODO Generate download link

        return null;
    }
}