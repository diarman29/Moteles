package com.example.diego.listarclientes2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.entidades.Cliente;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class CrearCliente extends AppCompatActivity {

    private TextView mcodigoclienteView;
    private TextView mnombreclienteView;
    private TextView mapellidoclienteView;
    private TextView mdireccionclienteView;
    private TextView mtelefonoclienteView;
    private TextView mcorreoclienteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mcodigoclienteView = (TextView) findViewById(R.id.cedula);
        mnombreclienteView = (TextView) findViewById(R.id.nombre);
        mapellidoclienteView = (TextView) findViewById(R.id.apellido);
        mdireccionclienteView = (TextView) findViewById(R.id.direccion);
        mtelefonoclienteView = (TextView) findViewById(R.id.telefono);
        mcorreoclienteView = (TextView) findViewById(R.id.correo);

        mcodigoclienteView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.guardarcliente || id == EditorInfo.IME_NULL) {
                    insertarCliente();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.guardarcliente);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarCliente();
            }
        });

    }



    private void insertarCliente() {
        // Reset errors.
        mcodigoclienteView.setError(null);
        mnombreclienteView.setError(null);
        mapellidoclienteView.setError(null);
        mdireccionclienteView.setError(null);
        mtelefonoclienteView.setError(null);
        mcorreoclienteView.setError(null);

        // Store values at the time of the login attempt.
        String cedula = mcodigoclienteView.getText().toString();
        String nombre = mnombreclienteView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(cedula) ) {
            mcodigoclienteView.setError("Debe digitar una cedula");
            focusView = mcodigoclienteView;
            cancel = true;
        }
        if (TextUtils.isEmpty(nombre)) {
            mnombreclienteView.setError("Campo requerido");
            focusView = mnombreclienteView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();

        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            try {
                JSONObject jsonParams = new JSONObject();
                jsonParams.put("codigo_cliente", mcodigoclienteView.getText().toString());
                jsonParams.put("nombre_cliente", mnombreclienteView.getText().toString());
                jsonParams.put("apellido_cliente", mapellidoclienteView.getText().toString());
                jsonParams.put("direccion_cliente", mdireccionclienteView.getText().toString());
                jsonParams.put("telefono_cliente", mtelefonoclienteView.getText().toString());
                jsonParams.put("correo_cliente", mcorreoclienteView.getText().toString());
                StringEntity entity = new StringEntity(jsonParams.toString());

                invokeWS(entity);
            }catch (JSONException e){
                System.out.println("e = " + e);

            }catch (UnsupportedEncodingException w){

                System.out.println("w = " + w);
            }

        }
    }





    public void invokeWS(StringEntity params){
        // Make RESTful webservice call using AsyncHttpClient object
        AsyncHttpClient client = new AsyncHttpClient();
        //client.addHeader("Accept"," application/json");
        //client.addHeader("content-type"," application/json");
        client.post(getApplicationContext(), "http://10.0.2.2:8080/moteles/webresources/guardarCliente",params ,"application/json",
                new AsyncHttpResponseHandler() {


                    // When the response returned by REST has Http response code '200'
                    @Override
                    public void onSuccess(String response) {
                        // Hide Progress Dialog
                        try {
                            // JSON Object
                            if(response !=null) {
                                JSONObject arreglo = new JSONObject(response);
                                Cliente cliente = new Cliente();

                                cliente.setCodigoCliente(arreglo.getString("codigoCliente"));
                                cliente.setNombreCliente(arreglo.getString("nombreCliente"));
                                cliente.setApellidoCliente(arreglo.getString("apellidoCliente"));
                                cliente.setDireccionCliente(arreglo.getString("direccionCliente"));
                                cliente.setTelefonoCliente(arreglo.getString("telefonoCliente"));
                                cliente.setCorreoCliente(arreglo.getString("correoCliente"));


                                //vista_Registro();
                                Toast.makeText(getApplicationContext(), "Se guardo el numero de cedula  " + cliente.getCodigoCliente() , Toast.LENGTH_LONG).show();
                            }else {

                                Toast.makeText(getApplicationContext(),"Accesos Incorrectos", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();

                        }

                    }
                    // When the response returned by REST has Http response code other than '200'
                    @Override
                    public void onFailure(int statusCode, Throwable error,
                                          String content) {
                        // Hide Progress Dialog

                        // When Http response code is '404'
                        if(statusCode == 404){
                            Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code is '500'
                        else if(statusCode == 500){
                            Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }
                        // When Http response code other than 404, 500
                        else{
                            System.out.println(content);
                            Toast.makeText(getApplicationContext(), "Codigo " + statusCode + content, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public  void    iniciarSesion(View view){
        Intent intent = new Intent();
        intent.setClass(CrearCliente.this, ListarClientes.class);
        startActivity(intent);

    }
}
