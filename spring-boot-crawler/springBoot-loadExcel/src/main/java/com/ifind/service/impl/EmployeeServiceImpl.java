package com.ifind.service.impl;

import com.ifind.dao.EmployeeDao;
import com.ifind.entity.Employee;
import com.ifind.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author 易都军
 * @date 2020/3/23 11:32
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    /**
     * 根据电话号码得到员工信息
     * @param moblie
     * @return
     */
    @Override
    public Employee getEmpByMoblie(String moblie) {
        Employee employee = dao.getEmpByMoblie(moblie);
        return employee;
    }

    /**
     *  添加员工信息
     * @param emp
     */
    @Override
    public void addEmployee(Employee emp) {
        dao.addEmployee(emp);
    }


}
