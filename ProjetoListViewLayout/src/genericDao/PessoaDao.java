package genericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Pessoa;
import javafx.scene.control.Alert;

public class PessoaDao {
	
	public void insert(Pessoa pessoa) {
		
		String sql = "INSERT INTO person_registration (name, email, number) VALUES (?,?,?)";
		
		try(Connection conn = new ConnectionFactory().getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getEmail());
			ps.setInt(3, pessoa.getNumber());
			ps.execute();
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Projeto JavaFx");
            dialogoErro.setHeaderText(null);
            dialogoErro.setContentText("Usuario inserido com Sucesso!");
            dialogoErro.showAndWait();
			
			
		}catch(SQLException e) {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setTitle("Projeto JavaFx");
            dialogoErro.setHeaderText(null);
            dialogoErro.setContentText("Error ao Tentar inserir Usuario \n"+ e.getMessage());
            dialogoErro.showAndWait();
		}
	}
	
	public List<Pessoa> listar() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM person_registration";
		try(Connection conn = new ConnectionFactory().getConnection()) {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Pessoa pf = new Pessoa();
				pf.setNome(rs.getString("name"));
				pf.setEmail(rs.getString("email"));
				pf.setNumber(rs.getInt("number"));
				pessoas.add(pf);
			}
			}catch (SQLException e) {
				System.out.println(e.getMessage());
				System.out.println("Erro ao listar pessoas!");
			}
		
		
		return pessoas;
	}

}
