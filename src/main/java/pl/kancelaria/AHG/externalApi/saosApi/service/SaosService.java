package pl.kancelaria.AHG.externalApi.saosApi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaosService {

//    private final RestTemplate restTemplate;

    @Value("${saosApi10}")
    private String saosHost;

    public HttpResponse<String> getTenJudgments() throws IOException, InterruptedException, URISyntaxException {
        HttpClient httpClient = HttpClient.newHttpClient();
        String apiEndpoint = saosHost;
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(apiEndpoint))
                .header("Accept", "application/json")
                .GET()
                .build();

        return  httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());

//        HttpHeaders headers = new HttpHeaders();
//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.APPLICATION_JSON);
//        headers.setAccept(mediaTypes);
//
//        RestTemplate restTemplate =  new RestTemplate();
//
//       return restTemplate.exchange(saosHost, HttpMethod.GET, new HttpEntity<String>(headers), String.class);
    }
}
