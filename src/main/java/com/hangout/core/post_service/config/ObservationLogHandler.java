package com.hangout.core.post_service.config;

import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.micrometer.common.KeyValue;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;

// Example of plugging in a custom handler that in this case will print a statement before and after all observations take place
@Component
public class ObservationLogHandler implements ObservationHandler<Context> {
    private static final Logger log = LoggerFactory.getLogger(ObservationLogHandler.class);

    @Override
    public void onStart(Context context) {
        log.debug("Before running the observation for context [{}], postType [{}]", context.getName(),
                getPostTyprFromContext(context));
    }

    @Override
    public void onStop(Context context) {
        log.debug("After running the observation for context [{}], postType [{}]", context.getName(),
                getPostTyprFromContext(context));
    }

    @Override
    public boolean supportsContext(Context context) {
        return true;
    }

    private String getPostTyprFromContext(Context context) {
        return StreamSupport.stream(context.getLowCardinalityKeyValues().spliterator(), false)
                .filter(keyValue -> keyValue.getKey().equals("postType"))
                .map(KeyValue::getValue)
                .findFirst()
                .orElse("DEFAULT");
    }

}
