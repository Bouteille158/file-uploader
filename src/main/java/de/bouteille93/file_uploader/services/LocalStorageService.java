package de.bouteille93.file_uploader.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.models.FileData;
import de.bouteille93.file_uploader.models.FileInfo;

@Service
public class LocalStorageService implements StorageInterface {
    @Autowired
    private FileRegistrationServiceImpl fileRegistrationServiceImpl;

    @Override
    public String upload(FileData file) {

        // TODO Generate download link
        file.getFileInfo().setUrl("uploads/" + file.getFileInfo().getId());

        file.getFileInfo().setStorageType("local");

        FileInfo fileInfo = file.getFileInfo();
        try {
            fileRegistrationServiceImpl.saveFileToDatabase(fileInfo);
        } catch (Exception e) {
            System.err.println("Erreur lors de la sauvegarde du fichier dans la BDD");
            throw e;
        }

        try {
            // Définir le chemin où le fichier sera enregistré
            Path path = Paths.get("uploads/" + file.getFileInfo().getId());

            // Vérifier si le répertoire existe
            if (!Files.exists(path.getParent())) {
                // Si le répertoire n'existe pas, le créer
                Files.createDirectories(path.getParent());
            }

            // Écrire le fichier sur le disque
            Files.write(path, file.getData());

            return file.getFileInfo().getUrl();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

    }

    @Override
    public FileData download(String attachment_id) {
        // TODO Generate download link

        return null;
    }
}
