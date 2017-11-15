package pojo;


import java.io.Serializable;

/**
 * Created by ALE on 09/11/2017.
 */

public class Veiculo implements Serializable {

	private long id;
	private String placa;
	private String cor;
	private String marca;
	private boolean novo;
	
	public Veiculo() {
	}

	public Veiculo(long id, String placa, String cor, String marca, boolean novo) {
		super();
		this.id = id;
		this.placa = placa;
		this.cor = cor;
		this.marca = marca;
		this.novo = novo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isNovo() {
		return novo;
	}

	public void setNovo(boolean novo) {
		this.novo = novo;
	}


	// APARECE EM BUSCAR TODOS
	@Override
	public String toString() {
		return placa+":"+cor+":"+marca+":"+(novo?"novo":"semi-novo");
	}
}
