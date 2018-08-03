package ldg.study.springboot.tools.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Jim Gong(gongjian_xm@163.com）
 * @since 2017.08.21
 */
public final class Response {

    private Integer code;

    private String message;

    private Object data;

    private Response() {
        // do nothing
    }

    public static ResponseEntity<Response> success(Object data) {
        Response response = new Response();
        response.code = 0;
        response.data = data;
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    public static ResponseEntity<Response> error(Exception exception) {
        Response response = new Response();
        response.code = 1;
        response.message = exception.getMessage();
        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}
