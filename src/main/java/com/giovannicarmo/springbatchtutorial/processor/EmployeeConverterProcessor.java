package com.giovannicarmo.springbatchtutorial.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.giovannicarmo.springbatchtutorial.domain.dto.EmployeeDTO;
import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;
import com.giovannicarmo.springbatchtutorial.mapper.EmployeeMapper;

public class EmployeeConverterProcessor implements ItemProcessor<Employee, EmployeeDTO> {

    @Override
    @Nullable
    public EmployeeDTO process(@NonNull Employee item) throws Exception {
        return EmployeeMapper.INSTANCE.toDTO(item);
    }

}
