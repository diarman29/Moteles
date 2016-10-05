package com.example.diego.listarclientes2;

/**
 * Created by DIEGO on 03/10/2016.
 */

import com.example.diego.entidades.Cliente;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class WebService {
    //Namespace of the Webservice - can be found in WSDL
    private static String NAMESPACE = "http://servicios.clientes/";
    //Webservice URL - WSDL File location
    private static String URL = "http://10.0.2.2:8080/moteles/WSclientes?wsdl";
    //SOAP Action URI again Namespace + Web method name
    private static String SOAP_ACTION = "http://servicios.clientes/WSclientes";

    private static List<HeaderProperty> headerPropertyList = new ArrayList<HeaderProperty>();

    public static String saludar(String name, String webMethName) {
        String resTxt = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, webMethName);
        // Property which holds input parameters
        PropertyInfo sayHelloPI = new PropertyInfo();
        // Set Name
        sayHelloPI.setName("name");
        // Set Value
        sayHelloPI.setValue(name);
        // Set dataType
        sayHelloPI.setType(String.class);
        // Add the property to request object
        request.addProperty(sayHelloPI);
        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + webMethName, envelope,headerPropertyList);
            // Get the response
            SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable
            resTxt = response.toString();

        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            resTxt = "Error occured";
        }
        //Return resTxt to calling object
        return resTxt;
    }

    public static List<Cliente> ListarClientes(String nombreMetodo){
        List<Cliente> listado = new ArrayList<>();
        SoapObject request = new SoapObject(NAMESPACE, nombreMetodo);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);

        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + nombreMetodo, envelope);
            // Get the response
            //SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
            // Assign it to resTxt variable static variable




            Vector<SoapObject> vector = (Vector<SoapObject>) envelope.getResponse();

            System.out.println("vector.toString() = " + vector.toString());
            //resTxt = response.toString();

            for (SoapObject mas:vector){
                Cliente cliente= new Cliente(mas.getProperty("codigoCliente").toString(),mas.getProperty("nombreCliente").toString()
                        ,mas.getProperty("apellidoCliente").toString(),mas.getProperty("direccionCliente").toString(),
                        mas.getProperty("telefonoCliente").toString(),mas.getProperty("correoCliente").toString());
                listado.add(cliente);
            }


        } catch (Exception e) {
            //Print error
            e.printStackTrace();
            //Assign error message to resTxt
            // resTxt = "Error occured";
        }
        //Return resTxt to calling object
        return listado;
    }
}
