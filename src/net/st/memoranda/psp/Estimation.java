package net.st.memoranda.psp;

public class Estimation {
	String moduleDescription;
	int lineCount;
	
	public Estimation( String aModuleDescrpition, int aLineCount ) {
		this.moduleDescription = aModuleDescrpition;
		this.lineCount = aLineCount;
	}

	public String getModuleDescription() {
		return moduleDescription;
	}

	public void setModuleDescription(String aModuleDescription) {
		this.moduleDescription = aModuleDescription;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int aLineCount) {
		this.lineCount = aLineCount;
	}
}
