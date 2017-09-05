package demo;

import java.util.ArrayList;
import java.util.Random;

import company.Office;
import company.Office.Departments;
import document.SecretDocument;
import document.SimpleDocument;
import employee.Employee;
import employee.Junior;
import employee.Regular;
import employee.Senior;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//creating an office
		Office office=new Office();
		
		//adding some employees
		for(int i=0; i<20; i++){
			Employee[] empsArr={new Junior("Junior" + (i+1), 800), new Regular("Regular" + (i+1), 1300), new Senior("Senior" + (i+1), 2000)};
			Departments[] deps={Departments.ADVERTISING, Departments.CLEANING, Departments.CORPORATIVE_CLIENTS, Departments.MANAGEMENT, Departments.PRIVATE_CLIENTS};
			office.addEmployee(deps[new Random().nextInt(deps.length)], empsArr[new Random().nextInt(empsArr.length)]);
		}
		
		//creating some documents
		ArrayList<SimpleDocument> docsList=new ArrayList<>();
		for(int i=0; i<50; i++){
			SimpleDocument[] docsArr={new SimpleDocument("SimpleDoc"+(i+1)), new SecretDocument("SecretDoc" + (i+1))};
			docsList.add(docsArr[new Random().nextInt(docsArr.length)]);
		}
		
		//send docs to the office
		office.addingDocs(docsList);
		
		//lets do some work
		for(int i=0; i<10; i++){
			office.setDocumentToEmp();
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		
		//print some employees reports
		for(int i=0; i<5; i++){
			Employee emp=office.randomEmployee();
			emp.printReport();
			emp.printSortedFails();
			System.out.println("--------------------------------------------------------------------------------------------------------");
		}
		
		//removing some loosers
		for(int i=0; i<5; i++){
			Senior emp=office.getRandomSenior();
			emp.throwGarbageEmployees();
		}
		
		//all office reports
		System.out.println();
		office.printBestEmployee();
		System.out.println();
		office.printFinishedDocument();
		System.out.println();
		office.printSummarySalaries();
		System.out.println();
		office.printWorstDep();
	}

}
