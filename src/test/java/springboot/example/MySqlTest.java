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
import springboot.example.entity.Role;
import springboot.example.entity.User;
import springboot.example.repository.DepartmentRepository;
import springboot.example.repository.RoleRepository;
import springboot.example.repository.UserRepository;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class})
public class MySqlTest {
    private static Logger logger = LoggerFactory.getLogger(MySqlTest.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void initData(){
        userRepository.deleteAll();
        roleRepository.deleteAll();
        departmentRepository.deleteAll();


        Department department = new Department();
        department.setName("development");
        departmentRepository.save(department);
        Assert.assertNotNull(department.getId());

        Role admin = new Role();
        admin.setName("admin");
        roleRepository.save(admin);
        Assert.assertNotNull(admin.getId());

        Role guest = new Role();
        guest.setName("guest");
        roleRepository.save(guest);
        Assert.assertNotNull(guest.getId());

        User user = new User();
        user.setName("user1");
        user.setCreatedate(new Date());
        user.setDepartment(department);
        List<Role> roles = roleRepository.findAll();
        user.setRoles(roles);
        userRepository.save(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    public void  findPage(){
        Pageable pageable = new PageRequest(0, 10, new Sort(Sort.Direction.ASC, "id"));
        Page<User> page = userRepository.findAll(pageable);
        Assert.assertNotNull(page);

        for(User user: page.getContent()){
            logger.info("user name: {}, user department: {}, user roles: {}, user createdate: {}",
                    user.getName(), user.getDepartment(), user.getRoles(), user.getCreatedate());
        }
    }
}
