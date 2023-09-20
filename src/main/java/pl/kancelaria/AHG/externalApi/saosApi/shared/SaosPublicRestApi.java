package pl.kancelaria.AHG.externalApi.saosApi.shared;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.net.URISyntaxException;

@Path(value = SaosPublicRestApiUrl.PATH_SAOS)
@RequestMapping(value = SaosPublicRestApiUrl.PATH_SAOS)
public interface SaosPublicRestApi {

    @GET
    @GetMapping(SaosPublicRestApiUrl.JUDGMENTS_GET10)
    @Path(SaosPublicRestApiUrl.JUDGMENTS_GET10)
    String getJudgments() throws IOException, URISyntaxException, InterruptedException;
}
