package cn.lijian.demo.repository;

import cn.lijian.demo.domain.Role;
import cn.lijian.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: lijian
 * @Description:
 * @Date: Created in 2020-06-12 10:33
 * @Modified By:
 * @Version:
 * @TaskId:
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}