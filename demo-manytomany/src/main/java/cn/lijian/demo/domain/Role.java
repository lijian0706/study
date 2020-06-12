package cn.lijian.demo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role",
				joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
	private List<User> users = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "group_role",
			joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "group_id", referencedColumnName = "id")})
	private List<GroupInfo> groups = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource",
			joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
	private List<Resource> resources = new ArrayList<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
	@JoinTable(name = "role_app_info",
			joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "app_id", referencedColumnName = "id")})
	private List<AppInfo> apps = new ArrayList<>();
}