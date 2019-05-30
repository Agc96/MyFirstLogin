package pe.edu.pucp.a20190000.myfirstlogin.features.base;

import android.content.Context;

public interface IView {
    Context getApplicationContext();
    void onDestroy();
}
