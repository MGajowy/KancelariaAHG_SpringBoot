package pl.kancelaria.AHG.externalApi.saosApi.restApi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.externalApi.saosApi.service.SaosService;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequiredArgsConstructor
public class SaosPublicRestApi implements pl.kancelaria.AHG.externalApi.saosApi.shared.SaosPublicRestApi {

    private final SaosService saosService;

    @Override
    public ResponseEntity<String> getJudgments() throws IOException, URISyntaxException, InterruptedException {
        ResponseEntity<String> response = saosService.getTenJudgments();
        if (response.getStatusCode().value() == 200)
            return response;
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
