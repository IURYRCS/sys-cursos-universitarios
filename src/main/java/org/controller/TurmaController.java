package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Disciplina;
import org.model.Professor;
import org.model.Turma;

public class TurmaController {

    @FXML
    private TextField semestreTextField;

    @FXML
    private ComboBox<Disciplina> disciplinaComboBox;

    @FXML
    private ComboBox<Professor> professorComboBox;

    @FXML
    private TextField horarioTextField;

    @FXML
    private TableView<Turma> turmaTableView;

    @FXML
    private TableColumn<Turma, String> semestreColumn;

    @FXML
    private TableColumn<Turma, Disciplina> disciplinaColumn;

    @FXML
    private TableColumn<Turma, Professor> professorColumn;

    @FXML
    private TableColumn<Turma, String> horarioColumn;

    private ObservableList<Turma> turmaList = FXCollections.observableArrayList();
    private ObservableList<Disciplina> disciplinaList = FXCollections.observableArrayList(
           );
    private ObservableList<Professor> professorList = FXCollections.observableArrayList(
      );

    @FXML
    public void initialize() {
        semestreColumn.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        disciplinaColumn.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        professorColumn.setCellValueFactory(new PropertyValueFactory<>("professor"));
        horarioColumn.setCellValueFactory(new PropertyValueFactory<>("horario"));

        disciplinaComboBox.setItems(disciplinaList);
        professorComboBox.setItems(professorList);
        turmaTableView.setItems(turmaList);
    }

    @FXML
    private void salvarTurma() {


        clearInputFields();
    }

    @FXML
    private void cancelarTurma() {
        clearInputFields();
    }

    @FXML
    private void novaTurma() {
        clearInputFields();
    }

    @FXML
    private void editarTurma() {

    }

    @FXML
    private void removerTurma() {

    }

    @FXML
    private void atualizarLista() {
    }

    private void clearInputFields() {
      }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
