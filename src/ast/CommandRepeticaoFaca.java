package ast;

import java.util.ArrayList;

public class CommandRepeticaoFaca extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> loop;

	public CommandRepeticaoFaca(String condition, ArrayList<AbstractCommand> loop) {
		this.condition = condition;
		this.loop = loop;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("do {");
		for (AbstractCommand cmd : loop) {
			str.append(cmd.generateJavaCode());
		}
		str.append("} while (" + condition + ");");

		return str.toString();
	}

	@Override
	public String generateCSharpCode() {
		StringBuilder str = new StringBuilder();
		str.append("do {");
		for (AbstractCommand cmd : loop) {
			str.append(cmd.generateCSharpCode());
		}
		str.append("} while (" + condition + ");");

		return str.toString();
	}
}