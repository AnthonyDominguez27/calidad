package capa1_Presentacion.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class PrincipalController {
private LoginController loginController;
private Stage stage;
    @FXML
    private Button btnsalir;

    @FXML
    private Label lblusuario;


    @FXML
    private TextField txtnombre;
    @FXML
    private Label txtsalir;


    public void init(String text, Stage stage, LoginController loginController) {
        lblusuario.setText(text);
        this.loginController = loginController;
        this.stage=stage;


    }
    @FXML
    public void showCita(ActionEvent event) throws IOException,Exception {
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
}

