package co.edu.uniquindio.envios.servicio;

import co.edu.uniquindio.envios.modelo.*;

import java.util.ArrayList;

public interface CreacionEnvio {

    Envio crearOrdenEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes);

    Factura crearFactura(Envio envio, float subtotal);


    float calcularCostoEnvio(Envio envio);


}
