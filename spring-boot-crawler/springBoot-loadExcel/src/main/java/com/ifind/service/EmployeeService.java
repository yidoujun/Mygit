package com.ifind.service;

import com.ifind.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * @author 易都军
 * @date 2020/3/23 11:30
 */
public interface EmployeeService {

    /**
     * 根据电话号码得到员工信息
     * @param moblie
     * @return
     */
    Employee getEmpByMoblie(String moblie);

    /**
     *  添加员工信息
     * @param emp
     */
    void addEmployee(Employee emp);

}
