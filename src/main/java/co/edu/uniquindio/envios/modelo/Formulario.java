package co.edu.uniquindio.envios.modelo;

import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.modelo.enums.TipoEstado;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class Formulario {

    private UUID codigo;
    private Persona remitente;
    private Persona destinatario;
    private Paquete paquete;
    private LocalDate fechaInicio;
    private TipoEnvio tipoEnvio;
    private float valorDeclarado;
    private TipoEstado tipoEstado;

    public void add(Formulario formulario) {
    }
}
