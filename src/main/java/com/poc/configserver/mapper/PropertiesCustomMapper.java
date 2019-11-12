package com.poc.configserver.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.configserver.domain.entity.Properties;
import com.poc.configserver.domain.entity.PropertiesId;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PropertiesCustomMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String profile;
    private final String label;
    private final String application;

    public PropertiesCustomMapper(String profile, String label, String application) {
        this.profile = profile;
        this.label = label;
        this.application = application;
    }

    /**
     * Generic Object to Properties List(used in config server)
     *
     * @param  - propertiesConfig - Object configuration
     * @return - Properties list (default config server)
     */
    public List<Properties> toListProperties(final Object propertiesConfig) {
        final List<Properties> properties = new ArrayList<>();
        final Map<String, Object> map = objectMapper.convertValue(propertiesConfig, Map.class);
        map.entrySet().stream().forEach(m -> addEntity(m.getKey(), m.getValue(), application, properties));

        return properties;
    }

    /**
     * Parse Map param in Property line (recursive method)
     *
     * @param key         - property key
     * @param value       - property value
     * @param application - application name
     */
    private void addEntity(final String key, final Object value, final String application, final List<Properties> properties) {

        if (value instanceof ArrayList) {

            final ArrayList<Map<String, Object>> listProperties = (ArrayList<Map<String, Object>>) value;

            int count = 0;
            for (Map<String, Object> map : listProperties) {
                final int num = count;
                map.entrySet().stream().forEach(m -> addEntity(key.concat("[" + num + "]").concat(".").concat(m.getKey()), m.getValue(), application, properties));
                count++;
            }
        } else if (value instanceof LinkedHashMap) {
            LinkedHashMap<String, Object> list = (LinkedHashMap<String, Object>) value;
            list.entrySet().stream().forEach(m -> addEntity(key.concat(".").concat(m.getKey()), m.getValue(), application, properties));
        } else {
            properties.add(new Properties(new PropertiesId(application, profile, label, key), value.toString()));
        }
    }
}
