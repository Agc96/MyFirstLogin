package pe.edu.pucp.a20190000.myfirstlogin.data.api.base;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("baseOutRO")
public class BaseOutRO {

    private int errorCode;
    private String message;

    public BaseOutRO(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
