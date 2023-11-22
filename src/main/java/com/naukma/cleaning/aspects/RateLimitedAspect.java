package com.naukma.cleaning.aspects;

import com.naukma.cleaning.viewControllers.RateLimited;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.Duration;

@Aspect
@Component
public class RateLimitedAspect {
    public static final int MAX_CALLS = 10;
    public static final Bandwidth LIMIT = Bandwidth.classic(MAX_CALLS, Refill.greedy(MAX_CALLS, Duration.ofMinutes(1)));
    private static Logger logger = LoggerFactory.getLogger(RateLimitedAspect.class);
    private final Bucket bucket;

    public RateLimitedAspect() {
        this.bucket = Bucket.builder()
                .addLimit(LIMIT)
                .build();
    }

    @Around("@annotation(rateLimited)")
    public Object checkRateLimit(ProceedingJoinPoint jp, RateLimited rateLimited) throws Throwable {
        if (!bucket.tryConsume(1)) {
            logger.warn("Can't call method! Max {} requests per minute", MAX_CALLS);
            throw new RuntimeException("Requests are limited for method: " + jp.getSignature().getName());
        }
        return jp.proceed();
    }
}
