package pe.edu.pucp.a20190000.myfirstlogin.data.api.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import pe.edu.pucp.a20190000.myfirstlogin.data.api.base.BaseInRO;

@JsonRootName("loginInRO")
public class LoginInRO extends BaseInRO {

    private String username;
    private String password;

    @JsonCreator
    public LoginInRO(@JsonProperty("applicationName") String applicationName,
                     @JsonProperty("username") String username,
                     @JsonProperty("password") String password) {
        super(applicationName);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
