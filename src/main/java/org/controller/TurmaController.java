package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Disciplina;
import org.model.Professor;
import org.model.Turma;
import org.utils.Alerta;

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

    @FXML
    private Button BtnSalvarTurma;

    @FXML
    private Button BtnCancelarTurma;

    @FXML
    private Button BtnNovaTurma;

    @FXML
    private Button BtnEditarTurma;

    @FXML
    private Button BtnRemoverTurma;

    @FXML
    private Button BtnAtualizarLista;

    private ObservableList<Turma> turmaList = FXCollections.observableArrayList();
    private ObservableList<Disciplina> disciplinaList = FXCollections.observableArrayList();
    private ObservableList<Professor> professorList = FXCollections.observableArrayList();

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
    private void onBtnSalvarTurma() {
        String semestre = semestreTextField.getText();
        Disciplina disciplina = disciplinaComboBox.getValue();
        Professor professor = professorComboBox.getValue();
        String horario = horarioTextField.getText();

        if (semestre.isEmpty() || disciplina == null || professor == null || horario.isEmpty()) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        Turma novaTurma = new Turma();
        turmaList.add(novaTurma);
        clearInputFields();
    }

    @FXML
    private void onBtnCancelarTurma() {
        clearInputFields();
    }

    @FXML
    private void onBtnNovaTurma() {
        clearInputFields();
    }

    @FXML
    private void onBtnEditarTurma() {
        Turma selectedTurma = turmaTableView.getSelectionModel().getSelectedItem();
        if (selectedTurma != null) {
            semestreTextField.setText(String.valueOf(selectedTurma.getSemestre()));
            disciplinaComboBox.setValue(selectedTurma.getDisciplina());
            professorComboBox.setValue(selectedTurma.getProfessor());
            horarioTextField.setText(selectedTurma.getHorario());
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma turma para editar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnRemoverTurma() {
        Turma selectedTurma = turmaTableView.getSelectionModel().getSelectedItem();
        if (selectedTurma != null) {
            turmaList.remove(selectedTurma);
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma turma para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        turmaTableView.refresh();
    }

    private void clearInputFields() {
        semestreTextField.clear();
        disciplinaComboBox.setValue(null);
        professorComboBox.setValue(null);
        horarioTextField.clear();
    }
}