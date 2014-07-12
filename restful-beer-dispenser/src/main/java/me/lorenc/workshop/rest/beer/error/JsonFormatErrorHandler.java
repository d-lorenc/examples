package me.lorenc.workshop.rest.beer.error;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.handler.ErrorHandler;
import org.eclipse.jetty.util.ByteArrayISO8859Writer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatErrorHandler extends ErrorHandler {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
        baseRequest.setHandled(true);
        response.setContentType(MediaType.APPLICATION_JSON);
        response.setHeader(HttpHeader.CACHE_CONTROL.asString(), "must-revalidate,no-cache,no-store");

        ByteArrayISO8859Writer writer = new ByteArrayISO8859Writer(4096);
        String reason = (response instanceof Response) ? ((Response) response).getReason() : null;
        handleErrorPage(request, writer, response.getStatus(), reason);

        writer.flush();
        response.setContentLength(writer.size());
        writer.writeTo(response.getOutputStream());
        writer.destroy();
    }

    @Override
    protected void handleErrorPage(HttpServletRequest request, Writer writer, int code, String reason) throws IOException {
        ErrorItem errorItem = new ErrorItem(code, reason);
        mapper.writeValue(writer, errorItem);
    }

}