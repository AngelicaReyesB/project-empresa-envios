package co.edu.uniquindio.envios.modelo;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import lombok.*;

//import static co.edu.uniquindio.envios.utils.Persistencia.RUTA_PERSONAS;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Factura implements Serializable {

    private String codigoEnvio;
    private double total;
    private double subtotal;

    public Factura() {
    }
}
