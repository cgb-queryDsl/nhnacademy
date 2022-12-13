package com.nhnacademy.edu.springboot.student.configure.endpoint;

import io.micrometer.core.lang.Nullable;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Endpoint(id = "counter")
public class CountEndPoint {
    private final AtomicLong count = new AtomicLong();

    @ReadOperation
    public Long read() {
        return count.get();
    }

    @WriteOperation
    public Long increment(@Nullable Long delta) {
        if (delta == null) {
            return count.incrementAndGet();
        }

        return count.addAndGet(delta);
    }

    @DeleteOperation
    public Long reset() {
        count.set(0);
        return count.get();
    }
}
