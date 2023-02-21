package gr.aueb.cf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gr.aueb.cf.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.service.util.DBUtil;

public class TeacherDAOImpl implements ITeacherDAO {

	@Override
	public Teacher insert(Teacher teacher) throws TeacherDAOException {
		String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?,?)";

		// int n;

		try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			String firstname = teacher.getFirstname();
			String lastname = teacher.getLastname();

			if (firstname.equals("") || lastname.equals("")) {
				return null;
			}

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.executeUpdate();
			return teacher;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new TeacherDAOException("SQL error in teacher insertion " + teacher);
		}
	}

	@Override
	public Teacher update(Teacher teacher) throws TeacherDAOException {
		String sql = "UPDATE TEACHERS SET FIRSTNAME=?, LASTNAME =? WHERE ID=? ";

		// int n;

		try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			int id = teacher.getId();
			String firstname = teacher.getFirstname();
			String lastname = teacher.getLastname();

			if (firstname.equals("") || lastname.equals("")) {
				return null;
			}

			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setInt(3, id);
			ps.executeUpdate();
			return teacher;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new TeacherDAOException("SQL error in teacher update " + teacher);
		}
	}

	@Override
	public void delete(int id) throws TeacherDAOException {
		String sql = "DELETE FROM TEACHERS WHERE ID = ";

		try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new TeacherDAOException("SQL error in teacher deleted with id" + id);
		}

	}

	@Override
	public List<Teacher> getByLastName(String lastname) throws TeacherDAOException {
		String sql = "SELECT ID,FIRSTNAME,LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
		ResultSet rs;
		List<Teacher> teachers = new ArrayList<>();

		try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

			ps.setString(1, lastname + '%');

			rs = ps.executeQuery();

			while (rs.next()) {
				Teacher teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));
				teachers.add(teacher);

			}
			return teachers;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new TeacherDAOException("SQL error in teacher with lastname" + lastname);
		}

	}

	@Override
	public Teacher getById(int id) throws TeacherDAOException {
		Teacher teacher = null;
		ResultSet rs;

		String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE ID = ?";

		try (Connection connection = DBUtil.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				teacher = new Teacher(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"));

			}
			return teacher;

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new TeacherDAOException("SQL error in teacher with id" + id);
		}
	}

}
