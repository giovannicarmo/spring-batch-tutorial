package com.giovannicarmo.springbatchtutorial;

import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.transform.stream.StreamResult;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class XmlWritter<T> implements ItemStreamWriter<T>, ItemWriteListener<T> {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private List<String> marshaledItems = new CopyOnWriteArrayList<>();

    private AtomicInteger chunkCount = new AtomicInteger(0);
    private final int TOTAL_CHUNKS = 10;

    @Override
    public synchronized void write(@NonNull Chunk<? extends T> chunk) throws Exception {

        for (T item : chunk.getItems()) {
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(item, new StreamResult(writer));
                marshaledItems.add(writer.toString());
            } catch (Exception e) {
                throw e;
            }
        }
        chunkCount.incrementAndGet();
    }

    @Override
    public synchronized void afterWrite(Chunk<? extends T> items) {
        if (chunkCount.get() == TOTAL_CHUNKS) {
            marshaledItems.forEach(i -> {
                System.out.println(i);
            });
        }
    }
}
