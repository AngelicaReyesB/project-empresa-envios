package co.edu.uniquindio.envios.modelo;


import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona implements Serializable {


    @EqualsAndHashCode.Include
    private String cedula;
    private String nombre;
    private String direccion;
    private String ciudad;
    private String contacto;
    private String email;
}
