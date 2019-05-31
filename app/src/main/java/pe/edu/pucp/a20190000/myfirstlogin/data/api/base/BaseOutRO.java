package pe.edu.pucp.a20190000.myfirstlogin.data.api.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("baseOutRO")
public class BaseOutRO {

    private int errorCode;
    private String message;

    @JsonCreator
    public BaseOutRO(@JsonProperty("errorCode") int errorCode,
                     @JsonProperty("message") String message) {
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
