package co.edu.uniquindio.envios.controlador;

import co.edu.uniquindio.envios.modelo.Envio;
import co.edu.uniquindio.envios.modelo.Formulario;
import co.edu.uniquindio.envios.modelo.Paquete;
import co.edu.uniquindio.envios.modelo.Persona;
import co.edu.uniquindio.envios.modelo.enums.TipoEnvio;
import co.edu.uniquindio.envios.modelo.enums.TipoEstado;
import co.edu.uniquindio.envios.servicio.ServiciosEmpresa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorPrincipal implements ServiciosEmpresa {

    private final Envio envio;
    private ArrayList<Persona> personas;
    @Getter
    private ArrayList<Paquete> paquetes;

    // Singleton
    private static ControladorPrincipal INSTANCIA;

    private ControladorPrincipal() {
        envio = new Envio();
        personas = new ArrayList<>();
        paquetes = new ArrayList<>();
    }

    public static ControladorPrincipal getINSTANCIA() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    public Persona buscarPersona(String cedula) {
        return envio.buscarPersona(cedula);
    }

    @Override
    public void guardarDatos() throws Exception {

    }

    @Override
    public void cargarDatos() throws Exception {

    }

    @Override
    public Envio crearOrdenEnvio(Persona remitente, Persona destinatario, ArrayList<Paquete> paquetes, TipoEnvio tipoEnvio) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Envio> listarTodosEnvios() {
        return null;
    }

    @Override
    public ArrayList<Envio> listarEnvios(LocalDate fecha) {
        return null;
    }

    public FXMLLoader navegar(String nombreVista, String titulo) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreVista));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);

            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(titulo);

            // Mostrar la nueva ventana
            stage.show();

            return loader;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta - Envío");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Formulario crearOrdenEnvio() {
        return null;
    }

    public Envio getEnvio() {
        return null;
    }

}
