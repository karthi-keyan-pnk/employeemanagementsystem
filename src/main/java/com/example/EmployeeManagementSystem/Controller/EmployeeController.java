package com.example.EmployeeManagementSystem.Controller;

import com.example.EmployeeManagementSystem.DTO.RequestDTO;
import com.example.EmployeeManagementSystem.DTO.ResponseDTO;
import com.example.EmployeeManagementSystem.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;
    EmployeeController(EmployeeService employeeService )
    {
        this.employeeService = employeeService;
    }

    @GetMapping("/allEmp")
    public ResponseEntity<List<ResponseDTO>> getAllEmployee(@RequestParam(required = false,defaultValue = "1")int page,
                                                            @RequestParam(required = false,defaultValue = "5")int pageSize,
                                                            @RequestParam(required = false,defaultValue = "id")String sortBy,
                                                            @RequestParam(required = false,defaultValue = "ASC")String sortDir,
                                                            @RequestParam(required = false)String search){

        Sort sort ;
        if(sortDir.equals("ASC")){
            sort = Sort.by(sortBy).ascending();
        }
        else
        sort = Sort.by(sortBy).descending();

        return new ResponseEntity<>(employeeService.getAllEmployee(PageRequest.of(page-1,pageSize,sort),search), HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<ResponseDTO> getEmployeeById(@RequestParam long id){

        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @PostMapping("/addEmp")
    public ResponseEntity<ResponseDTO> addEmployee(@Valid @RequestBody RequestDTO requestDTO){
        return new ResponseEntity<>(employeeService.addEmployee(requestDTO),HttpStatus.CREATED);
    }

    @DeleteMapping("/delEmp/{id}")
    public ResponseEntity<String> deleteEmployee (@Valid @PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted",HttpStatus.OK);
    }

    @PutMapping("/uptEmp/{id}")
    public ResponseEntity<ResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody RequestDTO requestDTO){
        return new ResponseEntity<>(employeeService.updateEmployee(id,requestDTO),HttpStatus.OK);
    }

}
