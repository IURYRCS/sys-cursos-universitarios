package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dao.DisciplinaDAO;
import org.model.Curso;
import org.model.Disciplina;
import org.utils.Alerta;

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

    @FXML
    private Button BtnSalvarDisciplina;

    @FXML
    private Button BtnCancelarDisciplina;

    @FXML
    private Button BtnNovaDisciplina;

    @FXML
    private Button BtnEditarDisciplina;

    @FXML
    private Button BtnRemoverDisciplina;

    @FXML
    private Button BtnAtualizarLista;

    private ObservableList<Disciplina> disciplinaList = FXCollections.observableArrayList();
    private ObservableList<Curso> cursoList = FXCollections.observableArrayList();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO(); // Instance of DisciplinaDAO
    private Disciplina disciplinaEmEdicao = null; // Track disciplina being edited

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        cursoColumn.setCellValueFactory(new PropertyValueFactory<>("curso"));

        cursoComboBox.setItems(cursoList);
        disciplinaTableView.setItems(disciplinaList);
        loadDisciplinas(); // Load disciplines from the database
    }

    private void loadDisciplinas() {
        disciplinaList.clear();
        disciplinaList.addAll(disciplinaDAO.findAll()); // Load all disciplines from the database
    }

    @FXML
    private void onBtnSalvarDisciplina() {
        String nome = nomeTextField.getText();
        String descricao = descricaoTextArea.getText();
        Curso curso = cursoComboBox.getValue();

        if (nome.isEmpty() || descricao.isEmpty() || curso == null) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        if (disciplinaEmEdicao != null) {
            // Update existing disciplina
            disciplinaEmEdicao.setNome(nome);
            disciplinaEmEdicao.setDescricao(descricao);
            disciplinaEmEdicao.setCurso(curso);
            disciplinaDAO.update(disciplinaEmEdicao);
            disciplinaEmEdicao = null; // Clear edit mode
        } else {
            // Create new disciplina
            Disciplina novaDisciplina = new Disciplina();
            novaDisciplina.setNome(nome);
            novaDisciplina.setDescricao(descricao);
            novaDisciplina.setCurso(curso);
            disciplinaDAO.create(novaDisciplina);
        }

        loadDisciplinas(); // Refresh the list
        clearInputFields();
    }

    @FXML
    private void onBtnCancelarDisciplina() {
        clearInputFields();
        disciplinaEmEdicao = null; // Clear edit state on cancel
    }

    @FXML
    private void onBtnNovaDisciplina() {
        clearInputFields();
        disciplinaEmEdicao = null; // Clear edit state on new
    }

    @FXML
    private void onBtnEditarDisciplina() {
        Disciplina selectedDisciplina = disciplinaTableView.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
            disciplinaEmEdicao = selectedDisciplina; // Mark selected disciplina as being edited
            nomeTextField.setText(selectedDisciplina.getNome());
            descricaoTextArea.setText(selectedDisciplina.getDescricao());
            cursoComboBox.setValue(selectedDisciplina.getCurso());
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma disciplina para editar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnRemoverDisciplina() {
        Disciplina selectedDisciplina = disciplinaTableView.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
            disciplinaDAO.delete(selectedDisciplina); // Delete the selected disciplina from the database
            loadDisciplinas(); // Refresh the list
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma disciplina para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        loadDisciplinas(); // Refresh the list from the database
    }

    private void clearInputFields() {
        nomeTextField.clear();
        descricaoTextArea.clear();
        cursoComboBox.setValue(null);
    }
}