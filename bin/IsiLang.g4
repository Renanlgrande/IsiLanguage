grammar IsiLang;

@header { 
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
}

@members{
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
}

	prog	: 'programa' declara bloco 'fimprog.'
			{	
				ArrayList<IsiSymbol> lista = symbolTable.getAll();
				for (IsiSymbol symbol : lista) {
					if (symbol.checkUsage() == false)
						System.out.println("AVISO: Variável ''" + symbol.getName() + "'' declarada, mas não utilizada");
				}
				program.setVarTable(symbolTable);
				program.setComandos(stack.pop());				
			}
		;
		
declara	: (declaraVar)+ 
		;

declaraVar	: 'declare' tipo ID {
				                  _varName = _input.LT(-1).getText();
				                  _varValue = null;
				                  symbol = new IsiVariable(_varName, _tipo, _varValue);
				                  if (!symbolTable.exists(_varName)){
				                     symbolTable.add(symbol);	
				                  }
				                  else{
				                  	 throw new IsiSemanticException("Simbolo "+_varName+" já foi declarado");
				                  }
                    			} 
							 (',' 
							 	ID {
					                  _varName = _input.LT(-1).getText();
					                  _varValue = null;
					                  symbol = new IsiVariable(_varName, _tipo, _varValue);
					                  if (!symbolTable.exists(_varName)){
					                     symbolTable.add(symbol);	
					                  }
					                  else{
					                  	 throw new IsiSemanticException("Simbolo "+_varName+" já foi declarado");
					                  }
				                   } 
							 )* '.'
			;
			
tipo	: 'numero' { _tipo = IsiVariable.NUMBER;}
		| 'texto'  { _tipo = IsiVariable.TEXT;}
		;
	
bloco	: { curThread = new ArrayList<AbstractCommand>();
			stack.push(curThread);
		  }
		(cmd)+
		;
		
cmd     : cmdLeitura { System.out.println("Execução do comando de leitura concluída."); }
        | cmdEscrita { System.out.println("Execução do comando de escrita concluída."); }
        | cmdIf      { System.out.println("Execução do comando de decisão concluída."); }
        | cmdWhile   { System.out.println("Execução do comando de repetição (While) concluída."); }
        | cmdDoWhile { System.out.println("Execução do comando de repetição (DoWhile) concluída."); }
        | cmdExpr    { System.out.println("Execução do comando de atribuição concluída."); }
		;
		
cmdLeitura 	: 'leia' AP 
	ID {
	verificaVariavelNaoInicializada($ID.text);
	_readID = $ID.text;
	} FP END {
	IsiVariable var = (IsiVariable)symbolTable.get(_readID);
	CommandLeitura cmd = new CommandLeitura(_readID, var);
	stack.peek().add(cmd);
	}
;
			
cmdEscrita	: 'escreva' AP 
						(
						TEXTO { _writeID = _input.LT(-1).getText();}
						| ID {
							verificaVariavelNaoInicializada($ID.text);
							_writeID = $ID.text;
							marcarVariavelComoInicializada(_writeID);
						}
						) 
						FP 
						END
			  			{
			  				CommandEscrita cmd = new CommandEscrita(_writeID);
			  				stack.peek().add(cmd);
			  			}
			;
			
cmdIf		: 'se' AP {listaFalse = null;}
				   expr { _exprDecision = _input.LT(-1).getText(); }
				   (OP_rel { _exprDecision += _input.LT(-1).getText(); }
				   	expr { _exprDecision += _input.LT(-1).getText(); }
				   )+ 
				   FP
			  'entao' AC 
			  		  { curThread = new ArrayList<AbstractCommand>();
			  		  	stack.push(curThread);
			  		  }
			  		  (cmd)+ 
			  		  FC 
			  		  {
			  		  	listaTrue = stack.pop();
			  		  }
			  ('senao' 
			  	AC 
			  	{ curThread = new ArrayList<AbstractCommand>();
			  	  stack.push(curThread);
			    }
			  	(cmd)+ 
			  	FC
			  	{
			  		listaFalse = stack.pop();
			  	}
			  )?
			  {
			  	CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
			  	stack.peek().add(cmd);
			  }
			;
			

