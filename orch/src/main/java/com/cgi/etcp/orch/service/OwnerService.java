package com.cgi.etcp.orch.service;

import com.cgi.etcp.orch.Properties;
import com.cgi.etcp.orch.dto.OwnerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class OwnerService {

    @Autowired private Properties properties;
    @Autowired private RestTemplate restTemplate;

    public Optional<OwnerDTO> getOwner(final int pId) {
        try {
            return Optional.ofNullable( restTemplate.exchange(
                                    properties.getOwnerURL() + pId, HttpMethod.GET, new HttpEntity<>(createHeaders()), OwnerDTO.class)
                                        .getBody()  );
        } catch (final Exception e) {
            log.warn("Exception when fetching an owner with ID {}", pId, e);
            return Optional.empty();
        }
    }

    public Optional<OwnerDTO> setLanguage(final int pId, final String pLang){
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(pId));
        map.put("language", pLang);
        try {
            return Optional.ofNullable(restTemplate.exchange(properties.getOwnerURL() + "setLang?id=" + pId, HttpMethod.POST,
                                    new HttpEntity<>(map, createHeaders()), OwnerDTO.class).getBody());
        } catch (final Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private HttpHeaders createHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", ("Bearer " + properties.getSecret()));
        return headers;
    }

    private HttpHeaders createHeaders(final Map<String, String> pHeaders) {
        final HttpHeaders headers = new HttpHeaders();
        pHeaders.forEach(headers::set);
        log.info(headers.toString());
        return headers;
    }
}
