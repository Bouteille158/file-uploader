package de.bouteille93.file_uploader.interfaces;

import de.bouteille93.file_uploader.models.FileInfo;

public interface FileRegistrationService {
    public void saveFileToDatabase(FileInfo fileInfo);

    public FileInfo[] getFileListFromDatabase();

    public FileInfo getFileInfoFromDatabase(String id);
}
