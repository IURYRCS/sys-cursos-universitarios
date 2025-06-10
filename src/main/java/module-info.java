module org.syscursosuniversitarios {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires org.postgresql.jdbc;
    opens org to javafx.fxml;
    exports org;
    opens org.controller to javafx.fxml;
    opens org.model to org.hibernate.orm.core;
    exports org.controller; // Adicione esta linha
}