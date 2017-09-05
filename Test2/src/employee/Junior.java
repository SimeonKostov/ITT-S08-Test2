package employee;

import java.util.Random;
import java.util.TreeSet;

import javax.swing.text.Document;

import document.SimpleDocument;

public class Junior extends Employee {

	public Junior(String name, double salary) {
		super(name, salary);
		this.rank=new Random().nextInt(4)+1;
	}

	public void workOnDocument(){
		System.out.println(this.name + " is working on " + this.documents.getLast().getTitle());
		int chance=new Random().nextInt(101);
		if(chance>40){
			this.documents.getLast().setFail(true);
			this.countOfFails++;
			this.countOfDocuments++;
		}
		else{
			this.documents.getLast().setFail(false);
			this.countOfSuccess++;
			this.countOfDocuments++;
		}
	}
	
	public void printReport(){
		System.out.println("COUNT OF FINISHED DOCUMENTS: " + this.countOfDocuments);
		System.out.println("COUNT OF FAILS: " + this.countOfFails);
		System.out.println("COUNT OF SUCCESSFULL DOCUMENTS: " + this.countOfSuccess);
	}
	
	public void printSortedFails(){
		TreeSet<SimpleDocument> set=new TreeSet<>((o1,o2)-> o1.getTitle().compareTo(o2.getTitle()));
		for (SimpleDocument document : this.documents) {
			if( document.isFail()){
				set.add(document);
			}
		}
	}
	
	public void throwDocument(){
	
	}
}
