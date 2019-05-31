package pe.edu.pucp.a20190000.myfirstlogin.features.login;

import android.os.AsyncTask;
import java.lang.ref.WeakReference;

import pe.edu.pucp.a20190000.myfirstlogin.data.db.AppDatabase;
import pe.edu.pucp.a20190000.myfirstlogin.data.db.entities.User;

public class UserSaveTask extends AsyncTask<Void, Void, Boolean> {
    private WeakReference<ILoginView> view;
    private User user;

    protected UserSaveTask(ILoginView view, User user) {
        this.view = new WeakReference<>(view);
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // Verificar que la vista todavía está disponible
        ILoginView view = this.view.get();
        if (view == null) return false;
        // Inicializar la base de datos, si es que aún no se hizo
        AppDatabase database = AppDatabase.getInstance(view.getContext());
        if (database == null) return false;
        // Guardar los datos del usuario en la BD si no se ha guardado con anterioridad
        User exists = database.userDao().findById(user.getUserId());
        if (exists == null) {
            database.userDao().insert(user);
        }
        return true;
    }
}
