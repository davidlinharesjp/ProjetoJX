package genericDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageDatabase {
	
	private static String uri = "localhost";
	private static String port= "3306";
	private static String schema = "projeto_javaFx";
	private static String login= "root";
	private static String password= "123456";
	private static String url = "jdbc:mysql://"+ uri +":"+port+"/";
	
	private static void createSchema() {
		String sql = "CREATE SCHEMA IF NOT EXISTS "+ schema +";";
		
		
		try(Connection conn = DriverManager.getConnection(url,login, password)){
			Statement statement = conn.createStatement();
			statement.execute(sql);
//			Alert dialogoErro = new Alert(AlertType.ERROR);
//            dialogoErro.setTitle("Projeto JavaFx");
//            dialogoErro.setHeaderText(null);
//            dialogoErro.setContentText("SCHEMA CRIADO COM SUCESSO");
//            dialogoErro.showAndWait();
		}catch(SQLException e) {
//			Alert dialogoErro = new Alert(AlertType.ERROR);
//            dialogoErro.setTitle("Projeto JavaFx");
//            dialogoErro.setHeaderText(null);
//            dialogoErro.setContentText("Error ao Tentar Criar Schema!! \n"+ e.getMessage());
//            dialogoErro.showAndWait();
			e.getMessage();					
		}
	}
	
	private static void createTables() {
		
		
		String sql = "CREATE TABLE IF NOT EXISTS person_registration ("
				+ "id int NOT NULL AUTO_INCREMENT,"
				+ "name varchar(100),"
				+ "email varchar(100),"
				+ "number int(12),"
				+ "primary key(id))";
		
		
		
		try(Connection conn = DriverManager.getConnection(url+schema, login, password)){
			Statement statement = conn.createStatement();
			statement.execute(sql);
			System.out.println("aqui");
//			Alert dialogoErro = new Alert(AlertType.INFORMATION);
//            dialogoErro.setTitle("Projeto JavaFx");
//            dialogoErro.setHeaderText(null);
//            dialogoErro.setContentText("TABELA CRIADA");
//            dialogoErro.showAndWait();
		}catch(SQLException e) {
//			Alert dialogoErro = new Alert(AlertType.ERROR);
//            dialogoErro.setTitle("Projeto JavaFx");
//            dialogoErro.setHeaderText(null);
//            dialogoErro.setContentText("Error ao Tentar Criar a Tabela!! \n"+ e.getMessage());
//            dialogoErro.showAndWait();
			System.out.println(e.getMessage());
		}
	}
	
	public static void main ( String[] args) {
		createSchema();
		createTables();
	}
	

}








































