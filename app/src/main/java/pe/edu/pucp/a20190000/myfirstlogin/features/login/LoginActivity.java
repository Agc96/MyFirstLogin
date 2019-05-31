package pe.edu.pucp.a20190000.myfirstlogin.features.login;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.pucp.a20190000.myfirstlogin.R;
import pe.edu.pucp.a20190000.myfirstlogin.utils.Utilities;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private EditText mUsername;
    private EditText mPassword;
    private Button mSubmit;
    private ILoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.login_ipt_username);
        mPassword = findViewById(R.id.login_ipt_password);
        mSubmit = findViewById(R.id.login_btn_submit);
        mPresenter = new LoginPresenter(this);
    }

    public void login(View v) {
        // Esconder el teclado
        Utilities.hideKeyboard(this);

        // Obtener datos de usuario
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        // Iniciar sesión y notificar al usuario que se está iniciando sesión
        mPresenter.loginRest(username, password);
        Utilities.showMessage(this, R.string.login_msg_loading);
    }

    public void askForLoginOffline() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.login_dlg_offline_title)
                .setMessage(R.string.login_dlg_offline_msg)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.loginOffline();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}
