package pl.kancelaria.AHG.externalApi.saosApi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaosService {

    @Value("${saosApi10}")
    private String saosHost;

    public ResponseEntity<String> getTenJudgments() throws IOException, InterruptedException, URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypes);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.exchange(saosHost, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
    }
}
