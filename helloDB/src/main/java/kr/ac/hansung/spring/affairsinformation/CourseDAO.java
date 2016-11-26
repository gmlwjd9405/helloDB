package kr.ac.hansung.spring.affairsinformation;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component("courseDAO")
public class CourseDAO {
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public int getRowCount() {
		String sqlStatement = "select count(*) from course";
		return jdbcTemplateObject.queryForObject(sqlStatement, Integer.class);
	}

	// querying and returning a single object
	// return record
	public Course getCourse(String code) {
		String sqlStatement = "select * from course where code=?";
		return jdbcTemplateObject.queryForObject(sqlStatement, new Object[] { code }, new CourseMapper());
	}

	/* querying and returning a multiple object */
	// return all records
	public List<Course> getAllCourses() {
		String sqlStatement = "select * from course";
		return jdbcTemplateObject.query(sqlStatement, new CourseMapper());
	}

	public boolean insert(Course course) {
		int year = course.getYear();
		int semester = course.getSemester();
		String code = course.getCode();
		String subject = course.getSubject();
		String division = course.getDivision();
		int credit = course.getCredit();

		// year, semester, code, subject, division, credit
		String sqlStatement = "insert into course (year, semester, code, subject, division, credit) values (?,?,?,?,?,?)";
		return (jdbcTemplateObject.update(sqlStatement,
				new Object[] { year, semester, code, subject, division, credit }) == 1);
	}

	public boolean update(Course course) {
		int year = course.getYear();
		int semester = course.getSemester();
		String code = course.getCode();
		String subject = course.getSubject();
		String division = course.getDivision();
		int credit = course.getCredit();

		// year, semester, code, subject, division, credit
		String sqlStatement = "update course set year=?, semester=?, subject=?, division=?, credit=? where code=?";
		return (jdbcTemplateObject.update(sqlStatement,
				new Object[] { year, semester, subject, division, credit, code }) == 1);
	}

	public boolean delete(String code) {
		String sqlStatement = "delete from course where code=?";
		return (jdbcTemplateObject.update(sqlStatement, new Object[] { code }) == 1);
	}
}
