package pl.kancelaria.AHG.shared.restapi.modules.about.onlineHelp.pub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.modules.about.onlineHelp.dto.OnlineHelpRequestDto;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path(OnlineHelpPublicRestApiUrl.SCIEZKA_POMOC_ONLINE)
@RequestMapping(OnlineHelpPublicRestApiUrl.SCIEZKA_POMOC_ONLINE)
public interface IOnlineHelpPublicRestApi {

    @POST
    @PostMapping(OnlineHelpPublicRestApiUrl.WYSLIJ_POWIADOMIENIE)
    @Path(OnlineHelpPublicRestApiUrl.WYSLIJ_POWIADOMIENIE)
    ResponseEntity<HttpStatus> wyslijPowiadomienie(@RequestBody OnlineHelpRequestDto request);
}
