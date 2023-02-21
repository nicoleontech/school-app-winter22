package gr.aueb.cf.dao;

import java.util.List;

import gr.aueb.cf.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

//Crud API
public interface ITeacherDAO {

	Teacher insert(Teacher teacher) throws TeacherDAOException;
	Teacher update(Teacher teacher) throws TeacherDAOException;
	void delete(int id) throws TeacherDAOException;
	List<Teacher> getByLastName(String lastname) throws TeacherDAOException;
	Teacher getById (int id) throws TeacherDAOException; 
}
