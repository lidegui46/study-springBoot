package ldg.study.springboot.mvc.dto;


import java.io.PrintWriter;
import java.io.StringWriter;

public class ApiErrorResponse {

    private int code;
    private String message;
    private String[] exception;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getException() {
        return exception;
    }

    public void setException(String[] exception) {
        this.exception = exception;
    }

    public ApiErrorResponse(int code, String message, Exception exception) {

        this.code = code;
        this.message = message;

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        pw.close();
        this.exception = sw.toString().split("\n");
    }
}
