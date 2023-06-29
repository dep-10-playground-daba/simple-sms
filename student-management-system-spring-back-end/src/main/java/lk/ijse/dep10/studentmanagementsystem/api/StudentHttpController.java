package lk.ijse.dep10.studentmanagementsystem.api;

import lk.ijse.dep10.studentmanagementsystem.entity.Student;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/students")
public class StudentHttpController {
    @GetMapping
    public String getAllStudents(){
        return "<h1>Get request Working<h1>";
    }
    @PostMapping
    public String SaveStudent(){
        return "<h1>Save Student Working<h1>";
    }
    @DeleteMapping("/{studentId}")
    public String DeleteStudent(){
        return "<h1>Delete Student Working<h1>";
    }

}
