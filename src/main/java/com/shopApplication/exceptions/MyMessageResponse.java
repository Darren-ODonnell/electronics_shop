package com.shopApplication.exceptions;

import com.shopApplication.enums.MessageTypes;
import com.shopApplication.payload.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMessageResponse extends MessageResponse {

    private static final Logger logger = LoggerFactory.getLogger(MyMessageResponse.class);

    public MyMessageResponse(String message, MessageTypes type) {
        super(message);
        switch(type) {
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
        }
    }
}
