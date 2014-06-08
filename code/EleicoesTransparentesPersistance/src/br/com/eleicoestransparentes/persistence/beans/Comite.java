package br.com.eleicoestransparentes.persistence.beans;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Comite")
public class Comite extends EBean {

	@DatabaseField(generatedId = true)
	protected int id;
	
	@DatabaseField(canBeNull = false)
	protected String UF;

	@DatabaseField
	protected String municipio;

	@DatabaseField
	protected String tipo;
	
	@DatabaseField(foreign = true)
	protected Partido partido;
	
	@DatabaseField(unique = true)
	protected String sequencialComite;

	public String getUF() {
		return UF;
	}

	public void setUF(String uF) {
		UF = uF;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
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

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String getSequencialComite() {
		return sequencialComite;
	}

	public void setSequencialComite(String sequencialComite) {
		this.sequencialComite = sequencialComite;
	}

}