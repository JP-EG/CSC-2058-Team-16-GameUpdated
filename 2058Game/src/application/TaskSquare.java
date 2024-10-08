package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TaskSquare {

	private List<Task> tasks;
	
	public TaskSquare() {

		tasks = new ArrayList<>();

		Task t1 = new Task(1, "Task 1(a)", "Conduct site assessment", 
				new Step("Labour Hours", "Time Units", 10, 2, "Technical Expertise", "Community Trust Points", 1, 2));

		Task t2 = new Task(2, "Task 1(b)", "Obtain necessary permits and approvals",
				new Step("Budget", "Time Units", 2000, 3,"Permit Tokens", "Budget", 3, 1000));
		
		Task t3 = new Task(3, "Task 1(c)", "Conduct an Enviornmental Impact Assessment",
				new Step("Permit Tokens", "Budget", 2, 500, "Budget", "Community Trust Points", 1500, 3));
	 
		Task t4 = new Task(4, "Task 2(a)", "Procure solar panels, pumps, pipes, and other equipment",
				new Step("Budget", "Material Units", 5000, 5, "Equipment Efficiency Points", "Budget", 2, 2000));

		Task t5 = new Task(5, "Task 2(b)", "Manage the budget for equipment procurement efficiently",
				new Step("Budget", "Technical Expertise", 1000, 2, "Budget", "Equipment Efficiency Points", 3000, 3));
		
		Task t6 = new Task(6, "Task 3(a)", "Assemble and install the equipment",
				new Step("Material Units", "Labour Hours", 5, 10, "Technical Expertise", "Labour Hours", 2, 8));
		
		Task t7 = new Task(7, "Task 3(b)", "Ensure the proper connection of the solar-powered system",
				new Step("Equipment Efficiency Points", "Technical Expertise", 5, 1, "Community Trust Points", "Technical Expertise", 1, 2));
		
		Task t8 = new Task(8, "Task 3(c)", "Test the water pump to ensure it operates effectively",
				new Step("Equipment Efficiency Points", "Time Units", 4, 4, "Labour Hours", "Time Units", 8, 3));
		
		Task t9 = new Task(9, "Task 4(a)", "Establish a maintenance plan for routine inspections and repairs",
				new Step("Budget", "Technical Expertise", 2000, 3, "Permit Tokens", "Labour Hours", 2, 4));
		
		Task t10 = new Task(10, "Task 4(b)", "Train local community members to operate and maintain the water pump",
				new Step("Labour Hours", "Community Engagement", 6, 1, "Budget", "Community Trust Points", 1000, 2));
		
		Task t11 = new Task(11, "Task 4(c)", "Develop a community-based funding model to support ongoing maintenance",
				new Step("Budget", "Community Engagement", 3000, 2, "Material Units", "Budget", 10, 4000));
		
		Task t12 = new Task(12, "Task 5(a)", "Engage with the local community to gain their support and involvement in the project",
				new Step("Time Units", "Community Trust Points", 3, 2, "Community Trust Points", "Community Engagement", 5, 2));
		
		Task t13 = new Task(13, "Task 5(b)", "Resolve any community disputes or concerns related to the project",
				new Step("Time Units", "Community Engagement", 2, 2, "Time Units", "Community Trust Points", 6, 3));
		
		tasks.add(t1);
		tasks.add(t2);
		tasks.add(t3);
		tasks.add(t4);
		tasks.add(t5);
		tasks.add(t6);
		tasks.add(t7);
		tasks.add(t8);
		tasks.add(t9);
		tasks.add(t10);
		tasks.add(t11);
		tasks.add(t12);
		tasks.add(t13);
		

	}
	
	public Task getSpecificTask(int num) {
		return tasks.get(num - 1);
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
}
