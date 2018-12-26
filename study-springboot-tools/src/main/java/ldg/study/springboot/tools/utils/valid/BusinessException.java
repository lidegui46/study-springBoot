package ldg.study.springboot.tools.utils.valid;

import ldg.study.springboot.tools.utils.json.JsonUtil;
import org.springframework.http.HttpStatus;

/**
 * 自定义 业务异常
 *
 * @author： ldg
 * @create date： 2018/12/26
 */
public class BusinessException extends RuntimeException {
    private int errorCode;
    private Object[] arguments;

    public BusinessException(String msg, Object... args) {
        super(msg);
        this.errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.arguments = args;
    }

    public BusinessException(int code, String msg, Object... args) {
        super(msg);
        this.errorCode = code;
        this.arguments = args;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.getMessage()).append("\r\n");
        stringBuilder.append("errorCode:" + this.errorCode + "\r\n");
        stringBuilder.append("输入参数：\r\n");
        Object[] var2 = this.arguments;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            Object argument = var2[var4];
            stringBuilder.append("\t" + JsonUtil.toJson(argument) + "\r\n");
        }

        return stringBuilder.toString();
    }
}
