package lk.ijse.dep10.studentmanagementsystem.dao.custom.util;

import lk.ijse.dep10.studentmanagementsystem.entity.Student;
import org.springframework.jdbc.core.RowMapper;


public class Mapper {
    public static final RowMapper<Student> STUDENT_ROW_MAPPER = (rs, rowNum) -> new Student(rs.getString("id"), rs.getString("name"),rs.getString("address"));
}
