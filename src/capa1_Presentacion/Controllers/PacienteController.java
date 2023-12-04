package capa1_Presentacion.Controllers;
import capa2_Aplicacion.RegistrarCitaServicio;
import capa2_Aplicacion.RegistrarDisponibilidadServicio;
import capa3_Dominio.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;
import java.time.LocalDateTime;
import capa2_Aplicacion.RegistrarEspecialidadServicio;
import capa2_Aplicacion.RegistrarPacienteServicio;
import javafx.util.StringConverter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class PacienteController {
    private CitaController citaController;
    private LoginController loginController;
    private RegistrarPacienteServicio registrarPacienteServicio = new RegistrarPacienteServicio();
    private Stage stage;
    @FXML
    private Button btnregistrarpaciente;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;
    @FXML
    private Label lblusuario;
    @FXML
    public void init(String text, Stage stage, CitaController citaController) throws Exception {
        lblusuario.setText(text);
        this.citaController = citaController;
        this.stage = stage;
    }
    @FXML
    public void showCita(ActionEvent event) throws IOException,Exception {
        System.out.println("E");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capa1_Presentacion/Interfaces/Cita.fxml"));
        Parent root= loader.load();
        CitaController controller= loader.getController();
        Scene scene= new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(lblusuario.getText(), stage, this);
        stage.show(); //visualizar
        this.stage.close(); //cerrar ventana
    }

    @FXML
    private void registrarCita() throws Exception {
        System.out.println("Botón de registrar Paciente presionado");
        String dni = new String(txtDni.getText());
        String nombre= new String(txtNombre.getText());
        String direccion = new String(txtDireccion.getText());
        String correo= new String(txtCorreo.getText());
        String telefono = new String(txtTelefono.getText());
        int edad=0;
        try {
            if(txtEdad.getText().isEmpty() ||!txtEdad.getText().strip().matches("\\d+")) {
                throw new IllegalArgumentException("Complete los campos");
            }
            else {
                edad= Integer.parseInt(txtEdad.getText());
            }
            Paciente paciente = new Paciente( dni,  nombre,  direccion,  correo,  telefono,  edad);
            if(paciente.esPacienteValido())
                registrarPacienteServicio.GuardarPaciente(paciente);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cita Registrada");
            alert.setContentText("Datos Registrados correctamente");
            alert.showAndWait();

        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Datos Invalidos");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            System.out.println("Error en la cita: " + e.getMessage());
        }

    }


}
