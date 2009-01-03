package br.com.goals.utils;

import java.net.URLEncoder;

public class StringUtils {
	/**
	 * Dexa apenas a-z A-Z 0-9 e ' ' espaco em branco
	 * @param s
	 * @return
	 */
	public static String removePontuacao(String s){
		StringBuffer buf = new StringBuffer();
		int len = (s == null ? -1 : s.length());
		for (int i = 0; i < len; i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9' || c==' ') {
				buf.append(c);
			}
		}
		return buf.toString();
	}
	public static String stringEntre(String source,String a,String b) throws Exception{
		int ini = source.indexOf(a)+a.length();
		int fim = source.indexOf(b,ini);
		return source.substring(ini,fim);
	}
	public static String removeAcentos(String pStrTexto) {
		return pStrTexto.replace("�", "a").replace("�", "a").replace("�", "a").replace("�", "a").replace("�", "a").replace("�", "A").replace("�", "A").replace("�", "A").replace("�", "A").replace("�", "A").replace("�", "e").replace("�", "e").replace("�", "e").replace("�", "e")
				.replace("�", "E").replace("�", "E").replace("�", "E").replace("�", "E").replace("�", "i").replace("�", "i").replace("�", "i").replace("�", "i").replace("�", "I").replace("�", "I").replace("�", "I").replace("�", "I").replace("�", "o").replace("�", "o").replace(
						"�", "o").replace("�", "o").replace("�", "o").replace("�", "O").replace("�", "O").replace("�", "O").replace("�", "O").replace("�", "O").replace("�", "u").replace("�", "u").replace("�", "u").replace("�", "u").replace("�", "U").replace("�", "U").replace(
						"�", "U").replace("�", "U").replace("�", "n").replace("�", "N").replace("�", "y").replace("�", "y").replace("�", "Y").replace("�", "c").replace("�", "C").replace("�", "").replace("`", "").replace("~", "").replace("^", "").replace("�", "");
	}

	public static String tratarCaracteresEspeciaisRegex(String puro) {
		return puro.replace("\\","\\\\").replace("$", "\\$");
	}

	public static String urlEncode(String valor) {
		String retorno = "";

		try {

			retorno = URLEncoder.encode(valor, "UTF-8");

		} catch (Exception e) {
		}

		return retorno;
	}
}
