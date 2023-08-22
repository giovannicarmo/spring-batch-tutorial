package com.giovannicarmo.springbatchtutorial.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.giovannicarmo.springbatchtutorial.domain.Employee;
import com.giovannicarmo.springbatchtutorial.generator.mapper.EmployeeMapper;
import com.giovannicarmo.springbatchtutorial.generator.valueobject.EmployeeVO;

public class EmployeeConverterProcessor implements ItemProcessor<Employee, EmployeeVO> {

    @Override
    @Nullable
    public EmployeeVO process(@NonNull Employee item) throws Exception {
        return EmployeeMapper.INSTANCE.toVO(item);
    }

}
