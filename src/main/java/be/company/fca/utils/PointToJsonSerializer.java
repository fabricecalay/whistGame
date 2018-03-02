package be.company.fca.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Point;

import java.io.IOException;

public class PointToJsonSerializer extends JsonSerializer<Point> {

    @Override
    public void serialize(Point value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException,
            JsonProcessingException {

        String jsonValue = "null";
        try
        {
            if(value != null) {
                double lat = value.getY();
                double lon = value.getX();
                int srid = value.getSRID();
                jsonValue = String.format("POINT (%s %s %s)", lat, lon, srid);
            }
        }
        catch(Exception e) {}

        jgen.writeString(jsonValue);
    }
}