package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dao.CursoDAO;
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
    private CursoDAO cursoDAO = new CursoDAO();
    private Curso cursoEmEdicao = null; // Track curso being edited

    @FXML
    public void initialize() {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cargaHorariaColumn.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        loadCursos();
        cursoTableView.setItems(cursoList);
    }

    private void loadCursos() {
        cursoList.clear();
        cursoList.addAll(cursoDAO.findAll());
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

        if (cursoEmEdicao != null) {
            // Update existing curso
            cursoEmEdicao.setNome(nome);
            cursoEmEdicao.setCargaHoraria(cargaHoraria);
            cursoDAO.update(cursoEmEdicao);
            cursoEmEdicao = null; // Clear edit mode
        } else {
            // Create new curso
            Curso novoCurso = new Curso();
            novoCurso.setNome(nome);
            novoCurso.setCargaHoraria(cargaHoraria);
            cursoDAO.create(novoCurso);
        }

        loadCursos();
        clearFields();
    }

    @FXML
    private void onBtnCancelarCurso() {
        clearFields();
        cursoEmEdicao = null; // Clear edit state on cancel
    }

    @FXML
    private void onBtnNovoCurso() {
        clearFields();
        cursoEmEdicao = null; // Clear edit state on new
    }

    @FXML
    private void onBtnEditarCurso() {
        Curso cursoSelecionado = cursoTableView.getSelectionModel().getSelectedItem();
        if (cursoSelecionado != null) {
            cursoEmEdicao = cursoSelecionado; // Mark selected curso as being edited
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
            cursoDAO.delete(cursoSelecionado);
            loadCursos();
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um curso para remover.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onBtnAtualizarLista() {
        loadCursos();
    }

    private void clearFields() {
        nomeTextField.clear();
        cargaHorariaTextField.clear();
    }
}

