package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Professor;

public class ProfessorController {

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField formacaoTextField;

    @FXML
    private TableView<Professor> professorTableView;

    @FXML
    private TableColumn<Professor, String> nomeColumn;

    @FXML
    private TableColumn<Professor, String> emailColumn;

    @FXML
    private TableColumn<Professor, String> formacaoColumn;

    private ObservableList<Professor> professorList = FXCollections.observableArrayList(
         );

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        formacaoColumn.setCellValueFactory(new PropertyValueFactory<>("formacao"));

        professorTableView.setItems(professorList);
    }

    @FXML
    private void salvarProfessor() {
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String formacao = formacaoTextField.getText();


        clearInputFields();
    }

    @FXML
    private void cancelarProfessor() {
        clearInputFields();
    }

    @FXML
    private void novoProfessor() {
        clearInputFields();
    }

    @FXML
    private void editarProfessor() {
        Professor selectedProfessor = professorTableView.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            nomeTextField.setText(selectedProfessor.getNome());
            emailTextField.setText(selectedProfessor.getEMail());
            formacaoTextField.setText(selectedProfessor.getFormacao());
        } else {
            showAlert("Nenhum Professor Selecionado", "Por favor, selecione um professor para editar.");
        }
    }

    @FXML
    private void removerProfessor() {
        Professor selectedProfessor = professorTableView.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            professorList.remove(selectedProfessor);
        } else {
            showAlert("Nenhum Professor Selecionado", "Por favor, selecione um professor para remover.");
        }
    }

    @FXML
    private void atualizarLista() {
        professorTableView.refresh();
    }

    private void clearInputFields() {
        nomeTextField.clear();
        emailTextField.clear();
        formacaoTextField.clear();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
