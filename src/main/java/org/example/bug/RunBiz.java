package org.example.bug;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunBiz implements CommandLineRunner {
    private static final Logger LOG = LoggerFactory.getLogger(RunBiz.class);
    @Resource
    private Biz biz;

    @Override
    public void run(String... args) throws Exception {
        //*/
        // 先执行实际 defaultFallback 不存在的方法, 会导致后面有回调的方法不执行 defaultFallback
        try {
            LOG.error("[1] should not output: {}", biz.doubled(0)); // no output
        } catch (IllegalArgumentException e) {
            LOG.info("[2] exception: {}", e.getMessage()); // go here
        }
        try {
            LOG.info("[3] expect fallback: {}" , biz.doubled("")); // no output
        } catch (IllegalArgumentException e) {
            LOG.error("[4] IllegalArgumentException: {}", e.getMessage()); // go here, an IllegalArgumentException
        }
        /*/
        // 先执行实际 defaultFallback 存在的方法, 会导致后面无回调的方法报错
        try {
            LOG.info("[5] fallback: {}", biz.doubled("")); // fallback
        } catch (Exception e) {
            LOG.error("[6] should not output: {}", e.getMessage()); // no output
        }
        try {
            LOG.error("[7] should not output: {}", biz.doubled(0)); // no output
        } catch (ClassCastException e) {
            LOG.error("[8] unexpected ClassCastException: {}", e.getMessage()); // go here, a ClassCastException
        } catch (IllegalArgumentException e) {
            LOG.info("[9] expected exception: {}", e.getMessage()); // no output
        }
        //*/
    }
}
