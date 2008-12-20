package br.com.goals.grafo.controle;

import java.util.ArrayList;
import java.util.HashSet;

import br.com.goals.grafo.modelo.Ponto;
import br.com.goals.grafo.persistencia.PontoDao;

/**
 * Responsavel por carregar conceitos iniciais<br>
 * Criado pois nao aguentava mais trocar as descricoes<br>
 * Agora as descri��es podem ser comentadas :-)
 * 
 * @author Fabio Issamu Oshiro
 *
 */
public class Conceitos {
	private static PontoDao pontoDao = PontoDao.getInstance();
	public static final String DUVIDA = "D�vida, algo desconhecido";
	public static final String MENSAGEM_AO_CAL = "Uma mensagem de algo emitida ao CAL";
	public static final String ALGO_MENSAGEIRO = "Algo n�o identificado que emite mensagem";
	public static final String INSTANCIA_ALGO = "Algo que falou";
	public static final String INSTANCIA_MENSAGEM = "Inst�ncia de uma mensagem";
	public static final String ATRIBUTO = "Atributo de algo";
	public static final String VALOR_ATRIBUTO = "Valor de um atributo";
	public static final String VERBO = "Verbo";
	public static final String VERBO_SER_PRESENTE = "Verbo 'ser' no presente (�)";
	public static final String ARTIGO = "Artigo o, a, the, etc";
	public static Ponto duvida;
	public static Ponto verbo;
	public static Ponto artigo;
	public static Ponto artigo_o;
	public static Ponto verboSerPresente;
	private static HashSet<Long> conceitosBasicosID = new HashSet<Long>();
	/**
	 * Carrega no banco alguns conceitos iniciais
	 */
	public static void carregarConceitos(){
		duvida = criar(DUVIDA);
		verbo = criar(VERBO);
		artigo = criar(ARTIGO);
		criar(MENSAGEM_AO_CAL);
		criar(ALGO_MENSAGEIRO);
		criar(ATRIBUTO);
		criar(VALOR_ATRIBUTO);
		
		Ponto pararSom = new Ponto("Comando para parar som");
		pararSom.setNome("sil�ncio");
		pararSom.setClasse("PararSom");
		criarOuAcharPorDescricao(pararSom);
		
		criarVerboSer();
		
		artigo_o = new Ponto("Primeiramente como artigo do sistema, mas pode ter outro sentido.");
		artigo_o.setNome("o");
		artigo_o=criarOuAcharPorNome(artigo_o);
		pontoDao.ligarSeDesligado(artigo, artigo_o);
	}
	private static void criarVerboSer(){
		verboSerPresente = new Ponto(VERBO_SER_PRESENTE);
		verboSerPresente.setClasse("SerPresente");
		verboSerPresente = criarOuAcharPorDescricao(verboSerPresente);
		pontoDao.ligarSeDesligado(verbo, verboSerPresente);
		
		Ponto palavraEh = new Ponto();
		palavraEh.setNome("�");
		palavraEh = criarOuAcharPorNome(palavraEh);
		pontoDao.ligarSeDesligado(verboSerPresente, palavraEh);
	}
	/**
	 * Facilitador
	 * @param descricao descri��o do ponto
	 * @return ponto criado
	 */
	private static Ponto criar(String descricao){
		Ponto ponto =pontoDao.acharOuCriarPorDescricao(descricao);
		conceitosBasicosID.add(ponto.getPontoId());
		return ponto;
	}
	private static Ponto criarOuAcharPorNome(Ponto ponto){
		ponto = pontoDao.acharOuCriarPorNome(ponto);
		conceitosBasicosID.add(ponto.getPontoId());
		return ponto;
	}
	private static Ponto criarOuAcharPorDescricao(Ponto ponto){
		ponto = pontoDao.acharOuCriarPorDescricao(ponto);
		conceitosBasicosID.add(ponto.getPontoId());
		return ponto;
	}
	public static boolean ehConceitoBasico(Ponto ponto){
		return conceitosBasicosID.contains(ponto.getPontoId());
	}
}
