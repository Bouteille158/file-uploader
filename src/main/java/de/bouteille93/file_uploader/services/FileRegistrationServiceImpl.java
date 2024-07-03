package de.bouteille93.file_uploader.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bouteille93.file_uploader.interfaces.FileRegistrationService;
import de.bouteille93.file_uploader.models.FileInfo;
import de.bouteille93.file_uploader.repositories.FileInfoRepository;

@Component
public class FileRegistrationServiceImpl implements FileRegistrationService {
    @Autowired
    private FileInfoRepository fileInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(FileRegistrationServiceImpl.class);

    @Override
    public void saveFileToDatabase(FileInfo fileInfo) {
        logger.info("Saving file in database");
        try {
            fileInfoRepository.save(fileInfo);
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde du fichier dans la base de données", e);
            throw e;
        }
    }

    @Override
    public List<FileInfo> getFileListFromDatabase() {
        List<FileInfo> fileList = new ArrayList<>();
        try {
            fileList = fileInfoRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des info des fichiers : " + e.getMessage());
            throw e;
        }
        return fileList;
    }

    @Override
    public FileInfo getFileInfoFromDatabase(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFileInfoFromDatabase'");
    }
}
