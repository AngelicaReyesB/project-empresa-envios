package co.edu.uniquindio.envios.controlador;
import co.edu.uniquindio.envios.controlador.observador.Observable;
import co.edu.uniquindio.envios.modelo.Formulario;
import co.edu.uniquindio.envios.modelo.Paquete;
import co.edu.uniquindio.envios.modelo.Persona;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class CrearEnvioControlador {

    @FXML private TextField txtCedulaDest;
    @FXML private TextField txtCedulaRem;
    @FXML private TextField txtCiudadDest;
    @FXML private TextField txtCiudadRem;
    @FXML private TextField txtContactoDest;
    @FXML private TextField txtContactoRem;
    @FXML private TextField txtDescripcion;
    @FXML private TextField txtDireccionDest;
    @FXML private TextField txtDireccionRem;
    @FXML private TextField txtEmailDest;
    @FXML private TextField txtEmailRem;
    @FXML private TextField txtNombreDest;
    @FXML private TextField txtNombreRem;
    @FXML private TextField txtPeso;
    @FXML private TextField txtValor;
    @FXML private Button btnBuscarRem;
    @FXML private Button btnBuscarDest;
    @FXML private Button btnRegistrarRem;
    @FXML private Button btnRegistrarDest;
    @FXML private Button btnAgregarPaquete;
    private ArrayList<Persona> personas;
    private Observable observable;
    private final ControladorPrincipal controladorPrincipal;

    public CrearEnvioControlador(){
        controladorPrincipal = ControladorPrincipal.getINSTANCIA();
    }


    //Método que se encarga de crear un envío con los datos ingresados
    @FXML
    public void crearEnvio(javafx.event.ActionEvent actionEvent){
        try {
            Formulario formulario = controladorPrincipal.crearOrdenEnvio();
            controladorPrincipal.mostrarAlerta("El envío se ha creado correctamente.", Alert.AlertType.INFORMATION);
            observable.notificar();
            controladorPrincipal.cerrarVentana(txtCedulaDest);
        }catch (Exception e){
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    //buscar si los usuarios ya están registrados
    public void buscarRemitente(javafx.event.ActionEvent actionEvent) {
        Persona persona = controladorPrincipal.buscarPersona(txtCedulaRem.getText());
        if(persona != null) {
            txtCedulaRem.setText(persona.getCedula());
        }
    }

    public void buscarDestinatario(javafx.event.ActionEvent actionEvent) {
        Persona persona = controladorPrincipal.buscarPersona(txtCedulaDest.getText());
        if(persona != null) {
            txtCedulaDest.setText(persona.getCedula());
        }
    }

    //registro de usuario
    @FXML
    public void registrarRemitente(javafx.event.ActionEvent actionEvent) {
        try {
            personas.add(new Persona(
                    txtCedulaRem.getText(),
                    txtNombreRem.getText(),
                    txtDireccionRem.getText(),
                    txtCiudadRem.getText(),
                    txtContactoRem.getText(),
                    txtEmailRem.getText()));

            controladorPrincipal.mostrarAlerta("Se ha registrado correctamente", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(txtCedulaRem);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void registrarDestinatario(javafx.event.ActionEvent actionEvent) {
        try {
            personas.add(new Persona(
                    txtCedulaDest.getText(),
                    txtNombreDest.getText(),
                    txtDireccionDest.getText(),
                    txtCiudadDest.getText(),
                    txtContactoDest.getText(),
                    txtEmailDest.getText()));

            controladorPrincipal.mostrarAlerta("Se ha registrado correctamente", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana(txtCedulaDest);
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    //método para agregar un paquete en la tabla
    @FXML
    public void agregarPaquete(javafx.event.ActionEvent event) {
        try {
            double valorDeclarado = Double.parseDouble(txtValor.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String descripcion = txtDescripcion.getText();

            Paquete paquete = new Paquete(valorDeclarado, peso, descripcion);
            controladorPrincipal.getPaquetes().add(paquete);
            organizar();
        } catch (Exception e) {
            controladorPrincipal.mostrarAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void organizar(){
        txtPeso.clear();
        txtValor.clear();
        txtDescripcion.clear();
    }
}


