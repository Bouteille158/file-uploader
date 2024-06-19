package de.bouteille93.file_uploader.services;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.models.FileData;

public class LocalStorageService implements StorageInterface {
    @Override
    public String upload(FileData file) {

        // save file to local storage
        System.out.println("File saved to local storage");

        return file.getFileInfo().getUrl();

    }

    @Override
    public FileData download(String attachment_id) {
        return null;
    }
}
