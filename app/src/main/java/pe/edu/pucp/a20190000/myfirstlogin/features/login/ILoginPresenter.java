package pe.edu.pucp.a20190000.myfirstlogin.features.login;

import pe.edu.pucp.a20190000.myfirstlogin.features.base.IPresenter;

public interface ILoginPresenter extends IPresenter {
    void loginRest(String username, String password);
    void loginOffline();
}
