package Isi.main;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import Isi.parser.IsiLangLexer;
import Isi.parser.IsiLangParser;
import exceptions.IsiSemanticException;

// Responsável por criar a interação com o usuário
public class MainClass {
	public static void main(String[] args) {
		try {
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			// ler arquivo de entrada do analizador léxico
			lexer = new IsiLangLexer(CharStreams.fromFileName("input1.isi"));
			
			// cria um fluxo de tokens para o PARSER
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			// cria um parser a partir do tokenStream
			parser = new IsiLangParser(tokenStream);
			
			parser.prog();
			
			System.out.println("Compilação bem-sucedida!!!");
			
			parser.exibeComandos();
			
			parser.generateCode();
			
		}
		catch(IsiSemanticException ex) {
			System.err.println("Erro semântico - "+ex.getMessage());
		}
		catch(Exception ex) {
			System.err.println("ERRO " + ex.getMessage());
		}
	}
}