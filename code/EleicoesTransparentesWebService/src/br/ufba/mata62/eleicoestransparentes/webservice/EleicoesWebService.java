package br.ufba.mata62.eleicoestransparentes.webservice;

import java.util.List;

import br.ufba.mata62.eleicoestransparentes.EProperties;
import br.ufba.mata62.eleicoestransparentes.persistance.database.Comunicacao;
import br.ufba.mata62.eleicoestransparentes.persistance.database.Seed;
import br.ufba.mata62.eleicoestransparentes.persistance.database.beans.Bem;
import br.ufba.mata62.eleicoestransparentes.persistance.database.beans.Candidato;
import br.ufba.mata62.eleicoestransparentes.persistance.database.beans.SetorEconomico;
import br.ufba.mata62.eleicoestransparentes.persistance.database.beans.Transacao;
import br.ufba.mata62.eleicoestransparentes.persistance.database.logicbeans.DoadorWrapper;
import br.ufba.mata62.eleicoestransparentes.utils.Path;
import br.ufba.mata62.eleicoestransparentes.utils.ReadCVS;
import br.ufba.mata62.eleicoestransparentes.webservice.util.MD5;

import com.google.gson.Gson;



public class EleicoesWebService {

	private static final String CHAVE_SEGURANCA = "eleicoes-transparentes-mata62-ufba";

	public String consultaTransacaoPartido(int numero, String UF, String tipoTransacao){
		try {
			Gson gson = new Gson();
			Comunicacao comm = new Comunicacao();
			float valor = comm.consultaTransacaoPartido(numero, UF, tipoTransacao);
			comm.close();
			return gson.toJson(valor);
		} catch (Exception e) {
			return e.getCause().getMessage();
		}
	}
	
	public String consultaTransacaoCandidato(String sequencialCandidato, String tipoTransacao){
		try {
			Gson gson = new Gson();
			Comunicacao comm = new Comunicacao();
			float valor = comm.consultaTransacaoCandidato(sequencialCandidato, tipoTransacao);
			comm.close();
			return gson.toJson(valor);
		} catch (Exception e) {
			return e.getCause().getMessage();
		}
	}
	
	public String rankingMaioresDoadoresPessoaJuridica(String UF){
		try {
			Gson gson = new Gson();
			Comunicacao comm = new Comunicacao();
			List<DoadorWrapper> rankingPJ = comm.rankingMaioresDoadoresPessoaJuridica(UF);
			comm.close();
			return gson.toJson(rankingPJ);
		} catch (Exception e) {
			return e.getCause().getMessage();
		}
	}
	
	public String rankingMaioresDoadoresPessoaFisica(String UF){
		try {
			Gson gson = new Gson();
			Comunicacao comm = new Comunicacao();
			List<DoadorWrapper> rankingPF = comm.rankingMaioresDoadoresPessoaFisica(UF);
			comm.close();
			return gson.toJson(rankingPF);
		} catch (Exception e) {
			return e.getCause().getMessage();
		}
	}
	

	public String createTables(String chaveSeguranca){
		try{
			Gson gson = new Gson();
			if(MD5.crypt(CHAVE_SEGURANCA).equals(chaveSeguranca)){
				try{
					Seed.createTables();
				}catch (Exception e) {
					e.printStackTrace();
					return gson.toJson(e.getMessage());
				}
				return gson.toJson("Tabelas criadas com sucesso!");
			}else{
				return gson.toJson("Chave de Segurança inválida!!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public String parserDadosTSE(String chaveSeguranca){
		try{
			Gson gson = new Gson();
			if(MD5.crypt(CHAVE_SEGURANCA).equals(chaveSeguranca)){
				try{
					Seed.createTables();
					
					Comunicacao comm = new Comunicacao();
					
					for (Candidato t : ReadCVS.readCandidatos(Path.UFS[0])) {
						comm.insereCandidato(t);
					}
				
					for (Transacao t : ReadCVS.readPrestacaoContasPartidoReceita(Path.UFS[0])) {
						comm.insereTransacao(t);
					}
					
					for (Transacao t : ReadCVS.readPrestacaoContasPartidoDespesa(Path.UFS[0])) {
						comm.insereTransacao(t);
					}
					
					for (Transacao t : ReadCVS.readPrestacaoContasCandidatoDespesa(Path.UFS[0])) {
						comm.insereTransacao(t);
					}
					
					for (Transacao t : ReadCVS.readPrestacaoContasCandidatoReceita(Path.UFS[0])) {
						comm.insereTransacao(t);
					}
					
					for (Bem b : ReadCVS.readBens(Path.UFS[0])) {
						comm.insereBem(b);
					}
					
					comm.close();
				}catch (Exception e) {
					e.printStackTrace();
					return gson.toJson(e.getMessage());
				}
				return gson.toJson("Parser concluído com sucesso!");
			}else{
				return gson.toJson("Chave de Segurança inválida!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public String consultaApplicationPath(){
		try{
			Gson gson = new Gson();
			return gson.toJson(EProperties.findAppDirectory());
		}catch (Exception e) {
			return e.getCause().getMessage();
		}
	}
	
	public String consultaPathRoot(){
		try{
			Gson gson = new Gson();
			return gson.toJson(EProperties.getPathRoot());
		}catch (Exception e) {
			return e.getCause().getMessage();
		}
	}

	public String consultaSetoresEconomico(){

		try {
			Gson gson = new Gson();
			Comunicacao comm = new Comunicacao();
			List<SetorEconomico> list = comm.consultaSetoresEconomico();
			comm.close();
			return gson.toJson(list);
		} catch (Exception e) {
			return e.getCause().getMessage();
		}

	}

	public String consultaCandidatos(){

		try {
			Gson gson = new Gson();
			Comunicacao comm = new Comunicacao();
			List<Candidato> list = comm.consultaCandidatos();
			comm.close();
			return gson.toJson(list);
		} catch (Exception e) {
			return e.getCause().getMessage();
		}

	}
}
