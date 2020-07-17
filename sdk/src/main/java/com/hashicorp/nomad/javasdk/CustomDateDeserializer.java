package com.hashicorp.nomad.javasdk;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import java.io.IOException;
import java.text.ParsePosition;
import java.util.Date;

/**
 * Custom deserializer to parse dates in iso8601 format. This is much much faster and GC friendly than using
 * SimpleDateFormat so highly suitable to (un)serialize lots of date objects. Also it help to fix date parsing
 * issue in iso8601 format.
 *
 * Supported parse format: [yyyy-MM-dd|yyyyMMdd][T(hh:mm[:ss[.sss]]|hhmm[ss[.sss]])]?[Z|[+-]hh[:]mm]]
 *
 *
 * Fix: issue#32: Failed to deserialize timestamp with non-UTC time zone.
 *
 * @see <a href="http://www.w3.org/TR/NOTE-datetime">this specification</a>
 */
public class CustomDateDeserializer extends StdDeserializer<Date> {
    /**
     * Default constructor.
     */
    public CustomDateDeserializer() {
        this(Date.class);
    }

    /**
     * Constructor with class parameter.
     *
     * @param vc class parameter
     */
    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
            try {
                return ISO8601Utils.parse(jp.getText(), new ParsePosition(0));
            } catch (Exception e) {
                throw new IOException(e);
            }
        } else {
            throw new IOException("Unparseable data which is expected as STRING: " + jp.getCurrentToken().toString());
        }
    }
}
