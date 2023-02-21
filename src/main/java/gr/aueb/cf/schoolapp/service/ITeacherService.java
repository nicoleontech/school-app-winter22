package gr.aueb.cf.schoolapp.service;

import java.util.List;

import gr.aueb.cf.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.service.exceptions.TeacherNotFoundException;

public interface ITeacherService {
	Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDAOException;
	Teacher updateTeacher(TeacherDTO teacherToUpdate) 
			throws TeacherDAOException, TeacherNotFoundException;
	void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException;
	List<Teacher> getTeachersByLastName(String lastname) 
			throws TeacherDAOException, TeacherNotFoundException;

}
