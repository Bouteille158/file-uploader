package de.bouteille93.file_uploader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bouteille93.file_uploader.interfaces.FileRegistrationService;
import de.bouteille93.file_uploader.models.FileInfo;
import de.bouteille93.file_uploader.repositories.FileInfoRepository;

@Component
public class FileRegistrationServiceImpl implements FileRegistrationService {
    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Override
    public void saveFileToDatabase(FileInfo fileInfo) {
        System.out.println("Saving file in database");
        fileInfoRepository.save(fileInfo);
    }

    @Override
    public FileInfo[] getFileListFromDatabase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileListFromDatabase'");
    }

    @Override
    public FileInfo getFileInfoFromDatabase(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileInfoFromDatabase'");
    }
}
