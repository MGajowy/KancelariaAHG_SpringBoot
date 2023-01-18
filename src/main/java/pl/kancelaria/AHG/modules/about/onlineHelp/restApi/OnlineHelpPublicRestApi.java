package pl.kancelaria.AHG.modules.about.onlineHelp.restApi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.about.onlineHelp.dto.OnlineHelpRequestDto;
import pl.kancelaria.AHG.modules.about.onlineHelp.service.OnlineHelpService;
import pl.kancelaria.AHG.shared.restapi.modules.about.onlineHelp.pub.IOnlineHelpPublicRestApi;

@RestController
@RequiredArgsConstructor
public class OnlineHelpPublicRestApi implements IOnlineHelpPublicRestApi {

    private final OnlineHelpService onlineHelpService;

    @Override
    public ResponseEntity<HttpStatus> sendEmailNotification(OnlineHelpRequestDto request) {
        return onlineHelpService.sendEmailNotification(request);
    }
}
