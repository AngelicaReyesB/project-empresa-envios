package co.edu.uniquindio.envios.factory;

import co.edu.uniquindio.envios.modelo.Envio;
import co.edu.uniquindio.envios.modelo.Factura;
import co.edu.uniquindio.envios.modelo.Paquete;
import co.edu.uniquindio.envios.modelo.Persona;
import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.servicio.CreacionEnvio;

import java.util.ArrayList;

public class EnvioEstandar implements CreacionEnvio {

    @Override
    public Envio crearOrdenEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes) {
        Envio envio = new Envio();
        envio.setRemitente(remitente);
        envio.setDestinatario(destinatario);
        envio.setTipoEnvio(TipoEnvio.ESTANDAR);
        envio.setPaquetes(paquetes);
        return envio;
    }

    @Override
    public Factura crearFactura(Envio envio, float subtotal) {
        Factura factura = new Factura();
        factura.setCodigoEnvio(envio.getCodigo());
        factura.setSubtotal(subtotal);
        return factura;
    }

    @Override
    public float calcularCostoEnvio(Envio envio) {
        float costoBase = 7.00f; // Precio base del envío estándar
        float costoPorKgAdicional = 2.00f; // Costo por cada kilogramo adicional en envío estándar

        double pesoTotal = envio.getPaquetes().stream().map(Paquete::getPeso).reduce(0.0, Double::sum);
        float costoTotal = costoBase;

        if (pesoTotal > 2.5) {
            double pesoExtra = pesoTotal - 2.5;
            costoTotal += pesoExtra * costoPorKgAdicional;
        }


        return costoTotal;
    }
}
