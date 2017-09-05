package company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeSet;

import document.SecretDocument;
import document.SimpleDocument;
import employee.Employee;
import employee.Senior;

public class Office {

	public enum Departments{
		CORPORATIVE_CLIENTS, PRIVATE_CLIENTS, MANAGEMENT, ADVERTISING, CLEANING
	}
	
	private HashMap<Departments, HashSet<Employee>> employees;
	private LinkedList<SimpleDocument> documents;
	private ArrayList<SimpleDocument> realesedDocs;
	
	public Office() {
		this.employees=new HashMap<>();
		this.documents=new LinkedList<>();
		this.realesedDocs=new ArrayList<>();
	}
	
	public Map<Departments, HashSet<Employee>> getEmployees() {
		return Collections.unmodifiableMap(this.employees);
	}
	//adding an employee
	public void addEmployee(Departments dep, Employee emp){
		if(this.employees.containsKey(dep)){
			
			this.employees.get(dep).add(emp);
			emp.setDepartment(dep);
			emp.setOffice(this);
		}
		else{
			this.employees.put(dep, new HashSet<>());
			ArrayList<Senior> list=new ArrayList<>();
			if(emp instanceof Senior){
				this.employees.get(dep).add(emp);
				emp.setDepartment(dep);
				emp.setOffice(this);
			}
		}
	}
	
	//adding docs
	public void addingDocs(ArrayList<SimpleDocument> list){
		this.documents.addAll(list);
	}
	
	//setting document to employee
	public void setDocumentToEmp(){
		ArrayList<Employee> emp=new ArrayList<>();
		for (HashSet<Employee> set : this.employees.values()) {
			emp.addAll(set);
		}
		int empIndex=new Random().nextInt(emp.size());
		int docIndex=new Random().nextInt(this.documents.size());
		if(this.documents.get(docIndex) instanceof SecretDocument){
			if(emp.get(empIndex) instanceof Senior){
				emp.get(empIndex).addDocument(this.documents.get(docIndex));
				System.out.println(this.documents.get(docIndex).getTitle() + " is setted to " + emp.get(empIndex).getName());
				emp.get(empIndex).workOnDocument();
				this.realesedDocs.add(this.documents.get(docIndex));
			}
			else{
				System.out.println(emp.get(empIndex).getName() + " is not a Senior!");
				this.setDocumentToEmp();
			}
		}
		else{
			emp.get(empIndex).addDocument(this.documents.get(docIndex));
			System.out.println(this.documents.get(docIndex).getTitle() + " is setted to " + emp.get(empIndex).getName());
			emp.get(empIndex).workOnDocument();
			this.realesedDocs.add(this.documents.get(docIndex));
		}
	}
	
	//removing an employee
	public void removeEmployee(Employee employee){
		if(this.employees.containsValue(employee)){
			this.employees.get(employee.getDepartment()).remove(employee);
		}
	}
	
	//print all finished documents
	public void printFinishedDocument(){
		TreeSet<SimpleDocument> set=new TreeSet<>((o1, o2)->o2.getComplexity()-o2.getComplexity());
		set.addAll(this.realesedDocs);
		
		for (SimpleDocument simpleDocument : set) {
			System.out.println(simpleDocument.getTitle() + "-" + simpleDocument.getComplexity());
		}
	}
	
	//print summary salaries
	public void printSummarySalaries(){
		double sum=0;
		for (HashSet<Employee> emp : this.employees.values()) {
			for (Employee employee : emp) {
				sum+=employee.getSalary();
			}
		}
		System.out.println("SUMMARY SALARIES THAT OFFICE PAYS - " + sum);
	}
	
	//print best employee
	public void printBestEmployee(){
		TreeSet<Employee> emp=new TreeSet<>((o1, o2)->(o1.getCountOfFails()-o2.getCountOfFails()));
		for (HashSet<Employee> set : this.employees.values()) {
			for (Employee employee : set) {
				emp.add(employee);
			}
		}
		
		System.out.println("OUR BEST EMPLOYEE IS: " + emp.first().getName() + " with " + emp.first().getCountOfFails() + " fails");
	}
	
	//print worst department
	public void printWorstDep(){
		Departments name=Departments.ADVERTISING;
		int count=0;
		for (Entry<Departments, HashSet<Employee>> entry : this.employees.entrySet()) {
			int fails=0;
			for (Employee employee : entry.getValue()) {
				fails+=employee.getCountOfFails();
			}
			if(fails>count){
				count=fails;
				name=entry.getKey();
			}
		}
		
		System.out.println("OUR WORST DEPARTMENT IS: " + name);
	}
	
	//increase best employees salaries
	public void increaseSalaries(){
		TreeSet<Employee> emp=new TreeSet<>((o1, o2)->(o1.getCountOfFails()-o2.getCountOfFails())*-1);
		for (HashSet<Employee> set : this.employees.values()) {
			for (Employee employee : set) {
				emp.add(employee);
			}
		}
		
		ArrayList<Employee> list=new ArrayList<>();
		list.addAll(emp);
		System.out.println("THREE BEST EMPLOYEES: ");
		for(int i=0 ;i<2; i++){
			System.out.println(list.get(i).getName());
			list.get(i).setSalary(list.get(i).getSalary()+(0.1*list.get(i).getSalary()));
		}
	}
	
	//get random employee
	public Employee randomEmployee(){
		ArrayList<Employee> list=new ArrayList<>();
		for (HashSet<Employee> employee : this.employees.values()) {
			list.addAll(employee);
		}
		
		return list.get(new Random().nextInt(list.size()));
	}
	
	//get random senior
	public Senior getRandomSenior(){
		ArrayList<Senior> list=new ArrayList<>();
		for (HashSet<Employee> set : this.employees.values()) {
			for (Employee employee : set) {
				if(employee instanceof Senior){
					list.add((Senior) employee);
				}
			}
		}
		
		return list.get(new Random().nextInt(list.size()));
	}
}
