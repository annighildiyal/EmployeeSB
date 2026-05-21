package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {

        model.addAttribute(
                "employee",
                employeeService.getAllEmployees()
        );

        return "employee";
    }


 // Save Employee
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {

        employeeService.saveEmployee(employee);

        return "redirect:/employees";
    }}
