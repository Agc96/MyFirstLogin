package pe.edu.pucp.a20190000.myfirstlogin.features.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import pe.edu.pucp.a20190000.myfirstlogin.R;
import pe.edu.pucp.a20190000.myfirstlogin.data.api.ApiAdapter;
import pe.edu.pucp.a20190000.myfirstlogin.data.api.in.LoginInRO;
import pe.edu.pucp.a20190000.myfirstlogin.data.api.out.UserOutRO;
import pe.edu.pucp.a20190000.myfirstlogin.data.db.entities.User;
import pe.edu.pucp.a20190000.myfirstlogin.utils.Utilities;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView view;
    private String username;
    private String password;

    public LoginPresenter(ILoginView view) {
        this.view = view;
        this.username = null;
        this.password = null;
    }

    public void loginRest(String username, String password) {
        // Verificar que los datos sean correctos
        if (!verifyLoginData(username, password)) return;
        this.password = password;
        this.username = username;

        LoginInRO loginInRO = new LoginInRO(ApiAdapter.APPLICATION_NAME, username, password);
        Call<UserOutRO> call = ApiAdapter.getInstance().login(loginInRO);
        call.enqueue(new Callback<UserOutRO>() {
            @Override
            public void onResponse(@NonNull Call<UserOutRO> call, @NonNull Response<UserOutRO> response) {
                processUserResponse(response);
            }
            @Override
            public void onFailure(@NonNull Call<UserOutRO> call, @NonNull Throwable t) {
                view.askForLoginOffline();
            }
        });
    }

    private boolean verifyLoginData(String username, String password) {
        if (username.isEmpty()) {
            Utilities.showMessage(view.getContext(), R.string.login_msg_username_empty);
            return false;
        }
        if (password.isEmpty()) {
            Utilities.showMessage(view.getContext(), R.string.login_msg_password_empty);
            return false;
        }
        return true;
    }

    private void processUserResponse(Response<UserOutRO> response) {
        // Verificar respuesta del servidor REST
        Pair<UserOutRO, String> result = validateResponse(response);
        if (result.first == null) {
            // Mostramos el mensaje de error
            view.showErrorDialog(result.second);
            // Limpiamos los datos ingresados
            this.username = null;
            this.password = null;
        } else {
            // Obtener el objeto JSON
            UserOutRO userOutRO = result.first;
            // Guardar los datos del usuario en la base de datos
            int userId = userOutRO.getUserId();
            String names = userOutRO.getFullName();
            String email = userOutRO.getEmail();
            User user = new User(userId, names, email, username, password);
            new UserSaveTask(view, user).execute();
            // Ir a la pantalla de bienvenida
            view.goToHomePage(names, email);
        }
    }

    private Pair<UserOutRO, String> validateResponse(Response<UserOutRO> response) {
        Context context = view.getContext();
        String message;
        // Verificar que la respuesta es satisfactoria
        if (!response.isSuccessful()) {
            message = Utilities.formatString(context, R.string.api_dlg_error_msg_http, response.code());
        } else {
            UserOutRO userOutRO = response.body();
            // Verificar el contenido de la respuesta en JSON
            if (userOutRO == null) {
                message = Utilities.formatString(context, R.string.api_dlg_error_msg_empty);
            } else {
                // Verificar que la respuesta no indique un error
                int errorCode = userOutRO.getErrorCode();
                if (errorCode == 0) {
                    return new Pair<>(userOutRO, null);
                }
                // Verificar que hay mensaje de error
                message = userOutRO.getMessage();
                if (message == null || message.isEmpty()) {
                    message = Utilities.formatString(context, R.string.api_dlg_error_msg_rest, errorCode);
                }
            }
        }
        return new Pair<>(null, message);
    }

}
