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

		String sql = "INSERT INTO person_registration (name, email, number , estado , cidade) VALUES (?,?,?,?,?)";

		try (Connection conn = new ConnectionFactory().getConnection()) {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pessoa.getNome());
			ps.setString(2, pessoa.getEmail());
			ps.setInt(3, pessoa.getNumber());
			ps.setString(4, pessoa.getEstado());
			ps.setString(5, pessoa.getCidade());
			ps.execute();
			Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
			dialogoErro.setTitle("Projeto JavaFx");
			dialogoErro.setHeaderText(null);
			dialogoErro.setContentText("Usuario inserido com Sucesso!");
			dialogoErro.showAndWait();

		} catch (SQLException e) {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
			dialogoErro.setTitle("Projeto JavaFx");
			dialogoErro.setHeaderText(null);
			dialogoErro.setContentText("Error ao Tentar inserir Usuario \n" + e.getMessage());
			dialogoErro.showAndWait();
		}
	}

	public List<Pessoa> listar() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		String sql = "SELECT * FROM person_registration";
		try (Connection conn = new ConnectionFactory().getConnection()) {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Pessoa pf = new Pessoa();
				pf.setNome(rs.getString("name"));
				pf.setEmail(rs.getString("email"));
				pf.setNumber(rs.getInt("number"));
				pf.setEstado(rs.getString("estado"));
				pf.setCidade(rs.getString("cidade"));
				pf.setId(rs.getInt("id"));
				pessoas.add(pf);
			}
		} catch (SQLException e) {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
			dialogoErro.setTitle("Projeto JavaFx");
			dialogoErro.setHeaderText(null);
			dialogoErro.setContentText("Error ao Tentar inserir Usuario \n" + e.getMessage());
			dialogoErro.showAndWait();
		}

		return pessoas;
	}

	public void remove(int id) {
		String sql = "DELETE FROM person_registration  WHERE id = ? ";
		try (Connection conn = new ConnectionFactory().getConnection()) {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, id);
			psmt.execute();
			Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
			dialogoErro.setTitle("Projeto JavaFx");
			dialogoErro.setContentText("Usuario Removido com Sucesso!! ");
			dialogoErro.setHeaderText(null);
			dialogoErro.showAndWait();
		} catch (SQLException e) {
			Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
			dialogoErro.setTitle("Projeto JavaFx");
			dialogoErro.setContentText("Error ao Tentar Deletar\n" + e.getMessage());
			dialogoErro.setHeaderText(null);
			dialogoErro.showAndWait();

		}

	}

	public void alter(Integer id, String nome, String email, Integer number, String estado, String cidade) {
		String sql = "UPDATE person_registration SET `name` =  ?  , `email` =  ? , `number` = ? , `estado` =  ?  , `cidade` =  ?  "
				+ " Where `id` = ?  ";
		try (Connection conn = new ConnectionFactory().getConnection()) {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, nome);
			psmt.setString(2, email);
			psmt.setInt(3, number);
			psmt.setString(4, estado);
			psmt.setString(5, cidade);
			psmt.setInt(6, id);
			psmt.execute();
			Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
			dialogoErro.setTitle("Projeto JavaFx");
			dialogoErro.setContentText("Cadastro Alterado com Sucesso!!!");
			dialogoErro.setHeaderText(null);
			dialogoErro.showAndWait();

		} catch (SQLException e) {
			System.out.println(e.getMessage() + "\n" + e.getSQLState());
			Alert dialogoAlert = new Alert(Alert.AlertType.ERROR);
			dialogoAlert.setHeaderText(null);
			dialogoAlert.setTitle("Projeto JavaFX");
			dialogoAlert.setContentText("Erro inesperadp!!\n Erro ao tentar Alterar Usuario\n " + e.getMessage());
			dialogoAlert.showAndWait();

		}

	}

}
