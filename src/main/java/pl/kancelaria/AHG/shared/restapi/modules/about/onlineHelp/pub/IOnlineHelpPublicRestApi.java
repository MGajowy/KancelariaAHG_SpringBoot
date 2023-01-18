package pl.kancelaria.AHG.shared.restapi.modules.about.onlineHelp.pub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.GetReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;
import pl.kancelaria.AHG.modules.about.onlineHelp.dto.OnlineHelpRequestDto;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.MalformedURLException;
import java.util.List;

@Path(OnlineHelpPublicRestApiUrl.SCIEZKA_POMOC_ONLINE)
@RequestMapping(OnlineHelpPublicRestApiUrl.SCIEZKA_POMOC_ONLINE)
public interface IOnlineHelpPublicRestApi {

    @POST
    @PostMapping(OnlineHelpPublicRestApiUrl.WYSLIJ_POWIADOMIENIE)
    @Path(OnlineHelpPublicRestApiUrl.WYSLIJ_POWIADOMIENIE)
    ResponseEntity<HttpStatus> sendEmailNotification(@RequestBody OnlineHelpRequestDto request);

}
