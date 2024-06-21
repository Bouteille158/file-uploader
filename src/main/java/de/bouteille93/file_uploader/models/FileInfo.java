package de.bouteille93.file_uploader.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class FileInfo {
    private String id;
    private String name;
    private long size;
    private String type;
    private LocalDateTime uploadDate;
    private String url;

    public FileInfo(String id, String name, long size, String type, LocalDateTime uploadDate, String url) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.uploadDate = uploadDate;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", uploadDate=" + uploadDate +
                ", url='" + url + '\'' +
                '}';
    }
}