package com.ifind.dao;

import com.ifind.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface EmployeeDao {
    /**
     * 根据电话号码得到员工信息
     * @param moblie
     * @return
     */
    Employee getEmpByMoblie(@Param("moblie") String moblie);

    /**
     *  添加员工信息
     * @param emp
     */
    void addEmployee(Employee emp);
}
