package com.tranglo.kafka.connect.transform.util;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

public class SimpleConfig extends AbstractConfig {
    public SimpleConfig(ConfigDef configDef, Map<?, ?> originals) {
        super(configDef, originals, false);
    }
}
