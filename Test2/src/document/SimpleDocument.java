package document;

import java.util.Random;

public class SimpleDocument {

	protected String title;
	protected int complexity;
	protected boolean isFail;
	
	public SimpleDocument(String title) {
		this.title = title;
		this.complexity=new Random().nextInt(20)+1;
	}

	public int getComplexity() {
		return complexity;
	}
	
	public void setFail(boolean isFail) {
		this.isFail = isFail;
	}
	
	public String getTitle() {
		return title;
	}
	
	public boolean isFail() {
		return isFail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleDocument other = (SimpleDocument) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