cmdWhile	: 'enquanto' AP 
						 expr { _exprDecision = _input.LT(-1).getText(); }
						 (OP_rel { _exprDecision += _input.LT(-1).getText(); }
						 expr { _exprDecision += _input.LT(-1).getText(); }
						 )+ 
						 FP
			  			 AC
			  			 { curThread = new ArrayList<AbstractCommand>();
			  		  	   stack.push(curThread);
			  		  	 }
			  			 (cmd)+ 
			  			 FC
			  			 {
			  			   loop = stack.pop();
			  			   CommandRepeticao cmd = new CommandRepeticao(_exprDecision, loop);
			  		       stack.peek().add(cmd);
			  			 }
			;

cmdDoWhile  : 'faca' AC
	  { curThread = new ArrayList<AbstractCommand>();
	    stack.push(curThread);
	  }
	  (cmd)+
	  FC
	  'enquanto' AP
	  expr { _exprDecision = _input.LT(-1).getText(); }
	  (OP_rel { _exprDecision += _input.LT(-1).getText(); }
	    expr { _exprDecision += _input.LT(-1).getText(); }
	  )+
	  FP
	  END
	  {
	    loop = stack.pop();
	    CommandRepeticaoFaca cmd = new CommandRepeticaoFaca(_exprDecision, loop);
	    stack.peek().add(cmd);
	  }
	;

cmdExpr		: ID {
				_varName = $ID.text;
				_exprID = $ID.text;
				_tipo = symbolTable.get(_varName).getType();
				if (!_tipoInicializado) {
					marcarVariavelComoInicializada(_varName);
					_tipoInicializado = true;
				}
			}
			':=' {
				_exprContent = "";
				if (_tipo == IsiVariable.TEXT) {
					System.out.println("Atribuição de valor inicial em variável TEXTO: '" + _varName + "'");
				}
			}
			(expr {
				if (_tipo != 0) {
					throw new RuntimeException("Variável '" + _varName + "' é do tipo TEXTO, mas foi encontrado NÚMERO");
				}
			}
			| TEXTO {
				if (_tipo != 1) {
					throw new RuntimeException("Variável '" + _varName + "' é do tipo NÚMERO, mas foi encontrado TEXTO");
				} else {
					_exprContent += _input.LT(-1).getText();
				}
			})+
			END {
				symbolTable.get(_varName).varUsed();
				CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
				stack.peek().add(cmd);
			}
			;
			
OP_rel		: '<' | '>' | '<=' | '>=' | '!=' | '=='
			;
			
expr		: termo 
			  ('+' | '-' | '*' | '/') termo
			  {
			    _exprContent += _input.LT(-1).getText();
			    System.out.println("Operações Aritméticas executadas corretamente");
			  }
			| termo ('+' | '-' | '*' | '/') termo
			  {
			    _exprContent += _input.LT(-1).getText();
			    System.out.println("Operações Aritméticas executadas corretamente");
			  }
			| termo
			;

termo		: fator 
			  '*' { _exprContent += _input.LT(-1).getText();}
			  fator 
			| fator 
			  '/' { _exprContent += _input.LT(-1).getText();}
			  fator 
			| fator
			;
			
fator		: NUMBER { _exprContent += _input.LT(-1).getText();}
			| ID {
			verificaVariavelNaoInicializada($ID.text);
			IsiVariable var = (IsiVariable)symbolTable.get($ID.text);
			var.varUsed();
			_exprContent += $ID.text;
		}
;
			
AP	: '('
	;
	
FP	: ')'
	;
	
AC	: '{'
	;

FC	: '}'
	;
	
END : '.'
	;
	
OP	: '+' | '-' | '*' | '/'
	;
		
ID	: ([a-z] | [A-Z]) ([a-z] | [A-Z] | [0-9])*
	;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?
		;
		
TEXTO	: '"' ([a-z] | [A-Z] | [0-9] | ' ')+ '"'
		;
		
WS	: (' ' | '\t' | '\n' | '\r') -> skip;