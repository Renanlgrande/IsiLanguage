package Isi.datastructures;

public abstract class IsiSymbol {
	
	protected String name;
	
	public abstract String generateJavaCode();
	
	public abstract String generateCSharpCode();
	
	public IsiSymbol(String name) {
		this.name = name;
		
	}

	public abstract int getType();
	
	public abstract void varUsed();
	
	public abstract boolean checkUsage();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "IsiSymbol [name=" + name + "]";
	}
	
	private boolean initialized = false; 
    
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }
    
    public boolean isInitialized() {
        return initialized;
    }
	
}