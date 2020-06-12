package cn.lijian.demo.repository;

import cn.lijian.demo.domain.GroupInfo;
import cn.lijian.demo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: lijian
 * @Description:
 * @Date: Created in 2020-06-12 10:33
 * @Modified By:
 * @Version:
 * @TaskId:
 */
public interface GroupRepository extends JpaRepository<GroupInfo, Long> {
    GroupInfo findByName(String name);
}