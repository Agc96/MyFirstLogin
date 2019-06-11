package pe.edu.pucp.a20190000.myfirstlogin.data.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiAdapter {

    public static final String APPLICATION_NAME = "MFL_MOBILE";
    private static final String BASE_URL = "http://192.168.1.31:8080/";
    private static final int TIMEOUT = 90; // seconds (segundos)
    private static ApiService INSTANCE;

    public static ApiService getInstance() {
        if (INSTANCE == null) {
            // Crear la instancia de OkHttp, una librería que Retrofit usa para las peticiones HTTP
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .build();
            // Crear la instancia de Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .client(httpClient)
                    .build();
            // Crear la interfaz que manejará el servicio REST
            INSTANCE = retrofit.create(ApiService.class);
        }
        return INSTANCE;
    }

}
