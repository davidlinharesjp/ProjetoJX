package genericDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.scene.control.Alert;

public class ConnectionFactory {
	
	public Connection getConnection() {
		Connection coon = null;
		String urlSchema = "jdbc:mysql://localhost:3306/projeto_javaFx";
		try {
			coon = DriverManager.getConnection(urlSchema, "root", "123456");
		} catch(SQLException e) {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Projeto JavaFx");
            dialogoErro.setHeaderText(null);
            dialogoErro.setContentText("Error ao Tentar Conectar \n"+ e.getMessage());
            dialogoErro.showAndWait();
		}
		return coon;
	}
}
