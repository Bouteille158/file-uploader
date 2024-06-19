package de.bouteille93.file_uploader.models;

public class FileData {
    private FileInfo fileInfo;
    private byte[] data;

    public FileData(FileInfo fileInfo, byte[] data) {
        this.fileInfo = fileInfo;
        this.data = data;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
