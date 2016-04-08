package com.zefun.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.zefun.web.entity.Employee;

/**
 * 1.自定义类型转换器,比如在表单中请求epms的时候 ,提交的数据格式是//GG-gg@atguigu.com-0-105
 *      就会返回一个Employee类型的bean,而在请求的方法参数中这样写(@RequestParam(value="表单name") Employee employee)
 *      在强制转换的时候就会使用该转换器
 * 2.配置转换器 在xml中有详细讲解
 */
@Component(value="employeeConverter")
public class EmployeeConverter implements Converter<String, Employee> {

    public Employee convert(String source) {
        if (source != null) {
            String[] vals = source.split("-");
            // GG-gg@atguigu.com-0-105
            if (vals != null && vals.length == 4) {
                String lastName = vals[0];
                String email = vals[1];
                Integer gender = Integer.parseInt(vals[2]);
                Employee employee = new Employee(null, lastName, email, gender);
                System.out.println(source + "--convert--" + employee);
                return employee;
            }
        }
        return null;
    }

}
