
package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.Employee;
import com.utility.Emputility;

public class EmployeeDao {

	Employee emp = null;
	ResultSet rs = null;
	List<Employee> empDB = null;

	public List<Employee> getAllEmployee() {
		empDB = new ArrayList<Employee>();
		String query = "SELECT * FROM employee";
		try {
			ResultSet rs = Emputility.createConnection(query).executeQuery();
			while (rs.next()) {
				int id = rs.getInt("Id");
				String name = rs.getString("Name");
				String department = rs.getNString("Department");
				int salary = rs.getInt("Salary");
				String hiring_date = rs.getString("hiring_date");
				String address = rs.getString("Address");
				emp = new Employee(id, name, department, salary, hiring_date, address);
				empDB.add(emp);
			}
			Emputility.closeConnection();
			rs.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return empDB;
	}

	public boolean insertEmployee(int id, String name, String dept, int salary, String hiringD, String add) {
		emp = new Employee(id, name, dept, salary, hiringD, add);
//		String query = "INSERT INTO employee VALUES("+emp.getId()+",'"+emp.getName()+"','"+emp.getDepartment()+"',"+emp.getSalary()+",'"+emp.getHiring_date()+"','"+emp.getAddress()+"')";
		String query = "INSERT INTO employee VALUES(?,?,?,?,?,?)";
		try {
//			EmpUtility.createConnectionToInsert().prepareStatement(query).execute();
			PreparedStatement preStatement = Emputility.createConnectionToInsert().prepareStatement(query);
			preStatement.setInt(1, emp.getId());
			preStatement.setString(2, emp.getName());
			preStatement.setString(3, emp.getDepartment());
			preStatement.setInt(4, emp.getSalary());
			preStatement.setString(5, emp.getHiring_date());
			preStatement.setString(6, emp.getAddress());
			int rowsAffected = preStatement.executeUpdate();
			System.out.println("Inserted " + rowsAffected + " row(s) into Employee table.");
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return true;
	}

	public void updateName(int id, String upname) {
		String query = "UPDATE employee SET Name ='" + upname + "' WHERE Id =" + id;
		try {
			Emputility.createConnectionToInsert().prepareStatement(query).execute();
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void updateDept(int id, String upDept) {
		String query = "UPDATE employee SET Department ='" + upDept + "' WHERE Id =" + id;
		try {
			Emputility.createConnectionToInsert().prepareStatement(query).execute();
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void updateSalary(int id, int salary) {
		String query = "UPDATE employee SET Salary =" + salary + " WHERE Id =" + id;
		try {
			Emputility.createConnectionToInsert().prepareStatement(query).execute();
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void updateHDate(int id, String date) {
		String query = "UPDATE employee SET hiring_date ='" + date + "' WHERE Id =" + id;
		try {
			Emputility.createConnectionToInsert().prepareStatement(query).execute();
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void updateAdd(int id, String ads) {
		String query = "UPDATE employee SET Address ='" + ads + "' WHERE Id =" + id;
		try {
			Emputility.createConnectionToInsert().prepareStatement(query).execute();
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public boolean deleteEmployee(int id) {
		boolean b = true;
		String query = "DELETE FROM employee WHERE Id=" + id;
		try {
			b = Emputility.createConnection(query).execute();
			Emputility.closeConnection();
		} catch (SQLException e) {
			System.out.println("Invailid Id...");
			return true;
		}
		return b;
	}
}
