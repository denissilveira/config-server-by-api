package com.poc.configserver.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.configserver.domain.repository.PropertiesRepository;
import com.poc.configserver.exception.PropertieConfigException;
import com.poc.configserver.mapper.PropertiesCustomMapper;
import com.poc.configserver.service.client.CloudConfigClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;
    @Autowired
    private CloudConfigClient cloudConfigClient;
    @Autowired
    private ObjectMapper objectMapper;

    @Transactional
    public void save(final String profile, final String label, final String application, final Object dto) {
        final PropertiesCustomMapper mapper = new PropertiesCustomMapper(profile, label, application);
        propertiesRepository.saveAll(mapper.toListProperties(dto));
    }

    public LinkedHashMap findConfigurations(final String profile, final String label, final String application) throws IOException {
        return objectMapper.readValue(cloudConfigClient.getConfiguration(label, application, profile), LinkedHashMap.class);
    }

    public List<String> findAllApplications() {
        return propertiesRepository.findAllApplications();
    }

    public void update(final String profile, final String label, final String application, final Object dto) throws IOException, PropertieConfigException {

        final LinkedHashMap configurations = findConfigurations(profile, label, application);
        if(CollectionUtils.isEmpty(configurations)) {
            throw new PropertieConfigException();
        }
        save(profile, label, application, dto);
    }

}
