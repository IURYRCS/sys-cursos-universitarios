package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Curso;
import org.utils.Alerta;

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

    @FXML
    private Button BtnSalvarCurso;
    @FXML
    private Button BtnCancelarCurso;
    @FXML
    private Button BtnNovoCurso;
    @FXML
    private Button BtnEditarCurso;
    @FXML
    private Button BtnRemoverCurso;
    @FXML
    private Button BtnAtualizarLista;

    private ObservableList<Curso> cursoList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cargaHorariaColumn.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        cursoTableView.setItems(cursoList);
    }

    @FXML
    public void onBtnSalvarCurso() {
        String nome = nomeTextField.getText();
        String cargaHorariaText = cargaHorariaTextField.getText();

        if (nome.isEmpty() || cargaHorariaText.isEmpty()) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        int cargaHoraria;
        try {
            cargaHoraria = Integer.parseInt(cargaHorariaText);
        } catch (NumberFormatException e) {
            Alerta.exibirAlerta("Erro", null, "Carga horária deve ser um número.", Alert.AlertType.WARNING);
            return;
        }

        Curso novoCurso = new Curso();
        cursoList.add(novoCurso);
        clearFields();
    }

    @FXML
    private void onBtnCancelarCurso() {
        clearFields();
    }

    @FXML
    private void onBtnNovoCurso() {
        clearFields();
    }

    @FXML
    private void onBtnEditarCurso() {
        Curso cursoSelecionado = cursoTableView.getSelectionModel().getSelectedItem();
        if (cursoSelecionado != null) {
            nomeTextField.setText(cursoSelecionado.getNome());
            cargaHorariaTextField.setText(String.valueOf(cursoSelecionado.getCargaHoraria()));
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um curso para editar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnRemoverCurso() {
        Curso cursoSelecionado = cursoTableView.getSelectionModel().getSelectedItem();
        if (cursoSelecionado != null) {
            cursoList.remove(cursoSelecionado);
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um curso para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        cursoTableView.refresh();
    }

    private void clearFields() {
        nomeTextField.clear();
        cargaHorariaTextField.clear();
    }
}

