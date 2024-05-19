package org.example.bug;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;

@Component
@SentinelResource(defaultFallback = "df")
public class Biz {
    @SentinelResource
    public int doubled(int a) {
        if (a == 0) {
            throw new IllegalArgumentException("a should not be 0");
        }
        return a * 2;
    }

    public String df() {
        return "fallback";
    }

    @SentinelResource
    public String doubled(String a) {
        if (a == null || a.isEmpty()) {
            throw new IllegalArgumentException("a should not be empty");
        }
        return a + a;
    }
}
