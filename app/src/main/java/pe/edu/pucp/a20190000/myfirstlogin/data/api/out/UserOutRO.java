package pe.edu.pucp.a20190000.myfirstlogin.data.api.out;

import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.a20190000.myfirstlogin.data.api.base.BaseOutRO;

@JsonRootName("userOutRO")
public class UserOutRO extends BaseOutRO {

    private int userId;
    private String fullName;
    private String email;

    public UserOutRO(int errorCode, String message, int userId, String fullName, String email) {
        super(errorCode, message);
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
