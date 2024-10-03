package de.bouteille93.file_uploader.interfaces;

import de.bouteille93.file_uploader.models.FileData;
import de.bouteille93.file_uploader.models.FileInfo;

public interface StorageInterface {
    public String upload(FileData file);

    public FileData download(FileInfo fileInfo);

    public String remove(FileInfo fileInfo);
}
