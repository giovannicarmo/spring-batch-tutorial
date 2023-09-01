package com.giovannicarmo.springbatchtutorial.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.lang.NonNull;

import com.giovannicarmo.springbatchtutorial.JobCompletionNotificationListener;
import com.giovannicarmo.springbatchtutorial.domain.entity.Employee;
import com.giovannicarmo.springbatchtutorial.ultils.WrappedItem;

public class EmployeeWriter implements ItemStreamWriter<WrappedItem<Employee, String>> {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Override
    public void write(@NonNull Chunk<? extends WrappedItem<Employee, String>> chunk) throws Exception {
        for (WrappedItem<Employee, String> wrappedItem : chunk.getItems()) {
            Employee originalItem = wrappedItem.getOriginalItem();
            String marshaledString = wrappedItem.getMarshaledString();

            log.info("Original Item: {} --> Marshaled String: {}", originalItem.toString(), marshaledString);
        }
    }
}
