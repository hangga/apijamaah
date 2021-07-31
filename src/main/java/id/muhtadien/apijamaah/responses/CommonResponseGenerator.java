package id.muhtadien.apijamaah.responses;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator {

    public <T> CommonResponse successResponse(T data, String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(data);
        commonResponse.setMessage(message);
        commonResponse.setStatus(200);
        return commonResponse;
    }

    public <T> CommonResponse failedResponse(String message, int status) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(null);
        commonResponse.setMessage(message);
        commonResponse.setStatus(status);
        return commonResponse;
    }

    public <T> CommonResponse emptyResponse(String message) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(null);
        commonResponse.setMessage("Tidak ditemukan!");
        commonResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return commonResponse;
    }
}
