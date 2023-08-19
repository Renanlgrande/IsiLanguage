// Generated from IsiLang.g4 by ANTLR 4.7.1
 
	package Isi.parser;
	import Isi.datastructures.IsiSymbol;
	import Isi.datastructures.IsiVariable;
	import Isi.datastructures.IsiSymbolTable;
	import exceptions.IsiSemanticException;
	import ast.IsiProgram;
	import ast.AbstractCommand;
	import ast.CommandLeitura;
	import ast.CommandEscrita;
	import ast.CommandAtribuicao;
	import ast.CommandRepeticao;
	import ast.CommandRepeticaoFaca;
	import ast.CommandDecisao;
	import ast.CommandSwitch;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, OP_rel=19, AP=20, FP=21, AC=22, FC=23, END=24, OP=25, ID=26, 
		NUMBER=27, TEXTO=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
		"T__17", "OP_rel", "AP", "FP", "AC", "FC", "END", "OP", "ID", "NUMBER", 
		"TEXTO", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'programa'", "'fimprog.'", "'declare'", "','", "'numero'", "'texto'", 
		"'leia'", "'escreva'", "'se'", "'entao'", "'senao'", "'enquanto'", "'faca'", 
		"':='", "'+'", "'-'", "'*'", "'/'", null, "'('", "')'", "'{'", "'}'", 
		"'.'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "OP_rel", "AP", "FP", "AC", 
		"FC", "END", "OP", "ID", "NUMBER", "TEXTO", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


		private int _tipo;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private String cases = "";
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> loop;
		private ArrayList<AbstractCommand> choice;
		private ArrayList<AbstractCommand> defaultChoice;
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("AVISO: Símbolo ''"+id+"'' não declarado");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}
		
		public void generateCode(){
			program.generateTarget();
		}
		public void verificaVariavelNaoInicializada(String id) {
			if (!symbolTable.exists(id)) {
				System.out.println("AVISO: Variável '" + id + "' não declarada");
			}

			IsiSymbol symbol = symbolTable.get(id);
			if (!symbol.isInitialized()) {
				System.out.println("AVISO: Variável '" + id + "' não tem valor inicial");
			}
		}

		public void marcarVariavelComoInicializada(String id) {
			IsiSymbol symbol = symbolTable.get(id);
			symbol.setInitialized(true);
		}


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00d4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20"+
		"\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\5\24\u00a5\n\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\5\33\u00b4\n\33\3\33\7\33\u00b7\n\33\f\33\16\33\u00ba"+
		"\13\33\3\34\6\34\u00bd\n\34\r\34\16\34\u00be\3\34\3\34\6\34\u00c3\n\34"+
		"\r\34\16\34\u00c4\5\34\u00c7\n\34\3\35\3\35\6\35\u00cb\n\35\r\35\16\35"+
		"\u00cc\3\35\3\35\3\36\3\36\3\36\3\36\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37\3\2\t\4\2>>@@\5\2,-//\61\61"+
		"\4\2C\\c|\5\2\62;C\\c|\3\2\62;\6\2\"\"\62;C\\c|\5\2\13\f\17\17\"\"\2\u00dc"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\3=\3\2\2\2\5F\3\2\2\2\7O\3\2\2\2\tW\3\2\2\2\13Y\3\2\2\2\r`\3\2"+
		"\2\2\17f\3\2\2\2\21k\3\2\2\2\23s\3\2\2\2\25v\3\2\2\2\27|\3\2\2\2\31\u0082"+
		"\3\2\2\2\33\u008b\3\2\2\2\35\u0090\3\2\2\2\37\u0093\3\2\2\2!\u0095\3\2"+
		"\2\2#\u0097\3\2\2\2%\u0099\3\2\2\2\'\u00a4\3\2\2\2)\u00a6\3\2\2\2+\u00a8"+
		"\3\2\2\2-\u00aa\3\2\2\2/\u00ac\3\2\2\2\61\u00ae\3\2\2\2\63\u00b0\3\2\2"+
		"\2\65\u00b3\3\2\2\2\67\u00bc\3\2\2\29\u00c8\3\2\2\2;\u00d0\3\2\2\2=>\7"+
		"r\2\2>?\7t\2\2?@\7q\2\2@A\7i\2\2AB\7t\2\2BC\7c\2\2CD\7o\2\2DE\7c\2\2E"+
		"\4\3\2\2\2FG\7h\2\2GH\7k\2\2HI\7o\2\2IJ\7r\2\2JK\7t\2\2KL\7q\2\2LM\7i"+
		"\2\2MN\7\60\2\2N\6\3\2\2\2OP\7f\2\2PQ\7g\2\2QR\7e\2\2RS\7n\2\2ST\7c\2"+
		"\2TU\7t\2\2UV\7g\2\2V\b\3\2\2\2WX\7.\2\2X\n\3\2\2\2YZ\7p\2\2Z[\7w\2\2"+
		"[\\\7o\2\2\\]\7g\2\2]^\7t\2\2^_\7q\2\2_\f\3\2\2\2`a\7v\2\2ab\7g\2\2bc"+
		"\7z\2\2cd\7v\2\2de\7q\2\2e\16\3\2\2\2fg\7n\2\2gh\7g\2\2hi\7k\2\2ij\7c"+
		"\2\2j\20\3\2\2\2kl\7g\2\2lm\7u\2\2mn\7e\2\2no\7t\2\2op\7g\2\2pq\7x\2\2"+
		"qr\7c\2\2r\22\3\2\2\2st\7u\2\2tu\7g\2\2u\24\3\2\2\2vw\7g\2\2wx\7p\2\2"+
		"xy\7v\2\2yz\7c\2\2z{\7q\2\2{\26\3\2\2\2|}\7u\2\2}~\7g\2\2~\177\7p\2\2"+
		"\177\u0080\7c\2\2\u0080\u0081\7q\2\2\u0081\30\3\2\2\2\u0082\u0083\7g\2"+
		"\2\u0083\u0084\7p\2\2\u0084\u0085\7s\2\2\u0085\u0086\7w\2\2\u0086\u0087"+
		"\7c\2\2\u0087\u0088\7p\2\2\u0088\u0089\7v\2\2\u0089\u008a\7q\2\2\u008a"+
		"\32\3\2\2\2\u008b\u008c\7h\2\2\u008c\u008d\7c\2\2\u008d\u008e\7e\2\2\u008e"+
		"\u008f\7c\2\2\u008f\34\3\2\2\2\u0090\u0091\7<\2\2\u0091\u0092\7?\2\2\u0092"+
		"\36\3\2\2\2\u0093\u0094\7-\2\2\u0094 \3\2\2\2\u0095\u0096\7/\2\2\u0096"+
		"\"\3\2\2\2\u0097\u0098\7,\2\2\u0098$\3\2\2\2\u0099\u009a\7\61\2\2\u009a"+
		"&\3\2\2\2\u009b\u00a5\t\2\2\2\u009c\u009d\7>\2\2\u009d\u00a5\7?\2\2\u009e"+
		"\u009f\7@\2\2\u009f\u00a5\7?\2\2\u00a0\u00a1\7#\2\2\u00a1\u00a5\7?\2\2"+
		"\u00a2\u00a3\7?\2\2\u00a3\u00a5\7?\2\2\u00a4\u009b\3\2\2\2\u00a4\u009c"+
		"\3\2\2\2\u00a4\u009e\3\2\2\2\u00a4\u00a0\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"(\3\2\2\2\u00a6\u00a7\7*\2\2\u00a7*\3\2\2\2\u00a8\u00a9\7+\2\2\u00a9,"+
		"\3\2\2\2\u00aa\u00ab\7}\2\2\u00ab.\3\2\2\2\u00ac\u00ad\7\177\2\2\u00ad"+
		"\60\3\2\2\2\u00ae\u00af\7\60\2\2\u00af\62\3\2\2\2\u00b0\u00b1\t\3\2\2"+
		"\u00b1\64\3\2\2\2\u00b2\u00b4\t\4\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b8"+
		"\3\2\2\2\u00b5\u00b7\t\5\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\66\3\2\2\2\u00ba\u00b8\3\2\2"+
		"\2\u00bb\u00bd\t\6\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc"+
		"\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c6\3\2\2\2\u00c0\u00c2\7\60\2\2"+
		"\u00c1\u00c3\t\6\2\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2"+
		"\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c0\3\2\2\2\u00c6"+
		"\u00c7\3\2\2\2\u00c78\3\2\2\2\u00c8\u00ca\7$\2\2\u00c9\u00cb\t\7\2\2\u00ca"+
		"\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2"+
		"\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\7$\2\2\u00cf:\3\2\2\2\u00d0\u00d1"+
		"\t\b\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\b\36\2\2\u00d3<\3\2\2\2\f\2\u00a4"+
		"\u00b3\u00b6\u00b8\u00be\u00c4\u00c6\u00ca\u00cc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}