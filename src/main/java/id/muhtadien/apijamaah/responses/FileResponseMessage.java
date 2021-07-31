package id.muhtadien.apijamaah.responses;

public class FileResponseMessage {
    private String message;

    public FileResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
