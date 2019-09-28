package entities;

public class Pessoa {

	private String nome;
	private String email;
	private int number;

	public Pessoa() {

	}

	public Pessoa(String nome, String email, int number) {
		super();
		this.nome = nome;
		this.email = email;
		this.number = number;
	}

	
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
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

	@Override
	public String toString() {
		return "Nome: " + nome 
				+ "\nEmail: " + email
				+"\nNumero: "+ number;
	}
	
	

}
