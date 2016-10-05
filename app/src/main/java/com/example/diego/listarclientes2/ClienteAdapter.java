package com.example.diego.listarclientes2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.diego.entidades.Cliente;

import java.util.List;

/**
 * Created by DIEGO on 07/09/2016.
 */
public class ClienteAdapter extends BaseAdapter {

    private final Activity actividad;
    public List<Cliente> lista;

    public ClienteAdapter(Activity actividad) {
        this.actividad = actividad;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=actividad.getLayoutInflater();

        View vista=inflater.inflate(R.layout.elemento_cliente,null,true);

        TextView txtCodigo = (TextView) vista.findViewById(R.id.txtCodigo);
        txtCodigo.setText(lista.get(position).getCodigoCliente().toString());

        TextView txtNombre = (TextView) vista.findViewById(R.id.txtNombre);
        txtNombre.setText(lista.get(position).getNombreCliente().toString());



        return vista;
    }
}
