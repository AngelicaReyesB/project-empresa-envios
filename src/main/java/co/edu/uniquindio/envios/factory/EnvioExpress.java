package co.edu.uniquindio.envios.factory;

import co.edu.uniquindio.envios.modelo.Envio;
import co.edu.uniquindio.envios.modelo.Factura;
import co.edu.uniquindio.envios.modelo.Paquete;
import co.edu.uniquindio.envios.modelo.Persona;
import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.servicio.CreacionEnvio;

import java.util.ArrayList;

public class EnvioExpress implements CreacionEnvio {

    @Override
    public Envio crearOrdenEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes) {
        Envio envio = new Envio();
        envio.setRemitente(remitente);
        envio.setDestinatario(destinatario);
        envio.setTipoEnvio(TipoEnvio.EXPRESS);
        envio.setPaquetes(paquetes);
        // Otros detalles del envío...

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
        float costoBase = 10.00f; // Precio base del envío express
        float costoPorKgAdicional = 3.00f; // Costo por cada kilogramo adicional en envío express

        double pesoTotal = envio.getPaquetes().stream().map(Paquete::getPeso).reduce(0.0, Double::sum);
        float costoTotal = costoBase;

        if (pesoTotal > 2) {
            double pesoExtra = pesoTotal - 2;
            costoTotal += pesoExtra * costoPorKgAdicional;
        }

        return costoTotal;
    }
}
