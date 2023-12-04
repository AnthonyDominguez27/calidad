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

public class CitaController {

    private Stage stage;
    private PrincipalController principalController;
    private PacienteController pacienteController;

    @FXML
    private Button btnBuscarDisponibilidad;
    private boolean isSearching = false;
    private Paciente pacienteSeleccionado;
    private Disponibilidad disponibilidadSeleccionada;
    private RegistrarCitaServicio registrarCitaServicio = new RegistrarCitaServicio();



    @FXML
    private ComboBox<Especialidad> cboEspecialidad;
    @FXML
    private Label lblusuario;
    @FXML
    private ComboBox<Paciente> cboPaciente;
    @FXML
    private DatePicker dtpick1;
    @FXML
    private DatePicker dtpick2;
    @FXML
    private TableView<Disponibilidad> tableDisponibilidad = new TableView<>();
    @FXML
    private TableColumn<Disponibilidad, Especialidad> especialidadColumn;
    @FXML
    private TableColumn<Disponibilidad, LocalDateTime> fechaColumn;
    @FXML
    private TableColumn<Disponibilidad, LocalDateTime> horaColumn;
   @FXML
    private TableColumn<Disponibilidad, Consultorio> consultorioColumn;
    @FXML
    private TableColumn<Disponibilidad, Medico> medicoColumn;
    @FXML
    private TextField txtAsunto;
    @FXML
    private TextField txtImporte;

    @FXML
    public void init(String text, Stage stage, PrincipalController principalController) throws Exception {
        lblusuario.setText(text);
        this.principalController = principalController;
        this.stage = stage;
        llenarComboBoxEspecialidades();
        llenarComboBoxPacientes();
        setDatePickerMinimumDate(dtpick1);
        setDatePickerMinimumDate(dtpick2);
        configureLinkedDatePicker(dtpick1, dtpick2);
        dtpick1.setValue(LocalDate.now());
        llenarDisponibilidad();
    }
    public void init(String text, Stage stage, PacienteController pacienteController) throws Exception {
        lblusuario.setText(text);
        this.pacienteController = pacienteController;
        this.stage = stage;
        llenarComboBoxEspecialidades();
        llenarComboBoxPacientes();
        setDatePickerMinimumDate(dtpick1);
        setDatePickerMinimumDate(dtpick2);
        configureLinkedDatePicker(dtpick1, dtpick2);
        dtpick1.setValue(LocalDate.now());
        llenarDisponibilidad();
    }

