package capa1_Presentacion;
import java.io.IOException;

import capa1_Presentacion.Controllers.LoginController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import capa3_Dominio.Paciente;
import capa2_Aplicacion.RegistrarPacienteServicio;

public class Main  extends Application{

   
    @Override
    public void start(Stage primaStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capa1_Presentacion/Interfaces/Login.fxml"));
        Parent root=loader.load();
        Scene scene= new Scene(root);
        primaStage.setScene(scene);
        LoginController controller= loader.getController();
        controller.setStage(primaStage);
        primaStage.show();
        RegistrarPacienteServicio registrarPaciente=new RegistrarPacienteServicio();
        Paciente paciente= new Paciente("76622110");
        System.out.println(registrarPaciente.buscarPaciente(paciente.getDni()));


    }
     public static void main(String[] args) {
       launch(args);
    }


    
}
