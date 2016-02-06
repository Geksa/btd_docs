package foo.bar;

import objects.Document;
import objects.DocumentDao;
import objects.Employee;
import objects.EmployeeDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.logging.Logger;

public class HelloApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloService helloService = context.getBean(HelloService.class);
        Logger.getGlobal().info(helloService.sayHello());

//-----------создание Работника
        Employee employee = new Employee();
        employee.setName("Employee1");
        EmployeeDao employeeDao = (EmployeeDao)context.getBean("employeeJdbcDao");
        employeeDao.saveEmployee(employee);

        Employee employee2 = new Employee();
        employee2.setName("Employee2");
        employeeDao.saveEmployee(employee2);

//-----------получение списка работников
        List<Employee> employees=employeeDao.getAllEmployees();
        for (Employee itemEmployee : employees) {
            Logger.getGlobal().info("Employee="+itemEmployee.getName());
        }


//-----------создание Документа
        Document document = new Document();
        document.setName("Doc1Name");
        DocumentDao documentDao = (DocumentDao)context.getBean("documentJdbcDao");
        documentDao.saveDocument(document, employee2);

        Logger.getGlobal().info("end");
    }
}
