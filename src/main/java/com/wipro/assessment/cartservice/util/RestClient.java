package com.wipro.assessment.cartservice.util;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@AllArgsConstructor
public class RestClient<T> {

    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    private final Class<T> cls;

    public Optional<T> getResource(String url) {
        ResponseEntity<T> responseEntity = this.restTemplate.exchange( url, HttpMethod.GET, new HttpEntity<>( new HttpHeaders() ), cls);

        if (!HttpStatus.OK.equals( responseEntity.getStatusCode() )) {
            throw new IllegalArgumentException();
        }

        return Optional.ofNullable( responseEntity.getBody() );
    }

}
