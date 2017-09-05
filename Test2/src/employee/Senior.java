package employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.text.Document;

import company.Office.Departments;
import document.SecretDocument;
import document.SimpleDocument;

public class Senior extends Employee {

	public Senior(String name, double salary) {
		super(name, salary);
		this.rank=new Random().nextInt(9)+12;
	}

	public void workOnDocument(){
		System.out.println(this.name + " is working on " + this.documents.getLast().getTitle());
		int chance=new Random().nextInt(101);
		if(chance<10){
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
		ArrayList<Employee> list=new ArrayList<>();
		for (HashSet<Employee> set : this.office.getEmployees().values()) {
			for (Employee employee : set) {
				if((employee instanceof Regular) || (employee instanceof Junior)){
					list.add(employee);
				}
			}
		}
		int index=new Random().nextInt(list.size());
		if(!(this.documents.getLast() instanceof SecretDocument)){
			list.get(index).addDocument(this.documents.getLast());
		}
		else{
			System.out.println(list.get(index).getName() + " cannot work on secret documents!");
		}
	}
	
	public void throwGarbageEmployees(){
		TreeSet<Employee> emp=new TreeSet<>((o1, o2)->(o1.countOfFails-o2.countOfFails)*-1);
		for (Employee employee : this.office.getEmployees().get(this.department)) {
			emp.add(employee);
		}
		
		if(emp.first().countOfFails>0){
			this.office.removeEmployee(emp.first());
			
			System.out.println("=========REMOVED EMPLOYEE INFO=========");
			System.out.println(emp.first().getName() + "-" + emp.first().getCountOfFails() + " fails.");
		}
		else{
			System.out.println("No bad employees in our department.");
		}
	}
}
