package application;

import java.util.List;

public class Task {

	private int taskID;
	private String taskName;
	private String description;
	private Step step;

	public Task(int taskID, String taskName, String description, Step step) {
		this.taskName = taskName;
		this.taskID = taskID;
		this.description = description;
		this.step = step;
	}

	public Step getSteps() {
		return this.step;
	}
	
	public int getTaskID() {
		return this.taskID;
	}

	public String getDescription() {
		return this.description;
	}
	
	public String getTaskName() {
		return this.taskName;
	}

	//changed to public during testing
	public void setDescription(String s) {
		this.description = s;
	}
}
