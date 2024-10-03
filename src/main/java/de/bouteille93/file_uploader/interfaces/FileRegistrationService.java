package de.bouteille93.file_uploader.interfaces;

import java.util.List;

import de.bouteille93.file_uploader.models.FileInfo;

public interface FileRegistrationService {
    public void saveFileToDatabase(FileInfo fileInfo);

    public List<FileInfo> getFileListFromDatabase();

    public FileInfo getFileInfoFromDatabase(String id);

    public String removeFileInfoFromDatabase(String id);
}
