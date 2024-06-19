package de.bouteille93.file_uploader.interfaces;

import de.bouteille93.file_uploader.models.FileData;

public interface StorageInterface {
    public String upload(FileData file);

    public FileData download(String attachment_id);
}
