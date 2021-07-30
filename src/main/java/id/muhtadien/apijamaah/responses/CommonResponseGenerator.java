package id.muhtadien.apijamaah.responses;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class CommonResponseGenerator {

    public <T> CommonResponse successResponse(T data, String message){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(data);
        commonResponse.setMessage(message);
        commonResponse.setStatus(200);
        return commonResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public <T> CommonResponse failedResponse(String message, int status){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(null);
        commonResponse.setMessage(message);
        commonResponse.setStatus(status);
        return commonResponse;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public <T> CommonResponse emptyResponse(String message){
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(null);
        commonResponse.setMessage(message);
        commonResponse.setStatus(204);
        return commonResponse;
    }
}
