package jdbc;

import javax.sql.DataSource;
import objects.*;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
public class DocumentJdbcDao extends JdbcDaoSupport implements DocumentDao{
    private DataSource dataSource;


    @Override
    public void saveDocument(Document document) {
        this.getJdbcTemplate().update
                ("insert into DOCUMENTS (docName) values (?)",
                        new Object[] {document.getName()});
    }

    @Override
    public void saveDocument(Document document, Employee employee) {
        this.getJdbcTemplate().update
                ("insert into DOCUMENTS (docName, employees_empId) values (?, ?)",
                        new Object[] {document.getName(), employee.getEmpId()});
    }
}
