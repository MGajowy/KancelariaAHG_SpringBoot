package pl.kancelaria.AHG.externalApi.saosApi.restApi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.externalApi.saosApi.service.SaosService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@RestController
@RequiredArgsConstructor
public class SaosPublicRestApi implements pl.kancelaria.AHG.externalApi.saosApi.shared.SaosPublicRestApi {

    private final SaosService saosService;

    @Override
    public String getJudgments() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> response = saosService.getTenJudgments();
        if (response.statusCode() == 200)
            return response.body();
        return String.valueOf(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
}
