package com.cgi.ectp.orch.service;

import com.cgi.ectp.orch.Properties;
import com.cgi.ectp.orch.dto.OwnerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class OwnerService {

    @Autowired private RestTemplate restTemplate;
    @Autowired private Properties properties;

    public Optional<OwnerDTO> getOwner(final int pId) {
        try {
            return Optional.ofNullable( restTemplate.exchange(properties.getOwnerURL() + pId, HttpMethod.GET, new HttpEntity<>(createHeaders()), OwnerDTO.class)
                                        .getBody()  );
        } catch (final Exception e) {
            log.warn("Exception when fetching an owner with ID {}", pId, e);
            return Optional.empty();
        }
    }

    private HttpHeaders createHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        return headers;
    }
}
