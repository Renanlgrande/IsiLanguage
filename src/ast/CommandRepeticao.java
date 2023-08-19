package ast;

import java.util.ArrayList;

public class CommandRepeticao extends AbstractCommand {
	private String condition;
	private ArrayList<AbstractCommand> loop;

	public CommandRepeticao(String condition, ArrayList<AbstractCommand> loop) {
		this.condition = condition;
		this.loop = loop;
	}

	@Override
	public String generateJavaCode() {
		StringBuilder str = new StringBuilder();
		str.append("while (" + condition + ") {");
		for (AbstractCommand cmd : loop) {
			str.append(cmd.generateJavaCode());
		}
		str.append("}");

		return str.toString();
	}

	@Override
	public String generateCSharpCode() {
		StringBuilder str = new StringBuilder();
		str.append("while (" + condition + ") {");
		for (AbstractCommand cmd : loop) {
			str.append(cmd.generateCSharpCode());
		}
		str.append("}");

		return str.toString();
	}
}
