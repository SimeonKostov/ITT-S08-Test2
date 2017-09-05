package document;

public class SecretDocument extends SimpleDocument{

	private String password;
	public SecretDocument(String title) {
		super(title);
	}

	public void setPassword(String password) {
		if(password.length()<5){
			System.out.println("This password is not safety!");
		}
		else{
			this.password = password;
		}
	}
}
