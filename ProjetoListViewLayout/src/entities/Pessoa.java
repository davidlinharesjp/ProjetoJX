package entities;

public class Pessoa {

	private String nome;
	private String email;
	private Integer number;
	private String estado;
	private String cidade;
	private Integer id;

	public Pessoa() {

	}

	public Pessoa(String nome, String email, Integer number, String estado, String  cidade) {
		super();
		this.nome = nome;
		this.email = email;
		this.number = number;
		this.estado = estado;
		this.cidade = cidade;

		
	}

	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ID: "+id+"  Nome: " + nome 
				+ "\nEmail: " + email +"   Numero: "+ number
				+"\nEstado: "+ estado+"   Cidade: "+ cidade;
	}
	
	

}
