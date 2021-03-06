package br.ufba.eleicoestransparentes.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Eleicao")
public class Eleicao {
	
	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(unique = true)
	private String ano;

	/**
	 * Se municipal, estadual ou nacional
	 */
	@DatabaseField
	private String tipo;

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
