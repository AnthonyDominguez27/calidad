package capa1_Presentacion.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
private Stage stage;
    @FXML
    private Button btningresar;

    @FXML
    private PasswordField txtcontraseña= new PasswordField();

    @FXML
    private TextField txtusuario=new TextField();

    @FXML
    void eventKey(KeyEvent event) {
    }

    @FXML
    void showPrincipal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capa1_Presentacion/Interfaces/Principal.fxml"));
        Parent root=loader.load();
        PrincipalController controller= loader.getController();
        Scene scene= new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(txtusuario.getText(), stage, this);
        stage.show(); //visualizar
        this.stage.close(); //cerrar ventana
    }

    public void setStage(Stage primaStage) {
        stage =primaStage;
    }
}
