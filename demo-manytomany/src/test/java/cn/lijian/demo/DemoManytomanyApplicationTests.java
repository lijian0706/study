package cn.lijian.demo;

import cn.lijian.demo.domain.GroupInfo;
import cn.lijian.demo.domain.Role;
import cn.lijian.demo.domain.User;
import cn.lijian.demo.repository.GroupRepository;
import cn.lijian.demo.repository.RoleRepository;
import cn.lijian.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional // 懒加载需要加事务，否则获取多对多属性会报懒加载错误
@Rollback(false) // 配置事务不回滚
class DemoManytomanyApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Test
	void contextLoads() {

	}

	@Test
	public void createUserAndRole(){
		User user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		role.setName("管理员");
		roles.add(role);
		user.setRoles(roles);
		userRepository.save(user);
	}

	@Test
	public void userAddRole(){
		User user = userRepository.findByUsername("admin");
		Role role = new Role();
		role.setName("组长");
		user.getRoles().add(role);
		userRepository.save(user);
	}

	@Test
	public void deleteUserRole(){
		User user = userRepository.findByUsername("admin");
//		User user = userRepository.findById(1L).get();
		user.getRoles().removeIf(role -> role.getName().equals("管理员"));
		userRepository.save(user);
	}

	@Test
	public void userAllotRole(){
		User user = userRepository.findByUsername("admin");
		Role role = roleRepository.findByName("管理员");
		user.getRoles().add(role);
		userRepository.save(user);
	}

	/**
	 * @Description: 删除用户，关联关系、用户都删除了
	 * @Auther: lijian
	 * @Date:  2020-06-12 14:42
	 * @Return: void
	 * @Version: V1.0.0
	 * @TaskId: YJ-
	 */
	@Test
	public void deleteUser(){
		userRepository.deleteByUsername("admin");
	}

	/**
	 * @Description: 新建角色并分配给用户
	 * @Auther: lijian
	 * @Date:  2020-06-12 14:52
	 * @Return: void
	 * @Version: V1.0.0
	 * @TaskId: YJ-
	 */
	@Test
	public void createRoleAllotUser(){
		Role role = new Role();
		role.setName("普通用户");
		User user = userRepository.findByUsername("admin");
		role.getUsers().add(user);
		roleRepository.save(role);
	}

	@Test
	public void userCreateGroup(){
		GroupInfo groupInfo = new GroupInfo();
		groupInfo.setName("群组1");
		User user = userRepository.findByUsername("admin");
		user.getGroups().add(groupInfo);
		userRepository.save(user);
	}

	/**
	 * @Description: 删除群组将删除关联关系
	 * @Auther: lijian
	 * @Date:  2020-06-12 15:17
	 * @Return: void
	 * @Version: V1.0.0
	 * @TaskId: YJ-
	 */
	@Test
	public void deleteGroup(){
		GroupInfo groupInfo = groupRepository.findByName("群组1");
		groupRepository.delete(groupInfo);
		System.out.println();
	}

	@Test
	public void roleAllotGroup(){
		Role role = roleRepository.findByName("管理员");
		GroupInfo groupInfo = groupRepository.findByName("群组1");
		role.getGroups().add(groupInfo);
		roleRepository.save(role);
	}

	/**
	 * @Description: 删除群组，只删除了关联关系
	 * @Auther: lijian
	 * @Date:  2020-06-12 16:20
	 * @Return: void
	 * @Version: V1.0.0
	 * @TaskId: YJ-
	 */
	@Test
	public void roleDeleteGroup(){
		Role role = roleRepository.findByName("管理员");
		role.getGroups().removeIf(groupInfo -> groupInfo.getName().equals("群组1"));
		roleRepository.save(role);
	}
}
