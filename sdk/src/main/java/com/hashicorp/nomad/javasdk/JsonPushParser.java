package com.hashicorp.nomad.javasdk;

import java.nio.ByteBuffer;

/**
 * A stateful JSON parser into which data can be pushed as it arrives.
 */
class JsonPushParser<T> {
    private final JsonParser<T> jsonParser;
    private ByteBuffer partialValue;

    JsonPushParser(JsonParser<T> jsonParser) {
        this.jsonParser = jsonParser;
    }

    /**
     * Pushes new data into the parser, invoking the callback for each newly parsed value.
     */
    public void push(ByteBuffer newData, Callback<T> callback) throws NomadException {
        if (!newData.hasRemaining())
            return;

        ByteBuffer buffer;
        if (partialValue == null) {
            buffer = newData;
        } else {
            buffer = ByteBuffer.allocate(partialValue.remaining() + newData.remaining());
            buffer.put(partialValue);
            buffer.put(newData);
            buffer.flip();
        }
        partialValue = jsonParser.parseRepeatedly(buffer, callback);
    }

    /**
     * @throws ResponseParsingException if there is unconsumed data in the parser, representing a partial value.
     */
    public void close() throws ResponseParsingException {
        if (partialValue != null)
            throw new ResponseParsingException(
                    "Unexpected end of stream after " + partialValue.remaining() + " bytes of a partial JSON value",
                    null,
                    null);
    }

}
