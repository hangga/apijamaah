package id.muhtadien.apijamaah.responses;

public class CommonResponse<T> {
    private int status;
    private String message;
    private T data;

    public CommonResponse(String message, int status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public CommonResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
