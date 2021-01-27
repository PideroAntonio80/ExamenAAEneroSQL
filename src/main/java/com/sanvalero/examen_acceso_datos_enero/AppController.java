package com.sanvalero.examen_acceso_datos_enero;

import com.sanvalero.examen_acceso_datos_enero.dao.EquipoDAO;
import com.sanvalero.examen_acceso_datos_enero.dao.JugadorDAO;
import com.sanvalero.examen_acceso_datos_enero.domain.Equipo;
import com.sanvalero.examen_acceso_datos_enero.domain.Jugador;
import com.sanvalero.examen_acceso_datos_enero.util.AlertUtils;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Creado por @ author: Pedro Orós
 * el 27/01/2021
 */

public class AppController implements Initializable {
    public TextField tfNombreEquipo, tfPatrocinador, tfNombreJugador, tfApellidos, tfEquipo;
    public ComboBox<String> cbCategoria;
    public Label lAviso;
    public TableView<Equipo> tvDatos;
    public Button bGuardar, bBorrar, bNuevo, bReset;

    private EquipoDAO equipoDAO;
    private JugadorDAO jugadorDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            equipoDAO = new EquipoDAO();
            equipoDAO.conectar();

            jugadorDAO = new JugadorDAO();
            jugadorDAO.conectar();

        } catch (ClassNotFoundException cnfe) {
            AlertUtils.mostrarError("Error al iniciar la aplicación");
        } catch (SQLException sql) {
            AlertUtils.mostrarError("No se ha podido conectar");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        fijarColumnasTabla();

        cargarDatosEnTabla();

        lAviso.setText("Cargando Datos...");
        transicionLabelAviso(3);

        bGuardar.setDisable(true);
        bBorrar.setDisable(true);
    }

    @FXML
    public void mostrarDato(Event event) {
        Equipo equipo = tvDatos.getSelectionModel().getSelectedItem();
        cargarDato(equipo);

        bGuardar.setDisable(true);
        bBorrar.setDisable(false);
    }

    @FXML
    public void nuevo(Event event) {
        limpiarCajas();

        bGuardar.setDisable(false);
        bBorrar.setDisable(true);
    }

    @FXML
    public void guardar(Event event) {
        try {
            String nombre = tfNombreEquipo.getText();
            String patrocinador = tfPatrocinador.getText();
            String categoria = cbCategoria.getValue();

            if(nombre.equals("")) {
                AlertUtils.mostrarError("Debes rellenar el campo Nombre de Equipo");
                return;
            }

            Equipo equipo = new Equipo(nombre, patrocinador, categoria);

            equipoDAO.guardar(equipo);
            cargarDatosEnTabla();
            lAviso.setText("Equipo Registrado");
            transicionLabelAviso(3);
            bGuardar.setDisable(true);
            bBorrar.setDisable(true);

        } catch (SQLException throwables) {
            AlertUtils.mostrarError("Error al guardar");
        }
    }

    @FXML
    public void borrar(Event event) {
        AlertUtils.mostrarConfirmacion("borrar");
        try {

            String nombre = tfNombreEquipo.getText();
            String patrocinador = tfPatrocinador.getText();
            //String categoria = cbCategoria.getValue();

            Equipo equipo = new Equipo(nombre, patrocinador);

            equipoDAO.eliminar(equipo);
            cargarDatosEnTabla();
            lAviso.setText("Equipo borrado");
            transicionLabelAviso(3);
            bGuardar.setDisable(true);
            bBorrar.setDisable(true);
        } catch (SQLException throwables) {
            AlertUtils.mostrarError("Error al borrar datos");
        }
    }

    @FXML
    public void registrar(Event event) {
        try {
            String nombre = tfNombreJugador.getText();
            String apellidos = tfApellidos.getText();
            int equipo = Integer.parseInt(tfEquipo.getText());

            if(nombre.equals("")) {
                AlertUtils.mostrarError("Debes rellenar el campo Nombre");
                return;
            }

            Jugador jugador = new Jugador(nombre, apellidos, equipo);

            jugadorDAO.guardar(jugador);
            lAviso.setText("Jugador registrado");
            transicionLabelAviso(3);
            limpiarCajas();

        } catch (SQLException throwables) {
            AlertUtils.mostrarError("Error al guardar");
        }
    }

    @FXML
    public void cambiarEquipo(Event event) {
        String nombre = tfNombreJugador.getText();
        String apellidos = tfApellidos.getText();
        int equipo = Integer.parseInt(tfEquipo.getText());

        if(equipo == 0) {
            AlertUtils.mostrarError("Debes elegir un equipo");
            return;
        }

        Jugador jugador = new Jugador(nombre, apellidos);

        try {
            jugadorDAO.modificar(equipo, jugador);
            lAviso.setText("Jugador modificado");
            transicionLabelAviso(3);
            limpiarCajas();

        } catch (SQLException throwables) {
            AlertUtils.mostrarError("Error al cambiar equipo");
        }
    }

    @FXML
    public void reset(Event event) {
        limpiarCajas();
    }

    public void limpiarCajas() {
        tfNombreEquipo.setText("");
        tfPatrocinador.setText("");
        cbCategoria.setValue("<Selecciona Categoria>");
        tfNombreJugador.setText("");
        tfNombreEquipo.setText("");
    }

    public void cargarDato(Equipo equipo) {
        tfNombreEquipo.setText(equipo.getNombre());
        tfPatrocinador.setText(equipo.getPatrocinador());
        //cbCategoria.setValue(equipo.getCategoria());
    }

    public void fijarColumnasTabla() {
        Field[] fields = Equipo.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("id") || field.getName().equals("categoria"))
                continue;

            TableColumn<Equipo, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            tvDatos.getColumns().add(column);
        }
        tvDatos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void cargarDatosEnTabla() {
        tvDatos.getItems().clear();
        try {
            List<Equipo> datos = equipoDAO.listar();
            tvDatos.setItems(FXCollections.observableArrayList(datos));

            String[] tipos = new String[]{"<Selecciona Categoria>", "Primera", "Segunda", "Tercera"};
            cbCategoria.setItems(FXCollections.observableArrayList(tipos));
        } catch (Exception e) {
            AlertUtils.mostrarError("Error al cargar los datos");
        }
    }

    public void transicionLabelAviso(int segundos) {
        lAviso.setVisible(true);
        PauseTransition visiblePause = new PauseTransition((Duration.seconds(segundos)));
        visiblePause.setOnFinished(event -> lAviso.setVisible(false));
        visiblePause.play();
    }
}
