package ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import Isi.datastructures.IsiSymbol;
import Isi.datastructures.IsiSymbolTable;

public class IsiProgram {
	private IsiSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;
	
	public void generateTarget() {
		StringBuilder str1 = new StringBuilder();
		str1.append("import java.util.Scanner;");
		str1.append("public class MainClass{ ");
		str1.append(" public static void main(String args[]){ ");
		str1.append("  Scanner _key = new Scanner(System.in);\n");
		for(IsiSymbol symbol: varTable.getAll()) {
			str1.append(symbol.generateJavaCode() + "\n");
		}
		for(AbstractCommand command: comandos) {
			str1.append(command.generateJavaCode() + "\n");
		}
		str1.append(" }");
		str1.append("}");
		
		StringBuilder str2 = new StringBuilder();
		str2.append("using System;");
		str2.append("using System.IO;");
		str2.append("public class MainClass{ ");
		str2.append(" public static void Main(string[] args){ ");
		for(IsiSymbol symbol: varTable.getAll()) {
			str2.append(symbol.generateCSharpCode() + "\n");
		}
		for(AbstractCommand command: comandos) {
			str2.append(command.generateCSharpCode() + "\n");
		}
		str2.append(" }");
		str2.append("}");
		
		try {
			FileWriter fr1 = new FileWriter(new File("MainClass.java"));
			fr1.write(str1.toString());
			fr1.close();
			
			FileWriter fr2 = new FileWriter(new File("MainClass.cs"));
			fr2.write(str2.toString());
			fr2.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public IsiSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(IsiSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