    @FXML
    void showRegistrarPaciente(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/capa1_Presentacion/Interfaces/Paciente.fxml"));
        Parent root=loader.load();
        PacienteController controller= loader.getController();
        Scene scene= new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        controller.init(lblusuario.getText(), stage, this);
        stage.show(); //visualizar
        this.stage.close(); //cerrar ventana
    }
    private void llenarComboBoxEspecialidades() {
        try {
            ObservableList<Especialidad> especialidadesList = FXCollections.observableArrayList();
            RegistrarEspecialidadServicio registrarEspecialidad = new RegistrarEspecialidadServicio();
            Especialidad all=new Especialidad();
            all.setEspecialidad("TODAS");
            especialidadesList.add(all);
            List<Especialidad> especialidades = registrarEspecialidad.listarEspecialidades();
            especialidadesList.addAll(especialidades);
            cboEspecialidad.setItems(especialidadesList);

            if (!especialidadesList.isEmpty()) {
                cboEspecialidad.getSelectionModel().select(0);
            }

            cboEspecialidad.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Especialidad item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getEspecialidad());
                    }
                }
            });

            cboEspecialidad.setButtonCell(new ListCell<>() {
                @Override
                protected void updateItem(Especialidad item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getEspecialidad());
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            // Maneja la excepción de alguna manera apropiada.
        }
    }

    private void llenarComboBoxPacientes() {
        try {
            if (pacienteSeleccionado == null) {
                RegistrarPacienteServicio registrarPaciente = new RegistrarPacienteServicio();
                ObservableList<Paciente> pacientes = FXCollections.observableArrayList(registrarPaciente.listarPacientes());
                cboPaciente.setItems(pacientes);

                // Personaliza la forma en que se muestra la celda en la lista desplegable
                cboPaciente.setCellFactory(param -> new ListCell<>() {
                    @Override
                    protected void updateItem(Paciente item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            // Personaliza la representación del paciente
                            setText(item.getNombre() + " - DNI: " + item.getDni());
                        }
                    }
                });

                // Configura el StringConverter para mostrar solo el nombre en el ComboBox
                cboPaciente.setConverter(new StringConverter<Paciente>() {
                    @Override
                    public String toString(Paciente paciente) {
                        return (paciente != null) ? paciente.getNombre() : "";
                    }

                    @Override
                    public Paciente fromString(String string) {
                        return null; // No necesitas esto
                    }
                });

                habilitarBusquedaDinamicaPacientes();
                cboPaciente.setOnAction(event -> {
                    pacienteSeleccionado = cboPaciente.getValue();
                    cboPaciente.setItems(pacientes);
                    if (pacienteSeleccionado != null) {
                        System.out.println("Paciente seleccionado: " + pacienteSeleccionado.getNombre());
                        cboPaciente.setEditable(false);
                    } else {
                        cboPaciente.setEditable(true);
                        System.out.println("No se ha seleccionado un paciente válido.");

                    }
                });
                cboPaciente.setOnMouseClicked(event -> {
                            cboPaciente.setEditable(true);
                            cboPaciente.getEditor().clear();
                            cboPaciente.show();
                        }
                );

            }
        } catch (Exception e) {
            e.printStackTrace();
            // Maneja la excepción de alguna manera apropiada.
        }
    }


    private void habilitarBusquedaDinamicaPacientes() {
        cboPaciente.setEditable(true);
        ObservableList<Paciente> pacientes = cboPaciente.getItems();

        cboPaciente.setConverter(new StringConverter<Paciente>() {
            @Override
            public String toString(Paciente paciente) {
                return (paciente != null) ? paciente.getNombre() + " - DNI: " + paciente.getDni() : "";
            }

            @Override
            public Paciente fromString(String string) {
                return null;
            }
        });

        if (pacienteSeleccionado == null) {
            cboPaciente.setOnAction(event -> {
                pacienteSeleccionado = cboPaciente.getValue();
                isSearching = false; // Detén la búsqueda dinámica
            });
        }

        cboPaciente.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (isSearching) {
                return; // Evita la recursión
            }
            isSearching = true;

            if (newValue == null || newValue.isEmpty()) {
                cboPaciente.setItems(null); // Establecer el ComboBox en null
            } else {
                ObservableList<Paciente> filteredItems = FXCollections.observableArrayList();
                for (Paciente paciente : pacientes) {
                    String nombreDni = paciente.getNombre() + " - DNI: " + paciente.getDni();
                    if (nombreDni.toLowerCase().contains(newValue.toLowerCase())) {
                        filteredItems.add(paciente);
                    }
                }
                cboPaciente.setItems(filteredItems);
            }

            isSearching = false;
        });


    }

    private void setDatePickerMinimumDate(DatePicker datePicker) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.isBefore(today));
            }
        });
    }

    private void setDatePickerMinimumDate2(DatePicker datePicker, LocalDate fecha) {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate nuevo = fecha;
                setDisable(empty || date.isBefore(nuevo));
            }
        });
    }

    private void configureLinkedDatePicker(DatePicker sourceDatePicker, DatePicker linkedDatePicker) {
        sourceDatePicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue != null) {
                    linkedDatePicker.setValue(newValue);
                    setDatePickerMinimumDate2(dtpick2, newValue);
                }
            }
        });
    }
    @FXML
    private void llenarDisponibilidad() throws Exception {
        this.pacienteSeleccionado = cboPaciente.getValue();
        RegistrarDisponibilidadServicio registrarDisponibilidadServicio = new RegistrarDisponibilidadServicio();

        List<Disponibilidad> disponibilidadList = registrarDisponibilidadServicio.listarDisponibilidad(dtpick1.getValue(),dtpick2.getValue().plusDays(1),cboEspecialidad.getValue());

        especialidadColumn = new TableColumn<>("Especialidad");
        especialidadColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEspecialidad()));
        especialidadColumn.setCellFactory(column -> new TableCell<Disponibilidad, Especialidad>() {
            @Override
            protected void updateItem(Especialidad item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getEspecialidad()); // Asumiendo que el nombre es la propiedad que deseas mostrar
                }
            }
        });
        medicoColumn = new TableColumn<>("Medico");
        medicoColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getMedico()));
        medicoColumn.setCellFactory(column -> new TableCell<Disponibilidad, Medico>() {
            @Override
            protected void updateItem(Medico item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getNombre()); // Asumiendo que el nombre es la propiedad que deseas mostrar
                }
            }
        });

        consultorioColumn = new TableColumn<>("Consultorio");
        consultorioColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getConsultorio()));
        consultorioColumn.setCellFactory(column -> new TableCell<Disponibilidad, Consultorio>() {
            @Override
            protected void updateItem(Consultorio item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getConsultorio()); // Asumiendo que el nombre es la propiedad que deseas mostrar
                }
            }
        });
        fechaColumn = new TableColumn<>("Fecha");
        fechaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaHora()));
        fechaColumn.setCellFactory(column -> new TableCell<Disponibilidad, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String fecha = item.format(formatter);
                    setText(fecha); // Asumiendo que el nombre es la propiedad que deseas mostrar
                }
            }
        });
        // Asegúrate de que la columna se llame "horaColumn" si deseas agregarla
        horaColumn = new TableColumn<>("Hora");
        horaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaHora()));
        horaColumn.setCellFactory(column -> new TableCell<Disponibilidad, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    String hora = item.format(formatter);
                    setText(hora); // Asumiendo que el nombre es la propiedad que deseas mostrar
                }
            }
        });


        // Borra las columnas existentes antes de agregar las nuevas
        tableDisponibilidad.getColumns().clear();

        tableDisponibilidad.getColumns().addAll(especialidadColumn, medicoColumn, consultorioColumn, fechaColumn, horaColumn);

        tableDisponibilidad.getItems().clear();

        tableDisponibilidad.getItems().addAll(disponibilidadList);
    }

    @FXML
    private void registrarCita() throws Exception {
        System.out.println("Botón de registrar cita presionado");
        disponibilidadSeleccionada = tableDisponibilidad.getSelectionModel().getSelectedItem();
        String asunto = new String(txtAsunto.getText());
        String estado= "PROGRAMADO";
        BigDecimal costoB;
            try {
                if(txtImporte.getText().isEmpty() ||!txtImporte.getText().strip().matches("\\d+")) {
                    throw new IllegalArgumentException("Complete los campos.");
                }
                else {
                    costoB= new BigDecimal(txtImporte.getText());
                }
                System.out.println("Select: "+pacienteSeleccionado.toString());
                Cita cita = new Cita(pacienteSeleccionado, disponibilidadSeleccionada, asunto,estado, costoB);
                if(cita.esCitaValida())
                    registrarCitaServicio.GuardarCita(cita);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cita Registrada");
                alert.setContentText("Datos Registrados correctamente");
                alert.showAndWait();

            } catch (IllegalArgumentException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Datos Invalidos");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }

        }
    }