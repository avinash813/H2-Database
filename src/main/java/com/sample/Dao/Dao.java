package com.sample.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.tomcat.util.openssl.pem_password_cb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sample.model.Employee;

@Repository
public class Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sql = "insert into employee(id, name, city) values(?,?,?)";
	private String sql2 = "select * from employee";
	private String sql3 = "UPDATE employee SET name='Modified' WHERE city = ?";
	private String sql4 = "insert into employee(id, name, city) values(?,?,?); UPDATE employee SET name='JokerChanged' WHERE city = ?"; //This will not execute in 1 Go Its just like writing two JdbcTemplete.update() statements
	
	
	public void update() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("Alexa");
		emp.setCity("Hyderabad");
	   int rowsAffected =  jdbcTemplate.update(sql,emp.getId(),emp.getName(),emp.getCity());
	    emp.setId(2);
		emp.setName("Bobby");
		emp.setCity("Hyderabad");
	   int rowsAffectedd =  jdbcTemplate.update(sql,emp.getId(),emp.getName(),emp.getCity());
	   int gg = jdbcTemplate.update(sql3,emp.getCity());
	   emp.setId(3);
		emp.setName("Joker");
		emp.setCity("Bombay");
	   int rowsAffecteddd =  jdbcTemplate.update(sql4,emp.getId(),emp.getName(),emp.getCity(),emp.getCity());
	   System.out.println("Rows Affected are: " + rowsAffecteddd);
	   
	   
		
	}
	
	public List<Employee> retrieve() {
		
		RowMapper<Employee> rowMapper = (rs, n) -> {
			Employee emp = new Employee();
			emp.setId(rs.getInt(1));
			emp.setName(rs.getString(2));
			emp.setCity(rs.getString(3));
			return emp;
		};
		
		List<Employee> emp = jdbcTemplate.query(sql2 , rowMapper);
		
		return emp;
		
	}
	
}
