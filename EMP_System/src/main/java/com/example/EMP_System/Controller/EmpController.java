package com.example.EMP_System.Controller;

import ch.qos.logback.core.model.Model;
import com.example.EMP_System.Model.Employee;
import com.example.EMP_System.Repository.EmpRepo;
import com.example.EMP_System.Services.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.text.AttributedString;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmpController {
    @Autowired
    EmpService service;

    @GetMapping("/home")
    public String home()
    {
        return "index";
    }


    @GetMapping("/index")
    public String home(ModelMap model)
    {
        List<Employee> employees = service.getEmp();
        model.addAttribute("employees", employees); // Add it to the model
        return "index";
    }

    @GetMapping("/addEmppage")
    public String addEmp()
    {
        return "addEmp";
    }

    @PostMapping("/addEmp")
    public String addEmp(@ModelAttribute Employee employee)
    {
        service.addEmp(employee);
        return "redirect:/employees/index";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("employee") Employee employee)
    {

        service.update(employee);
        return "redirect:index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, ModelMap model )
    {
        Employee employee = service.getEmpById(id);
        model.addAttribute("employee", employee);
        return "update";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id)
    {
        System.out.println("Deleting with id" + id);
        service.deleteEmployee(id);
        return "redirect:/employees/index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("query") String query, ModelMap model)
    {
        List<Employee> employees = service.searchEmployees(query);
        model.addAttribute("employees", employees);
        return "employee_list";
    }


}
