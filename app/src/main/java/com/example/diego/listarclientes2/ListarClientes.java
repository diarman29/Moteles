package com.example.diego.listarclientes2;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.diego.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DIEGO on 03/10/2016.
 */
public class ListarClientes extends ListActivity {

    private ClienteAdapter clienteAdapter;
    private List<Cliente> listado= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listar_clientes);

        AsyncCallWS asyn= new AsyncCallWS();
        asyn.execute();

        clienteAdapter = new ClienteAdapter(this);
    }

    private class AsyncCallWS extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... params){
            //Invoke webservice

            listado=WebService.ListarClientes("listarClientes");
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //set response

            clienteAdapter.setLista(listado);
            setListAdapter(clienteAdapter);
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            //pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
