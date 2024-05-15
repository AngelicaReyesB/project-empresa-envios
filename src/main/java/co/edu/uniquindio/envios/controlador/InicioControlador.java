package co.edu.uniquindio.envios.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.awt.event.ActionEvent;

public class InicioControlador {

    private final ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        controladorPrincipal = ControladorPrincipal.getINSTANCIA();
    }

    @FXML
    private ComboBox<String> cbEstado;
    @FXML
    private DatePicker dateFecha;

    @FXML
    private void crearEnvio(javafx.event.ActionEvent actionEvent) {
        controladorPrincipal.navegar("/crearEnvio.fxml", "Envío - Registro de Envío");
    }

    @FXML
    private void cambiarEstado(javafx.event.ActionEvent event) {
        controladorPrincipal.navegar("/cambiarEstado.fxml", "Envío - Cambiar estado");
    }

    @FXML
    private void consultarEnvio(javafx.event.ActionEvent event) {
        controladorPrincipal.navegar("/consultarEnvio.fxml", "Envío - Consultar Envío");
    }

    @FXML
    private void btnFiltrar(javafx.event.ActionEvent event) {

    }

    @FXML
    private void initialize() {

    }
}

