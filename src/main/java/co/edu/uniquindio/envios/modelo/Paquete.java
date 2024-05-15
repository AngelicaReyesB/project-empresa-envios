package co.edu.uniquindio.envios.modelo;


import co.edu.uniquindio.envios.modelo.enums.TipoEstado;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Paquete implements Serializable {

    private final double valorDeclarado;
    private final double peso;
    private final String descripcion;
}
