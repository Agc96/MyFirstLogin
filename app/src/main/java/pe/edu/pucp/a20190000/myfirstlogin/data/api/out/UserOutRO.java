package pe.edu.pucp.a20190000.myfirstlogin.data.api.out;

import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.a20190000.myfirstlogin.data.api.base.BaseOutRO;

@JsonRootName("userOutRO")
public class UserOutRO extends BaseOutRO {

    private int userId;
    private String fullname;
    private String email;

    public UserOutRO(int errorCode, String message, int userId, String fullname, String email) {
        super(errorCode, message);
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
}
