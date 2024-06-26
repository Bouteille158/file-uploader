package de.bouteille93.file_uploader.services;

import org.springframework.stereotype.Component;

import de.bouteille93.file_uploader.interfaces.StorageInterface;
import de.bouteille93.file_uploader.interfaces.StorageServiceSelector;

@Component
public class StorageServiceSelectorImpl implements StorageServiceSelector {

    private final LocalStorageService localStorageService;
    private final CloudStorageService cloudStorageService;

    public StorageServiceSelectorImpl(
            LocalStorageService localStorageService,
            CloudStorageService cloudStorageService) {
        this.localStorageService = localStorageService;
        this.cloudStorageService = cloudStorageService;
    }

    @Override
    public StorageInterface selectStorage(String param) {
        if ("cloud".equals(param)) {
            return cloudStorageService;
        } else {
            return localStorageService;
        }
    }
}
