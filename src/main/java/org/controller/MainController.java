package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.utils.PathFXML;

import java.io.FileInputStream;
import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    public void showCursoView() {
        loadView (PathFXML.pathFXML()+"CursoView.fxml");
    }

    @FXML
    public void showProfessorView() {
        loadView("/org/view/professor-view.fxml");
    }

    @FXML
    public void showDisciplinaView() {
        loadView("/org/view/disciplina-view.fxml");
    }

    @FXML
    public void showTurmaView() {
        loadView("/org/view/turma-view.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane view = loader.load(new FileInputStream(PathFXML.pathFXML()+ fxmlPath));
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }
}
