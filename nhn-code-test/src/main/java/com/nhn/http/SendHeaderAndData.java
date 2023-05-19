package com.nhn.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

/*HttpServletResponse - writer*/
public class SendHeaderAndData {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendHeaderAndData.class);
    public static void send(Writer out, String responseCode, String contentType, String body){
        Date now = new Date();
        try {
            out.write(responseCode + "\r\n");
            out.write("Date: " + now + "\r\n");
            out.write("Server: JHTTP 2.0\r\n");
            out.write("Content-length: " + body.length() + "\r\n");
            out.write("Content-type: " + contentType + "\r\n\r\n");
            out.write(body);
            out.flush();
        }catch (IOException ioe){
            LOGGER.error("SendHeaderAndData Error  ", ioe );
        }
    }
    //
}
