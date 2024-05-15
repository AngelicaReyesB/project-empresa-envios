package co.edu.uniquindio.envios.modelo;

import co.edu.uniquindio.envios.servicio.CreacionEnvio;
import co.edu.uniquindio.envios.factory.EnvioEstandar;
import co.edu.uniquindio.envios.factory.EnvioExpress;
import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.servicio.ServiciosEmpresa;
import co.edu.uniquindio.envios.utils.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpresaEnvios implements ServiciosEmpresa {

    private Persistencia persistencia;
    public ArrayList<Envio> envios;
    public ArrayList<Factura> facturas;
    public ArrayList<Persona> personas;
    public ArrayList<Paquete> paquetes;


    public EmpresaEnvios() {
        this.envios = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.personas = new ArrayList<>();
        this.persistencia = new Persistencia();
        cargarDatos();
    }

    //persistencia para que queden guardados los datos
    @Override
    public void guardarDatos() throws Exception {
        try {
            persistencia.guardarPersonas(personas);
            persistencia.guardarFacturas(facturas);
            persistencia.guardarEnvios(envios);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    //persistencia para que queden guardados los datos
    @Override
    public void cargarDatos() {
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
            e.printStackTrace();
        }
    }


    @Override
    public Envio crearOrdenEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes, TipoEnvio tipoEnvio) throws Exception {

        CreacionEnvio creacionEnvio;

        if (tipoEnvio == TipoEnvio.ESTANDAR) {
            creacionEnvio = new EnvioEstandar();
        } else {
            creacionEnvio = new EnvioExpress();
        }

        Envio envio = creacionEnvio.crearOrdenEnvio(remitente, destinatario, paquetes);
        float valor = creacionEnvio.calcularCostoEnvio(envio);
        Factura factura = creacionEnvio.crearFactura(envio, valor);

        boolean remitenteExiste = false;
        for (Persona persona : personas) {
            if (persona.equals(remitente)) {
                remitenteExiste = true;
                break;
            }
        }
        if (!remitenteExiste) {
            personas.add(remitente);
        }

        boolean destinatarioExiste = false;
        for (Persona persona : personas) {
            if (persona.equals(destinatario)) {
                destinatarioExiste = true;
                break;
            }
        }
        if (!destinatarioExiste) {
            personas.add(destinatario);
        }

        envios.add(envio);
        facturas.add(factura);

        return envio;
    }




    @Override
    public ArrayList<Envio> listarTodosEnvios() {
        return envios;
    }

    public ArrayList<Envio> listarEnvios(LocalDate fecha) {
        ArrayList<Envio> enviosEnFecha = new ArrayList<>();
        for (Envio envio : envios) {
            if (envio.getFechaEnvio().isEqual(fecha)) {
                enviosEnFecha.add(envio);
            }
        }
        return enviosEnFecha;
    }
}
