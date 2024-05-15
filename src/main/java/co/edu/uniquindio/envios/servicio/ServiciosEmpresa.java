package co.edu.uniquindio.envios.servicio;

import co.edu.uniquindio.envios.modelo.Envio;
import co.edu.uniquindio.envios.modelo.Factura;
import co.edu.uniquindio.envios.modelo.Paquete;
import co.edu.uniquindio.envios.modelo.Persona;
import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.utils.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface ServiciosEmpresa {

    ArrayList<Persona> personas = new ArrayList<>();
    ArrayList<Factura> facturas = new ArrayList<>();
    ArrayList<Envio> envios = new ArrayList<>();
    Persistencia persistencia = new Persistencia();

    public default void guardarDatos() throws Exception {
        try {
            persistencia.guardarPersonas(personas);
            persistencia.guardarFacturas(facturas);
            persistencia.guardarEnvios(envios);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public default void cargarDatos() throws Exception {
        try {
            ArrayList<Persona> personasCargadas = persistencia.cargarPersonas();
            ArrayList<Factura> facturasCargadas = persistencia.cargarFacturas();
            ArrayList<Envio> enviosCargados = persistencia.cargarEnvios();

            if(personasCargadas != null){
                personas.addAll(personasCargadas);
            }

            if(facturasCargadas != null){
                facturas.addAll(facturasCargadas);
            }

            if(enviosCargados != null){
                envios.addAll(enviosCargados);
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
    }




    Envio crearOrdenEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes, TipoEnvio tipoEnvio) throws Exception;

    ArrayList<Envio> listarTodosEnvios();

    ArrayList<Envio> listarEnvios(LocalDate fecha);

    // Otros métodos del proyecto
}
