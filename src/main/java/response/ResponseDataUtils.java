package response;

import java.util.List;

public class ResponseDataUtils<T> {
    public ResponseDataUtils ResponseDataUtils() {
        return this;
    }

    public ResponseData dataBuilder(boolean status, String msg, T data) {
        ResponseData responseData = null;
        if (status) {
            responseData = new ResponseData("succ", "", data);
        } else {
            responseData = new ResponseData("error", msg, null);
        }
        return responseData;
    }

    public ResponseData dataBuilder(boolean status, String msg, List<T> data) {
        ResponseData responseData = null;
        if (status) {
            responseData = new ResponseData("succ", "", data);
        } else {
            responseData = new ResponseData("error", msg, null);
        }
        return responseData;
    }
}
