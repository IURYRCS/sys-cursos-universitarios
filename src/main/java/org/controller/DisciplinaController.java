package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Curso;
import org.model.Disciplina;

public class DisciplinaController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextArea descricaoTextArea;

    @FXML
    private ComboBox<Curso> cursoComboBox;

    @FXML
    private TableView<Disciplina> disciplinaTableView;

    @FXML
    private TableColumn<Disciplina, String> nomeColumn;

    @FXML
    private TableColumn<Disciplina, String> descricaoColumn;

    @FXML
    private TableColumn<Disciplina, Curso> cursoColumn;

    private ObservableList<Disciplina> disciplinaList = FXCollections.observableArrayList();
    private ObservableList<Curso> cursoList = FXCollections.observableArrayList(
    );

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        cursoColumn.setCellValueFactory(new PropertyValueFactory<>("curso"));

        cursoComboBox.setItems(cursoList);
        disciplinaTableView.setItems(disciplinaList);
    }

    @FXML
    private void salvarDisciplina() {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextArea.getText();
        Curso curso = cursoComboBox.getValue();



        clearInputFields();
    }

    @FXML
    private void cancelarDisciplina() {
        clearInputFields();
    }

    @FXML
    private void novaDisciplina() {
        clearInputFields();
    }

    @FXML
    private void editarDisciplina() {
        Disciplina selectedDisciplina = disciplinaTableView.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
            nomeTextField.setText(selectedDisciplina.getNome());
            descricaoTextArea.setText(selectedDisciplina.getDescricao());
            cursoComboBox.setValue(selectedDisciplina.getCurso());
        } else {
            showAlert("Nenhuma Disciplina Selecionada", "Por favor, selecione uma disciplina para editar.");
        }
    }

    @FXML
    private void removerDisciplina() {
        Disciplina selectedDisciplina = disciplinaTableView.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
            disciplinaList.remove(selectedDisciplina);
        } else {
            showAlert("Nenhuma Disciplina Selecionada", "Por favor, selecione uma disciplina para remover.");
        }
    }

    @FXML
    private void atualizarLista() {
        disciplinaTableView.refresh();
    }

    private void clearInputFields() {
        nomeTextField.clear();
        descricaoTextArea.clear();
        cursoComboBox.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
