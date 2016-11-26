package kr.ac.hansung.spring.affairsinformation;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"kr/ac/hansung/spring/affairsinformation/beans/beans.xml");

		/* TEST COUNT */
		CourseDAO courseDAO = (CourseDAO) context.getBean("courseDAO");
		System.out.println("The record count is: " + courseDAO.getRowCount());

		/* TEST ALLRECORD */
		List<Course> courseList = courseDAO.getAllCourses();
		for (Course course : courseList) {
			System.out.println(course);
		}

		/* TEST INSERT */
		Course newCourse = new Course(2017, 1, "CSE0042", "웹프레임워크2", "전선", 2);
		if (courseDAO.insert(newCourse)) {
			System.out.println("Object is inserted successfully");
		} else {
			System.out.println("Object insertion failed");
		}

		/* TEST UPDATE */
		newCourse = courseDAO.getCourse("CSE0042");
		System.out.println(newCourse);

		newCourse.setCredit(3);
		courseDAO.update(newCourse);
		if (courseDAO.update(newCourse)) {
			System.out.println("updated with object" + newCourse);
		} else {
			System.out.println("Cannot update an object");
		}

		/* TEST DELETE */
		if (courseDAO.delete(newCourse.getCode())) {
			System.out.println("Object is deleted");
		} else {
			System.out.println("Cannot delete object");
		}

		context.close();
	}
}
