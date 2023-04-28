package com.camerapipeline.camera_pipeline.provider.services.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.camerapipeline.camera_pipeline.provider.exception.BusinessException;
import com.camerapipeline.camera_pipeline.provider.exception.file.CustomIOException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Client {
    public HttpEntity post(String uri, String data, byte[] file, String fileName) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(uri);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        
        builder.addTextBody("data", data, ContentType.TEXT_PLAIN);
        
        builder.addBinaryBody(
            "file",
            file,
            ContentType.DEFAULT_BINARY,
            fileName
        );
        
        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        try {
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            return responseEntity;
            
        } catch (ClientProtocolException e) {
            throw new BusinessException("Error in the HTTP protocol");
        } catch (IOException e) {
            throw new CustomIOException(
                e.getMessage(), 
                "IO_EXCEPTION",
                Integer.toString(e.hashCode()), 
                e.getCause()
            );
        }
    }
    public JsonNode post(String uri, String requestJson) {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(uri);

        StringEntity requestEntity = new StringEntity(
            requestJson,
            "UTF-8");
        requestEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        httppost.setEntity(
            requestEntity
        );

        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new HttpServerErrorException(HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
            }
            HttpEntity entity = response.getEntity();
            JsonNode data;
            if (entity != null) {
                try (InputStream instream = entity.getContent()) {
                    ObjectMapper mapper = new ObjectMapper();
                    data = mapper.readTree(IOUtils.toString(instream, "utf-8"));
                    
                }
                return data;
            } else {
                throw new BusinessException("No entity response");
            }
        } catch (ClientProtocolException e) {
            throw new BusinessException("Error in the HTTP protocol");
        } catch (IOException e) {
            throw new CustomIOException(
                e.getMessage(), 
                "IO_EXCEPTION",
                Integer.toString(e.hashCode()), 
                e.getCause()
            );
        }
    }
}
