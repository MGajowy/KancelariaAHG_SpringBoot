package pl.kancelaria.AHG.externalApi.saosApi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class SaosService {

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

        return httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
    }
}
