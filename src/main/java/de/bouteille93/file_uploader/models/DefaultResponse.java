package de.bouteille93.file_uploader.models;

public class DefaultResponse {
    private String message;

    public DefaultResponse(String message) {
        this.message = message;
    }

    // Getter
    public String getMessage() {
        return message;
    }

    // Setter
    public void setMessage(String message) {
        this.message = message;
    }
}
