package com.example.appandroid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextInputLayout user, password;
    String username, passworduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //llamando a los id del login_activity
        btnLogin = findViewById(R.id.btnIngresar);
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        //obteniendo el valor de los edittext
         username =  user.getEditText().getText().toString();
         passworduser =  password.getEditText().getText().toString();

        //ruta del servidor remoto donde se encuentra el archivo
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario("https://androidappprueba.000webhostapp.com/login.php");
            }
        });
    }

    private void validarUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    if (username.equals("") || passworduser.equals("")){
                        Toast.makeText(LoginActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT);
                    }else{
                        JSONObject jsonObject = new JSONObject(response);
                    }


                }catch (JSONException e){
                    e.printStackTrace();
                }
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(),ListEmploye.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("usuario", username);
                parametros.put("password", passworduser);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
