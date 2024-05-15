package co.edu.uniquindio.envios.modelo;

import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.modelo.enums.TipoEstado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

// Clase Envío con atributos, constructor, getters y setters y métodos
@Setter
@AllArgsConstructor
@Getter
public class Envio implements Serializable {

    // Atributos
    private String codigo;
    private Persona destinatario;
    private Persona remitente;
    private TipoEnvio tipoEnvio;
    private final ArrayList<Paquete> paquetes;
    private final ArrayList<TipoEstado> historialEnvio;

    // Atributos adicionales
    private final ArrayList<Persona> personas;
    private final ArrayList<Formulario> formularios;
    private LocalDate fechaEnvio;

    // Constructor
    public Envio() {

        this.paquetes = new ArrayList<>();
        this.historialEnvio  = new ArrayList<>();
        this.personas  = new ArrayList<>();
        this.formularios = new ArrayList<>();
    }

    // Método para validar los datos de un envío antes de crearlo
    public boolean validarDatosEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes, TipoEnvio tipoEnvio) {
        return validarRemitente(remitente) && validarDestinatario(destinatario) && validarPaquetes(paquetes) && validarTipoEnvio(tipoEnvio);
    }

    // Métodos de validación individuales
    private boolean validarRemitente(Persona remitente) {
        return remitente != null && remitente.getCedula() != null && !remitente.getCedula().isEmpty() && remitente.getNombre() != null && !remitente.getNombre().isEmpty();
    }

    private boolean validarDestinatario(Persona destinatario) {
        return destinatario != null && destinatario.getCedula() != null && !destinatario.getCedula().isEmpty() && destinatario.getNombre() != null && !destinatario.getNombre().isEmpty();
    }

    private boolean validarPaquetes(ArrayList<Paquete> paquetes) {
        return paquetes != null && !paquetes.isEmpty();
    }

    private boolean validarTipoEnvio(TipoEnvio tipoEnvio) {
        return tipoEnvio != null;
    }

    // Método para agregar una persona y validar datos
    private Persona agregarPersona(String cedula, String nombre, String direccion, String ciudad, String contacto, String email) throws Exception {
        if (cedula == null || cedula.isBlank()) {
            throw new Exception("La cédula es obligatoria.");
        }
        if (nombre == null || nombre.isBlank()){
            throw new Exception("El nombre es obligatorio.");
        }
        if (direccion == null || direccion.isBlank()){
            throw new Exception("La dirección es obligatoria.");
        }
        if (ciudad == null || ciudad.isBlank()){
            throw new Exception("La ciudad es obligatoria.");
        }
        if (contacto == null || contacto.isBlank()){
            throw new Exception("El número de contacto es obligatorio.");
        }
        if(buscarPersona(cedula) != null){
            throw new Exception("El cliente ya existe.");
        }

        Persona persona = Persona.builder()
                .cedula(cedula)
                .nombre(nombre)
                .direccion(direccion)
                .ciudad(ciudad)
                .contacto(contacto)
                .email(email)
                .build();

        personas.add(persona);
        return persona;
    }

    // Método para actualizar los datos de una persona
    public void actualizarPersona(String cedula, String nombre, String direccion, String ciudad, String contacto, String email) throws Exception {
        if(cedula == null || cedula.isBlank()){
            throw new Exception("La cédula es obligatoria");
        }

        if(nombre == null || nombre.isBlank()){
            throw new Exception("El nombre es obligatorio");
        }

        if(direccion == null || direccion.isBlank()){
            throw new Exception("La dirección es obligatoria");
        }

        if(ciudad == null || ciudad.isBlank()){
            throw new Exception("La ciudad es obligatoria");
        }

        if(contacto == null || contacto.isBlank()){
            throw new Exception("El contacto es obligatorio");
        }

        if(obtenerPersona(cedula) == null){
            throw new Exception("No existe un usuario con el número de cédula: "+cedula);
        }

        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getCedula().equals(cedula)) {
                Persona persona = new Persona(cedula,nombre, direccion, ciudad, contacto, email);
                personas.set(i, persona);
                break;
            }
        }
    }

    // Método para generar un código único utilizando UUID
    private String generarNumeroCodigo(){
        return UUID.randomUUID().toString().substring(0, 10); // Tomar los primeros 10 caracteres del UUID generado
    }

    // Método para validar una persona
    public Persona validarPersona(String cedula) throws Exception {
        Persona persona = obtenerPersona(cedula);
        if(persona != null){
            if(persona.getCedula().equals(cedula)){
                return persona;
            }
        }
        throw new Exception("Los datos de acceso son incorrectos");
    }

    // Método para obtener un envío por su código
    public Formulario obtenerEnvio(String codigo) {
        for (Formulario formulario : formularios) {
            if (formulario.getCodigo().equals(codigo)) {
                return formulario;
            }
        }
        return null;
    }

    // Método para obtener una persona por su cédula
    private Persona obtenerPersona(String cedula) {
        for(int i = 0; i < personas.size(); i++){
            if(personas.get(i).getCedula().equals(cedula)){
                return personas.get(i);
            }
        }
        return null;
    }

    // Método para buscar una persona por su número de cédula
    public Persona buscarPersona(String cedula) {
        for(Persona persona : personas){
            if(persona.getCedula().equals(cedula)){
                return persona;
            }
        }
        return null;
    }

    public double calcularPrecioBase() {
        double precioBase = 0;
        double pesoTotal = calcularPesoTotal(); // Supongamos que tienes un método para calcular el peso total de los paquetes

        if (tipoEnvio == TipoEnvio.EXPRESS && pesoTotal <= 2) {
            precioBase = 15000; // Precio base para envío express hasta 2 kg en pesos colombianos
        } else if (tipoEnvio == TipoEnvio.ESTANDAR && pesoTotal <= 2.5) {
            precioBase = 10000; // Precio base para envío estándar hasta 2.5 kg en pesos colombianos
        }

        return precioBase;
    }

    private double calcularPesoTotal() {
        return 0;
    }

    public void setRemitente(Persona remitente) {
    }

    public void setDestinatario(Persona destinatario) {
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
    }

    public void setPaquetes(ArrayList<Paquete> paquetes) {
    }


    public Formulario crearRegistro(String cedula, String nombre, String direccion, String ciudad, String contacto, String email, LocalDate fechaEnvio, TipoEstado estado, int cantidadPaquetes, float valorTotal) throws Exception {
           Persona persona = buscarPersona(cedula);

           if(persona == null){
               persona = agregarPersona(cedula, nombre, direccion, ciudad, contacto, email);
           }
           if(fechaEnvio == null){
               throw new Exception("La fecha de envío es obligatoria");
           }
           if(fechaEnvio.isBefore(LocalDate.now())){
               throw new Exception("La fecha de inicio debe ser mayor o igual a la fecha actual");
           }

           Formulario formulario = Formulario.builder()
                   .codigo(UUID.randomUUID())
                   .remitente(persona)
                   .fechaInicio(fechaEnvio)
                   .paquete(Paquete.builder().build())
                   .valorDeclarado(valorTotal)
                   .tipoEstado(TipoEstado.CREADO)
                   .build();

           formulario.add(formulario);

           return formulario;
       }

    //Métodos adicionales
    public double calcularPrecio() {
        return 0;
    }
    public void agregarEstado() {
    }
    public String generarCodigo() {
        return null;
    }


}
