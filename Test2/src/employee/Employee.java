package employee;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.TreeSet;

import javax.swing.text.Document;

import company.Office;
import company.Office.Departments;
import document.SimpleDocument;

public abstract class Employee {

	protected String name;
	protected int rank;
	protected double salary;
	protected int countOfFails;
	protected LinkedList<SimpleDocument> documents;
	protected int countOfDocuments;
	protected int countOfSuccess;
	protected Departments department;
	protected Office office;
	
	public Employee(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
		this.documents=new LinkedList();
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public int getCountOfFails() {
		return countOfFails;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public Departments getDepartment() {
		return department;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void workOnDocument();
	
	public void printReport(){
		System.out.println("COUNT OF FINISHED DOCUMENTS: " + this.countOfDocuments);
		System.out.println("COUNT OF FAILS: " + this.countOfFails);
		System.out.println("COUNT OF SUCCESSFULL DOCUMENTS: " + this.countOfSuccess);
	}
	
	public void printSortedFails(){
		TreeSet<SimpleDocument> set=new TreeSet<>((o1,o2)-> o1.getTitle().compareTo(o2.getTitle()));
		for (SimpleDocument document : this.documents) {
			if(document.isFail()){
				set.add(document);
			}
		}
	}
	
	public void throwDocument(){
		
	}
	
	public void addDocument(SimpleDocument document){
		this.documents.add(document);
	}
	
	private double chanceOfFail(SimpleDocument doc){
		return doc.getComplexity()/this.rank;
	}
	
	public void setDepartment(Departments department) {
		this.department = department;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}
}
