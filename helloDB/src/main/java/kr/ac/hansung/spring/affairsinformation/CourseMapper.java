package kr.ac.hansung.spring.affairsinformation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CourseMapper implements RowMapper<Course> {

	public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Course course = new Course();
		course.setYear(resultSet.getInt("year")); 
		course.setSemester(resultSet.getInt("semester"));
		course.setCode(resultSet.getString("code"));
		course.setSubject(resultSet.getString("subject"));
		course.setDivision(resultSet.getString("division"));
		course.setCredit(resultSet.getInt("credit"));

		return course;
	}

}
