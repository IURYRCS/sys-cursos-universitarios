package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dao.ProfessorDAO;
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
    private ProfessorDAO professorDAO = new ProfessorDAO(); // Instance of ProfessorDAO
    private Professor professorEmEdicao = null; // Track professor being edited

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        formacaoColumn.setCellValueFactory(new PropertyValueFactory<>("formacao"));

        loadProfessores(); // Load professors from the database
        professorTableView.setItems(professorList);
    }

    private void loadProfessores() {
        professorList.clear();
        professorList.addAll(professorDAO.findAll()); // Load all professors from the database
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

        if (professorEmEdicao != null) {
            // Update existing professor
            professorEmEdicao.setNome(nome);
            professorEmEdicao.setEmail(email);
            professorEmEdicao.setFormacao(formacao);
            professorDAO.update(professorEmEdicao);
            professorEmEdicao = null; // Clear edit mode
        } else {
            // Create new professor
            Professor novoProfessor = new Professor();
            novoProfessor.setNome(nome);
            novoProfessor.setEmail(email);
            novoProfessor.setFormacao(formacao);
            professorDAO.create(novoProfessor);
        }

        loadProfessores(); // Refresh the list
        clearInputFields();
    }

    @FXML
    private void onBtnCancelarProfessor() {
        clearInputFields();
        professorEmEdicao = null; // Clear edit state on cancel
    }

    @FXML
    private void onBtnNovoProfessor() {
        clearInputFields();
        professorEmEdicao = null; // Clear edit state on new
    }

    @FXML
    private void onBtnEditarProfessor() {
        Professor selectedProfessor = professorTableView.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            professorEmEdicao = selectedProfessor; // Mark selected professor as being edited
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
            professorDAO.delete(selectedProfessor); // Delete the selected professor from the database
            loadProfessores(); // Refresh the list
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um professor para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        loadProfessores(); // Refresh the list from the database
    }

    private void clearInputFields() {
        nomeTextField.clear();
        emailTextField.clear();
        formacaoTextField.clear();
    }
}
