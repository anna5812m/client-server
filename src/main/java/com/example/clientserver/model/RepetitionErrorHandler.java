package com.example.clientserver.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.Charset;

public class RepetitionErrorHandler extends DefaultResponseErrorHandler {
    private Logger log = LoggerFactory.getLogger(RepetitionErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response)
        throws IOException {
        log.error(response.getStatusCode().toString());
        log.error(StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
    }
}
