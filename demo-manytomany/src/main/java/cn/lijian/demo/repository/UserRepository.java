package cn.lijian.demo.repository;

import cn.lijian.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: lijian
 * @Description:
 * @Date: Created in 2020-06-12 10:33
 * @Modified By:
 * @Version:
 * @TaskId:
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    void deleteByUsername(String username);
}