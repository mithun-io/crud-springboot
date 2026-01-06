package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Employee;
import com.repository.EmployeeRepository;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeRepository repository;

	@GetMapping({ "/", "/main" })
	public String loadMain() {
		return "main.html";
	}

	@GetMapping("/add")
	public String loadAdd() {
		return "add.html";
	}

	@PostMapping("/add")
	public String add(Employee employee, RedirectAttributes attribute) {
		repository.save(employee);
		attribute.addFlashAttribute("message", "record inserted sucessfully");
		return "redirect:/main";
	}

	@GetMapping("/manage")
	public String manage(ModelMap map) {
		List<Employee> list = repository.findAll();
		map.put("list", list);
		return "view.html";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id, RedirectAttributes attributes) {
		repository.deleteById(id);
		attributes.addFlashAttribute("message", "record deleted sucessfully");
		return "redirect:/manage";
	}

	@GetMapping("/edit")
	public String loadedit(@RequestParam("id") Integer id, ModelMap map) {
		Employee employee = repository.findById(id).orElseThrow();
		map.put("employee", employee);
		return "edit.html";
	}

	@PostMapping("/update")
	public String update(Employee employee, RedirectAttributes attributes) {
		repository.save(employee);
		attributes.addFlashAttribute("message", "record updated sucessfully");
		return "redirect:/manage";
	}
}
