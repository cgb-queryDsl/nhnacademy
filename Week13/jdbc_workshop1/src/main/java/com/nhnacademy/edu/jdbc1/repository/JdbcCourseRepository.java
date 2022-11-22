package com.nhnacademy.edu.jdbc1.repository;

import com.nhnacademy.edu.jdbc1.domain.Subject;
import com.nhnacademy.edu.jdbc1.domain.Teacher;
import com.nhnacademy.edu.jdbc1.service.course.Course;
import com.nhnacademy.edu.jdbc1.service.course.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;

@Slf4j
@Repository
public class JdbcCourseRepository implements CourseRepository {

    static class TempCourse {
        private final Long id;
        private final Long teacherId;
        private final Long subjectId;
        private final Date createdAt;

        public TempCourse(Long id, Long teacherId, Long subjectId, Date createdAt) {
            this.id = id;
            this.teacherId = teacherId;
            this.subjectId = subjectId;
            this.createdAt = createdAt;
        }

        public Long getId() {
            return id;
        }

        public Long getTeacherId() {
            return teacherId;
        }

        public Long getSubjectId() {
            return subjectId;
        }

        public Date getCreatedAt() {
            return createdAt;
        }
    }

    private final JdbcTemplate jdbcTemplate;

    public JdbcCourseRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Course> findAll() {
        return jdbcTemplate.query(
                "select C.id as c_id, C.created_at as c_created_at,\n" +
                        "T.id as t_id, T.name as t_name, T.created_at as t_created_at, \n" +
                        "S.id as s_id, S.name as s_name, S.created_at as s_created_at \n" +
                        "from JdbcCourses as C \n" +
                        "inner join JdbcTeachers as T on C.teacher_id = T.id \n" +
                        "inner join JdbcSubjects as S on C.subject_id = S.id",

                ( (rs, rowNum) ->
                    new Course(
                        rs.getLong("c_id"),

                        new Teacher(rs.getLong("t_id"),
                                rs.getString("t_name"),
                                rs.getTimestamp("t_created_at")),

                        new Subject(rs.getLong("s_id"),
                                rs.getString("s_name"),
                                rs.getTimestamp("s_created_at")),

                        rs.getTimestamp("c_created_at")))
        );
    }

    @Override
    public Course findCourseById(Long id) {
        return jdbcTemplate.queryForObject("select C.id as c_id, C.created_at as c_created_at,\n" +
                        "T.id as t_id, T.name as t_name, T.created_at as t_created_at, \n" +
                        "S.id as s_id, S.name as s_name, S.created_at as s_created_at \n" +
                        "from JdbcCourses as C \n" +
                        "inner join JdbcTeachers as T on C.teacher_id = T.id \n" +
                        "inner join JdbcSubjects as S on C.subject_id = S.id where C.id = ?",

                ( (rs, rowNum) ->
                        new Course(
                                rs.getLong("c_id"),

                                new Teacher(rs.getLong("t_id"),
                                        rs.getString("t_name"),
                                        rs.getTimestamp("t_created_at")),

                                new Subject(rs.getLong("s_id"),
                                        rs.getString("s_name"),
                                        rs.getTimestamp("s_created_at")),

                                rs.getTimestamp("c_created_at"))),
                id);
    }

    @Override
    @Transactional
    public void createCourse(String teacherName, String subjectName) {
        jdbcTemplate.update("INSERT INTO JdbcTeachers (name, created_at) values (?, ?)",
                        teacherName, new Date());
        jdbcTemplate.update("INSERT INTO JdbcSubjects (name, created_at) values (?, ?)",
                        subjectName, new Date());

        Teacher teacher = jdbcTemplate.queryForObject("select * from JdbcTeachers where name=?",
                (rs, rowNum) -> new Teacher(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")), teacherName);

        Subject subject = jdbcTemplate.queryForObject("select * from JdbcSubjects where name=?",
                (rs, rowNum) -> new Subject(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at")), subjectName);

        jdbcTemplate.update("INSERT INTO JdbcCourses (teacher_id, subject_id, created_at) values (?,?,?)",
                teacher.getId(), subject.getId(), new Date());
    }

    @Override
    public void updateCourse(Long courseId, Long teacherId, String teacherName, Long subjectId, String subjectName) {
        jdbcTemplate.update("UPDATE JdbcTeachers SET name = ? where id = ?",
                teacherName, teacherId);
        jdbcTemplate.update("UPDATE JdbcSubjects SET name = ? where id = ?",
                subjectName, subjectId);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        TempCourse temp = jdbcTemplate.queryForObject("select * from JdbcCourses where id=?",
                (rs, rowNum) -> new TempCourse(rs.getLong("id"),
                        rs.getLong("teacher_id"),
                        rs.getLong("subject_id"),
                        rs.getTimestamp("created_at")), id);

        jdbcTemplate.update("DELETE FROM JdbcCourses where id = ?", id);
        jdbcTemplate.update("DELETE FROM JdbcTeachers where id = ?",temp.getTeacherId());
        jdbcTemplate.update("DELETE FROM JdbcSubjects where id = ?", temp.getSubjectId());
    }

}
