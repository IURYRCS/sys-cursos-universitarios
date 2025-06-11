package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Curso; // Assuming you have a Curso model class

public class CursoController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField cargaHorariaTextField;

    @FXML
    private TableView<Curso> cursoTableView;

    @FXML
    private TableColumn<Curso, String> nomeColumn;

    @FXML
    private TableColumn<Curso, Integer> cargaHorariaColumn;


    private ObservableList<Curso> cursoList = FXCollections.observableArrayList(
    );

    @FXML
    public void initialize() {

        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cargaHorariaColumn.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));


        cursoTableView.setItems(cursoList);
    }

    @FXML
    private void salvarCurso() {

    }

    @FXML
    private void cancelarCurso() {

    }

    @FXML
    private void novoCurso() {

    }

    @FXML
    private void editarCurso() {

    }

    @FXML
    private void removerCurso() {

    }

    @FXML
    private void atualizarLista() {

    }
}
