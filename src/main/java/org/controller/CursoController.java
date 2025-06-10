package org.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.model.Curso;
import org.dao.CursoDAO;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CursoController implements Initializable {

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

    private ObservableList<Curso> cursoList = FXCollections.observableArrayList();

    private CursoDAO cursoDAO = new CursoDAO(); // Instantiate your DAO

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize TableView columns
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cargaHorariaColumn.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));

        // Load initial data
        loadCursos();
    }

    private void loadCursos() {
        List<Curso> cursos = cursoDAO.findAll(); // Get data from DAO
        cursoList.clear();
        cursoList.addAll(cursos);
        cursoTableView.setItems(cursoList);
    }

    @FXML
    private void saveCurso() {
        // Get data from form
        String nome = nomeTextField.getText();
        String cargaHorariaStr = cargaHorariaTextField.getText();

        try {
            int cargaHoraria = Integer.parseInt(cargaHorariaStr);

            // Create a new Curso object
            Curso curso = new Curso();
            curso.setNome(nome);
            curso.setCargaHoraria(cargaHoraria);

            // Save to database using DAO
            cursoDAO.create(curso);

            // Refresh the table
            loadCursos();

            // Clear the form
            clearForm();

        } catch (NumberFormatException e) {
            // Handle invalid input (e.g., show an error message)
            System.err.println("Invalid Carga Horária: " + e.getMessage()); // Replace with proper error handling
        }
    }

    @FXML
    private void cancel() {
        // Clear the form
        clearForm();
    }

    private void clearForm() {
        nomeTextField.setText("");
        cargaHorariaTextField.setText("");
    }

    @FXML
    private void novoCurso() {
        // Implement logic to create a new Curso
        System.out.println("Novo Curso clicked");
    }

    @FXML
    private void editarCurso() {
        // Implement logic to edit an existing Curso
        System.out.println("Editar Curso clicked");
    }

    @FXML
    private void removerCurso() {
        // Implement logic to remove a Curso
        System.out.println("Remover Curso clicked");
    }

    @FXML
    private void atualizarLista() {
        // Implement logic to refresh the list of Cursos
        loadCursos();
        System.out.println("Atualizar Lista clicked");
    }
}
