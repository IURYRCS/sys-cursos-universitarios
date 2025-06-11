package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        cursoColumn.setCellValueFactory(new PropertyValueFactory<>("curso"));

        cursoComboBox.setItems(cursoList);
        disciplinaTableView.setItems(disciplinaList);
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

        Disciplina novaDisciplina = new Disciplina();
        disciplinaList.add(novaDisciplina);
        clearInputFields();
    }

    @FXML
    private void onBtnCancelarDisciplina() {
        clearInputFields();
    }

    @FXML
    private void onBtnNovaDisciplina() {
        clearInputFields();
    }

    @FXML
    private void onBtnEditarDisciplina() {
        Disciplina selectedDisciplina = disciplinaTableView.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
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
            disciplinaList.remove(selectedDisciplina);
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma disciplina para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        disciplinaTableView.refresh();
    }

    private void clearInputFields() {
        nomeTextField.clear();
        descricaoTextArea.clear();
        cursoComboBox.setValue(null);
    }
}