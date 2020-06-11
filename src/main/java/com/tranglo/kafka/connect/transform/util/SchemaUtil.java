package com.tranglo.kafka.connect.transform.util;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;

import java.util.Map;

public class SchemaUtil {
    public SchemaUtil() {
    }

    public static SchemaBuilder copySchemaBasics(Schema source) {
        return copySchemaBasics(source, new SchemaBuilder(source.type()));
    }

    public static SchemaBuilder copySchemaBasics(Schema source, SchemaBuilder builder) {
        builder.name(source.name());
        builder.version(source.version());
        builder.doc(source.doc());
        Map<String, String> params = source.parameters();
        if (params != null) {
            builder.parameters(params);
        }

        return builder;
    }
}
