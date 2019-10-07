package aplication;
import java.util.List;

import entities.Pessoa;
import genericDao.PessoaDao;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application {

	private Scene scene;
	private AnchorPane pane;

	private TextField tfNome;
	private TextField tfEmail;
	private TextField tfNumber;
	private TextField tfEstado;
	private TextField tfCidade;
	
	
	private Label lblNomeProject;
	private Label lblNome;
	private Label lblEmail;
	private Label lblNumber;
	private Label lblEstado;
	private Label lblCidade;
	
	private Button btnCadastrar;
	private Button btnAlter;
	private Button btnRemove;
	private Button btnCancel;
	
	private ListView<Pessoa> listPessoa;
	private ObservableList<Pessoa> pessoas;
	private PessoaDao pDao = new PessoaDao();
	private Pessoa clickPessoa = new Pessoa();
	
	private Boolean alterBunttom= false;
	
	@Override
	public void start(Stage stage) {
		
		inicializacao();
		
		scene = new Scene(pane, 525, 500);

		stage.setScene(scene);

		stage.show();
		
		configuracao();	
		Acoes();
		listar();
	}
	
	private void clean() {
		tfNome.clear();
		tfEmail.clear();
		tfNumber.clear();
		tfCidade.clear();
		tfEstado.clear();
	}

	private void Acoes() {
		
		btnCadastrar.setOnAction(e -> {
			String nome = tfNome.getText();
			String email = tfEmail.getText();
			int number =Integer.parseInt(tfNumber.getText());
			String estado = tfEstado.getText();
			String cidade = tfCidade.getText();
			Pessoa p = new Pessoa(nome, email, number , estado, cidade);
			pDao.insert(p);
			pessoas.add(p);
			clean();
			listar();
			
		});
		
	    listPessoa.setOnMouseClicked(new EventHandler<MouseEvent>(){
	        @Override
	        public void handle(MouseEvent event) {
	            clickPessoa = listPessoa.getSelectionModel().getSelectedItem();
	            tfNome.setText(clickPessoa.getNome());
	            tfEmail.setText(clickPessoa.getEmail());
	            tfEstado.setText(clickPessoa.getEstado());
	            tfCidade.setText(clickPessoa.getCidade());
	            tfNumber.setText(Integer.toString(clickPessoa.getNumber()));
	            
	            
	            if (!alterBunttom) {	                
		            pane.getChildren().addAll(btnAlter, btnRemove, btnCancel);
		            pane.getChildren().removeAll(listPessoa, btnCadastrar);
		            alterBunttom = true;		            
	            }
	            
	   
	        }
	    });
	    
	    btnAlter.setOnAction(e ->{
	    	pDao.alter(clickPessoa.getId(), tfNome.getText(), tfEmail.getText(),Integer.parseInt(tfNumber.getText()),tfEstado.getText(), tfCidade.getText());
	    	pane.getChildren().removeAll(btnAlter, btnRemove, btnCancel);
	    	pane.getChildren().addAll(listPessoa, btnCadastrar);
	    	clean();
	    	listar();
	    	alterBunttom = false;
	    	
	    });
	    
	    
	    btnCancel.setOnAction(e -> {
	    	pane.getChildren().removeAll(btnAlter, btnRemove, btnCancel);
	    	pane.getChildren().addAll(listPessoa, btnCadastrar);
	    	clean();
	    	listar();
	    	alterBunttom = false;
	    });
	    
	    btnRemove.setOnAction(e ->{
	    	pDao.remove(clickPessoa.getId());
	    	pane.getChildren().removeAll(btnAlter, btnRemove, btnCancel);
	    	pane.getChildren().addAll(listPessoa, btnCadastrar);
	    	clean();
	    	listar();
	    	alterBunttom = false;
	    });
		 
	
	}

	private void configuracao() {
		lblNomeProject.setLayoutX(224);
		lblNomeProject.setLayoutY(25);
		lblNomeProject.setPrefWidth(101);
		lblNomeProject.setPrefHeight(25);
		
		
		lblNome.setLayoutX(14);
		lblNome.setLayoutY(59);
		tfNome.setLayoutX(14);
		tfNome.setLayoutY(76);
		tfNome.setPrefWidth(325);
		tfNome.setPrefHeight(25);

		lblEmail.setLayoutX(362);
		lblEmail.setLayoutY(59);
		tfEmail.setLayoutX(362);
		tfEmail.setLayoutY(76);
		
		lblNumber.setLayoutX(14);
		lblNumber.setLayoutY(111);
		tfNumber.setLayoutX(14);
		tfNumber.setLayoutY(128);
		
		lblEstado.setLayoutX(188);
		lblEstado.setLayoutY(111);
		tfEstado.setLayoutX(188);
		tfEstado.setLayoutY(128);
		
		lblCidade.setLayoutX(362);
		lblCidade.setLayoutY(111);	
		tfCidade.setLayoutX(362);
		tfCidade.setLayoutY(128);
		
		listPessoa.setPrefWidth(478);
		listPessoa.setPrefHeight(264);
		
		listPessoa.setLayoutX(24);
		listPessoa.setLayoutY(167);
				
		
		btnCadastrar.setLayoutX(414);
		btnCadastrar.setLayoutY(453);
		btnCadastrar.setPrefWidth(88);
		btnCadastrar.setPrefHeight(25);
		
		btnAlter.setLayoutX(414);
		btnAlter.setLayoutY(453);
		btnAlter.setPrefWidth(88);
		btnAlter.setPrefHeight(25);
		
		btnCancel.setLayoutX(24);
		btnCancel.setLayoutY(453);
		btnCancel.setPrefWidth(88);
		btnCancel.setPrefHeight(25);
		
		
		btnRemove.setLayoutX(295);
		btnRemove.setLayoutY(453);
		btnRemove.setPrefWidth(88);
		btnRemove.setPrefHeight(25);

	}
	
	private void listar() {
		List<Pessoa> pessoasDao = pDao.listar();
		pessoas = FXCollections.observableArrayList();
		listPessoa.setItems(pessoas);
		
		for(Pessoa p: pessoasDao) {
			if (p.getNome() == null) {
				p.setNome(" -- ");
			}
			if(p.getEmail() == null) {
				p.setEmail(" -- ");
			}
			if (p.getEstado() == null) {
				p.setEstado(" -- ");
			}
			if(p.getCidade() == null) {
				p.setCidade(" -- ");
			}
			if(p.getId() == 0) {
				p.setId(null);
			}
			pessoas.add(p);
		}
	}

	public void inicializacao() {
		
		
		pane = new AnchorPane();

		lblNomeProject = new Label();
		lblNomeProject.setText("PROJETO JAVAFX:");
		
		lblNome = new Label();
		lblNome.setText("Nome:");
		
		lblEmail = new Label();
		lblEmail.setText("Email:");
		
		lblNumber = new Label();
		lblNumber.setText("Telefone:");
		
		lblEstado = new Label();
		lblEstado.setText("Estado:");
		
		lblCidade = new Label();
		lblCidade.setText("Cidade:");
		
		tfEstado = new TextField();
		tfEstado.setPromptText("Digite o Estado");
		
		tfCidade = new TextField();
		tfCidade.setPromptText("Digite a Cidade");
		
		tfNome = new TextField();
		tfNome.setPromptText("Digite um nome");

		tfEmail = new TextField();
		tfEmail.setPromptText("Digite um email");
		
		
		tfNumber = new TextField();
		tfNumber.setPromptText("Digite seu Numero");
		
		btnCadastrar = new Button("Cadastrar");
		btnAlter = new Button("Alterar");
		btnRemove = new Button("Remover");
		btnCancel = new Button("Cancelar");

		pessoas = FXCollections.observableArrayList();
		
		listPessoa = new ListView<Pessoa>(FXCollections.emptyObservableList());
		listPessoa.setItems(pessoas);
		
		pane.getChildren().addAll(tfNome, tfEmail,tfNumber,tfCidade , tfEstado, lblCidade, lblEmail, lblEstado, lblNome, lblNomeProject, lblNumber, btnCadastrar, listPessoa);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
