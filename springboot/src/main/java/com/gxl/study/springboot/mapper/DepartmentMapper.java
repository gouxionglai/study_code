package com.gxl.study.springboot.mapper;

import java.util.List;

import com.gxl.study.springboot.model.Department;


/*@Repository
public class DepartmentMapper {

	private static Map<Integer, Department> departments;
	
	static{
		departments = new HashMap<Integer, Department>();
		
		departments.put(101, new Department(101, "D-AA"));
		departments.put(102, new Department(102, "D-BB"));
		departments.put(103, new Department(103, "D-CC"));
		departments.put(104, new Department(104, "D-DD"));
		departments.put(105, new Department(105, "D-EE"));
	}
	
	public Collection<Department> getDepartments(){
		return departments.values();
	}
	
	public Department getDepartment(Integer id){
		return departments.get(id);
	}
	
}*/

public interface DepartmentMapper {
	List<Department> getAll();

	Department getById(Integer id);

	int insert(Department department);

	int update(Department department);

	int delete(Department department);
}
