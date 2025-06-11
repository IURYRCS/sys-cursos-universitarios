package org.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.utils.PathFXML;
import org.utils.Alerta;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class MainController {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public void showCursoView() {
        loadView("CursoView.fxml");
    }

    @FXML
    public void showProfessorView() {
        loadView("ProfessorView.fxml");
    }

    @FXML
    public void showDisciplinaView() {
        loadView("DisciplinaView.fxml");
    }

    @FXML
    public void showTurmaView() {
        loadView("TurmaView.fxml");
    }

    public void loadView(String arquivoFXML) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            URL location = Paths.get(PathFXML.pathFXML(), arquivoFXML).toUri().toURL();
            fxmlLoader.setLocation(location);
            Parent root = fxmlLoader.load();

            contentPane.getChildren().clear();
            contentPane.getChildren().add(root);

            AnchorPane.setTopAnchor(root, 0.0);
            AnchorPane.setBottomAnchor(root, 0.0);
            AnchorPane.setLeftAnchor(root, 0.0);
            AnchorPane.setRightAnchor(root, 0.0);

        } catch (IOException e) {
            Alerta.exibirAlerta("Error", "Erro ao carregar a view", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (RuntimeException e) {
            Alerta.exibirAlerta("Error", "Erro ao carregar a view", e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
