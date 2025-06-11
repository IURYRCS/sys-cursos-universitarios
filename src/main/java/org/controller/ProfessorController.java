package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Professor;
import org.utils.Alerta;

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

    @FXML
    private Button BtnSalvarProfessor;

    @FXML
    private Button BtnCancelarProfessor;

    @FXML
    private Button BtnNovoProfessor;

    @FXML
    private Button BtnEditarProfessor;

    @FXML
    private Button BtnRemoverProfessor;

    @FXML
    private Button BtnAtualizarLista;

    private ObservableList<Professor> professorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        formacaoColumn.setCellValueFactory(new PropertyValueFactory<>("formacao"));

        professorTableView.setItems(professorList);
    }

    @FXML
    private void onBtnSalvarProfessor() {
        String nome = nomeTextField.getText();
        String email = emailTextField.getText();
        String formacao = formacaoTextField.getText();

        if (nome.isEmpty() || email.isEmpty() || formacao.isEmpty()) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        Professor novoProfessor = new Professor();
        professorList.add(novoProfessor);
        clearInputFields();
    }

    @FXML
    private void onBtnCancelarProfessor() {
        clearInputFields();
    }

    @FXML
    private void onBtnNovoProfessor() {
        clearInputFields();
    }

    @FXML
    private void onBtnEditarProfessor() {
        Professor selectedProfessor = professorTableView.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            nomeTextField.setText(selectedProfessor.getNome());
            emailTextField.setText(selectedProfessor.getEmail());
            formacaoTextField.setText(selectedProfessor.getFormacao());
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um professor para editar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnRemoverProfessor() {
        Professor selectedProfessor = professorTableView.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            professorList.remove(selectedProfessor);
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um professor para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        professorTableView.refresh();
    }

    private void clearInputFields() {
        nomeTextField.clear();
        emailTextField.clear();
        formacaoTextField.clear();
    }
}