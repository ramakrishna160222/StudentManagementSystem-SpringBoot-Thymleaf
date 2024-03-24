package net.javaguides.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;

@Controller
public class StudentController {
	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	@GetMapping("students")
	public String getAllStudents(Model m) {
		m.addAttribute("students",studentService.getAllStudents());
		return "students";
	}
	@GetMapping("/students/new")
	public String createStudent(Model m) {
		Student student=new Student();
		m.addAttribute("student", student);
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudentForm(@PathVariable("id") long id,Model m) {
		m.addAttribute("student", studentService.getStudentById(id));
		return "edit_student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable("id") long id,@ModelAttribute("student") Student student,Model m) {
		Student existingStudent =studentService.getStudentById(id);
				existingStudent.setId(id);
				existingStudent.setFirstName(student.getFirstName());
				existingStudent.setLastName(student.getLastName());
				existingStudent.setEmail(student.getEmail());
				studentService.updateStudent(existingStudent);
				return "redirect:/students";
				
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable("id") long id) {
		studentService.deleteStudent(id);
		return "redirect:/students";
		
	}
}