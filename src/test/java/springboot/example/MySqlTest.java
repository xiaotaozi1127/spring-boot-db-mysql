package springboot.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springboot.example.entity.Department;
import springboot.example.repository.DepartmentRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MySqlTest {
    private static Logger logger = LoggerFactory.getLogger(MySqlTest.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Before
    public void initData(){
        departmentRepository.deleteAll();
        Department department = new Department();
        department.setName("development");
        departmentRepository.save(department);
        Assert.assertNotNull(department.getId());
    }

    @Test
    public void  findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<Department> page = departmentRepository.findAll(pageable);
        Assert.assertNotNull(page);

        for(Department department: page.getContent()){
            logger.info("department name: {}", department.getName());
        }
    }
}
