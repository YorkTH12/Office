package com.itthisak.office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itthisak.office.model.Department;
import com.itthisak.office.model.Employee;
import com.itthisak.office.model.Project;
import com.itthisak.office.repository.DepartmentRepository;
import com.itthisak.office.repository.EmployeeRepository;
import com.itthisak.office.repository.ProjectRepository;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class OfficeApplication implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(
			OfficeApplication.class);
	private final EmployeeRepository employeeRepository;
	private final DepartmentRepository departmentrRepository;
	private final ProjectRepository projectRepository;

	public OfficeApplication(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository,
			ProjectRepository projectRepository) {
		this.employeeRepository = employeeRepository;
		this.departmentrRepository = departmentRepository;
		this.projectRepository = projectRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(OfficeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department department1 = new Department("IT");
		Department department2 = new Department("BC");
		departmentrRepository.saveAll(Arrays.asList(department1,department2));

		Project project1 = new Project("Eat rich");
		Project project2 = new Project("Hang out");
		projectRepository.saveAll(Arrays.asList(project1,project2));

		employeeRepository.save(new Employee("Itthisak", 23000, department2, project2));
		employeeRepository.save(new Employee("Josh", 3000, department1, project1));

		for(Employee employee : employeeRepository.findByName("itthisak")){
			logger.info("name : {}, salary : {}",employee.getName(), employee.getSalary());
		}

		for(Employee employee : employeeRepository.findBySalaryGreaterThan(1500)){
			logger.info("name : {}, salary : {}",employee.getName(), employee.getSalary());
		}

		for(Project project : projectRepository.findByNameContaining("IT")){
			logger.info("name : {}",project.getName());
		}
	}

}
