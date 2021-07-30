package id.muhtadien.apijamaah.responses;

import org.springframework.stereotype.Component;

@Component
public class CommonResponseGenerator<T> {

    public <T> CommonResponse<T> successResponse(T data, String message){
        CommonResponse commonResponse = new CommonResponse<>();
        commonResponse.setData(data);
        commonResponse.setMessage(message);
        commonResponse.setStatus(200);
        return commonResponse;
    }
}
