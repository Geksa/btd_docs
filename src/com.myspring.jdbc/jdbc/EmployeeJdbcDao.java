package jdbc;

import javax.sql.DataSource;

import objects.Employee;
import objects.EmployeeDao;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class EmployeeJdbcDao extends JdbcDaoSupport implements EmployeeDao {

    private DataSource dataSource;

    public void saveEmployee(final Employee employee) {

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement("insert into Employees (empName) values (?)", new String[]{"empId"});
                        ps.setString(1, employee.getName());
                        return ps;
                    }
                },
                keyHolder);
        employee.setEmpId((Long) keyHolder.getKey());

    }
    public List<Employee> getAllEmployees() {
        return this.getJdbcTemplate().query
                ("Select empName as Name from Employees",
                        new ParameterizedRowMapper<Employee>() {
                            public Employee mapRow(ResultSet rs, int rowNum)
                                    throws SQLException {
                                Employee employee = new Employee();
                                employee.setName(rs.getString("Name"));
                                return employee;
                            }
                        }
                );
    }

}
