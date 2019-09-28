package aplication;
import java.util.List;

import entities.Pessoa;
import genericDao.PessoaDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application {

	private Scene scene;
	private AnchorPane pane;

	private TextField tfNome;
	private TextField tfEmail;
	private TextField tfNumber;
	
	private Button btnCadastrar;

	private ListView<Pessoa> listPessoa;
	private ObservableList<Pessoa> pessoas;
	private PessoaDao pDao = new PessoaDao();
	
	
	@Override
	public void start(Stage stage) {
		
		inicializacao();
		
		scene = new Scene(pane, 400, 300);

		stage.setScene(scene);

		stage.show();
		
		configuracao();
	

		
		Acoes();
		listar();
	}

	private void Acoes() {
		btnCadastrar.setOnAction(e -> {
			String nome = tfNome.getText();
			String email = tfEmail.getText();
			int number =Integer.parseInt(tfNumber.getText());
			
			Pessoa p = new Pessoa(nome, email, number );
			pDao.insert(p);
			pessoas.add(p);

			tfNome.clear();
			tfEmail.clear();
			tfNumber.clear();
		});
	}

	private void configuracao() {
		listPessoa.setPrefWidth(200);
		listPessoa.setPrefHeight(150);
		
		listPessoa.setLayoutX(180);
		listPessoa.setLayoutY(10);
		
		tfNome.setLayoutX(10);
		tfNome.setLayoutY(10);
		
		tfEmail.setLayoutX(10);
		tfEmail.setLayoutY(tfNome.getHeight() + 20);
		
		tfNumber.setLayoutX(10);
		tfNumber.setLayoutY(tfEmail.getHeight() + 55);
		
		btnCadastrar.setLayoutY(tfNumber.getLayoutY()+tfNumber.getHeight() + 10);
		btnCadastrar.setLayoutX(tfNumber.getWidth()-btnCadastrar.getWidth());
	}
	
	private void listar() {
		List<Pessoa> pessoasDao = pDao.listar();
		
		for(Pessoa p: pessoasDao) {
			pessoas.add(p);
		}
	}

	public void inicializacao() {
		pane = new AnchorPane();

		tfNome = new TextField();
		tfNome.setPromptText("Digite um nome");

		tfEmail = new TextField();
		tfEmail.setPromptText("Digite um email");
		
		
		tfNumber = new TextField();
		tfNumber.setPromptText("Digite seu Numero");
		
		btnCadastrar = new Button("Cadastrar");

		pessoas = FXCollections.observableArrayList();
		
		listPessoa = new ListView<Pessoa>();
		
		listPessoa.setItems(pessoas);
		
		pane.getChildren().addAll(tfNome, tfEmail,tfNumber, btnCadastrar, listPessoa);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
