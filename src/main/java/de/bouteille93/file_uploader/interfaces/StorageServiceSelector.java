package de.bouteille93.file_uploader.interfaces;

public interface StorageServiceSelector {
    StorageInterface selectStorage(String param);
}
