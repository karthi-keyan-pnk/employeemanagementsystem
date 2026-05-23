package com.example.EmployeeManagementSystem.Service;

import com.example.EmployeeManagementSystem.DTO.RequestDTO;
import com.example.EmployeeManagementSystem.DTO.ResponseDTO;
import com.example.EmployeeManagementSystem.Entity.Employee;
import com.example.EmployeeManagementSystem.Exception.MyOwnException;
import com.example.EmployeeManagementSystem.Repository.EmployeeRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseDTO mapToResponseDTO(Employee employee){
        return new  ResponseDTO(
                employee.getId(),
                employee.getEmpName(),
                employee.getDeptName()
        );
    }

    public List<ResponseDTO> getAllEmployee() {

        List<ResponseDTO> list = new ArrayList<>();

        for(Employee e:employeeRepository.findAll()){
            list.add(mapToResponseDTO(e));
        }
        return list;
    }

    public ResponseDTO addEmployee(RequestDTO requestDTO) {

        Employee employee = Employee.builder()
                .empName(requestDTO.empName())
                .email(requestDTO.email())
                .deptName(requestDTO.deptName())
                .age(requestDTO.age())
                .salary(requestDTO.salary())
                .build();

        employeeRepository.save(employee);

        return mapToResponseDTO(employee);
    }

    public ResponseDTO updateEmployee(Long no, RequestDTO requestDTO) {

        Employee employee= employeeRepository.findById(no)
                .orElseThrow(()->new MyOwnException("Not Found"));

        employee.setAge(requestDTO.age());
        employee.setDeptName(requestDTO.deptName());
        employee.setEmpName(requestDTO.empName());
        employee.setSalary(requestDTO.salary());
        employee.setEmail(requestDTO.email());

        employeeRepository.save(employee);

        return mapToResponseDTO(employee);
    }

    public void deleteEmployee(Long id) {

        Employee employee= employeeRepository.findById(id)
                .orElseThrow(()->new MyOwnException("Resource Not Found"));

        employeeRepository.deleteById(id);

    }

    public ResponseDTO getEmployeeById(long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()->new MyOwnException("Resource Not Found"));

        return mapToResponseDTO(employee);
    }
}
