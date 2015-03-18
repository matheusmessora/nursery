package br.com.pandox.nursery.rest;


import br.com.pandox.nursery.entity.Resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestUtil {

    public static String toJson(Resource resource) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(resource);
        return json;
        //            httpEntity = new StringEntity(json, Charset.forName("UTF-8"));
        //            return this;
    }


    private static <T> Resource createResponseObject(HttpResponse httpResponse, Class<T> clazz) throws IOException {

        String stringResponse = createStringResponse(httpResponse);

        ObjectMapper mapper = new ObjectMapper();
//        mapper.setDateFormat(new SimpleDateFormat(DateFormatter.CUBUS_SUPPORT_DATE_FORMAT));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule dateModule = new SimpleModule("DateModule", new Version(1, 0, 0, null, null, null));
//        dateModule.addDeserializer(Date.class, new JsonDateDeserializer());
//        dateModule.addSerializer(Date.class, new JsonDateSerializer());
        mapper.registerModule(dateModule);

        JavaType type = mapper.getTypeFactory().constructParametricType(Resource.class, clazz);

        return mapper.readValue(stringResponse, type);
    }

    private static String createStringResponse(HttpResponse response) throws IOException {

        HttpEntity entity = response.getEntity();
        String stringResponse = EntityUtils.toString(entity);
        EntityUtils.consume(entity);

        return stringResponse;
    }
}
