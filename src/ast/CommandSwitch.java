package ast;

import java.util.ArrayList;

public class CommandSwitch extends AbstractCommand{
	private String condition;
	private String cases = "";
	private ArrayList<AbstractCommand> choice;
	private ArrayList<AbstractCommand> defaultChoice;
	
	public CommandSwitch (String condition) {
		this.condition = condition;
	}
	
	public void addCase(String cases, ArrayList<AbstractCommand> choice) {
		this.cases += cases;
		if(this.choice == null)
			this.choice = choice;
		else
			this.choice.addAll(choice);
	}
	
	public void addDefault(ArrayList<AbstractCommand> defaultChoice) {
		this.defaultChoice = defaultChoice;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		int i = 0;
		String[] caselist = cases.split(":");
		StringBuilder str = new StringBuilder();
		str.append("switch (" + condition + ") {");
		for(AbstractCommand cmd: choice) {
			str.append("case " + caselist[i++] + ':' +  cmd.generateJavaCode() + "break;");
		}
		if(defaultChoice != null && defaultChoice.size() > 0)
			for(AbstractCommand cmd: defaultChoice) {
				str.append("default:" +  cmd.generateJavaCode());
			}
		str.append("}");

		return str.toString();
	}
	
	@Override
	public String generateCSharpCode() {
		// TODO Auto-generated method stub
		int i = 0;
		String[] caselist = cases.split(":");
		StringBuilder str = new StringBuilder();
		str.append("switch (" + condition + ") {");
		for(AbstractCommand cmd: choice) {
			str.append("case " + caselist[i++] + ':' +  cmd.generateCSharpCode() + "break;");
		}
		if(defaultChoice != null && defaultChoice.size() > 0)
			for(AbstractCommand cmd: defaultChoice) {
				str.append("default:" +  cmd.generateCSharpCode() + "break;");
			}
		str.append("}");

		return str.toString();
	}
}


