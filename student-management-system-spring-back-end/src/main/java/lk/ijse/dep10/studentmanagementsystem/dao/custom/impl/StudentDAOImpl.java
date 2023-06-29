package lk.ijse.dep10.studentmanagementsystem.dao.custom.impl;

import lk.ijse.dep10.studentmanagementsystem.dao.custom.StudentDAO;
import lk.ijse.dep10.studentmanagementsystem.entity.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

import static lk.ijse.dep10.studentmanagementsystem.dao.custom.util.Mapper.STUDENT_ROW_MAPPER;

public class StudentDAOImpl implements StudentDAO {
    private final JdbcTemplate jdbcTemplate;

    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() throws Exception {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM student", Long.class);
    }

    @Override
    public Student save(Student entity) throws Exception {
        jdbcTemplate.update("INSERT INTO student (id, name,address) VALUES (?, ?,?)", entity.getId(), entity.getName(),entity.getAddress());
        return entity;
    }

    @Override
    public void update(Student entity) throws Exception {
        jdbcTemplate.update("UPDATE student SET name=?,address=? WHERE id=?",entity.getName(),entity.getAddress(),entity.getId());
    }

    @Override
    public void deleteById(String pk) throws Exception {
        jdbcTemplate.update("DELETE FROM student WHERE id=?",pk);
    }

    @Override
    public Optional<Student> findById(String pk) throws Exception {
        try {
            return Optional.of(jdbcTemplate.queryForObject("SELECT * FROM student WHERE id=?",STUDENT_ROW_MAPPER,pk));
        }catch (DataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        return null;
    }

    @Override
    public boolean existsById(String pk) throws Exception {
        return false;
    }
}
