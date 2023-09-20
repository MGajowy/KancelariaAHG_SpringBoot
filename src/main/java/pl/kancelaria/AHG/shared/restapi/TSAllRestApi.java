package pl.kancelaria.AHG.shared.restapi;

import pl.kancelaria.AHG.externalApi.saosApi.shared.SaosPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.administration.restapi.pub.IAdministrationPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.administration.restapi.secured.IAdministrationSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.auth.restApi.pub.IAuthPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.about.onlineHelp.pub.IOnlineHelpPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured.CategorySecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.pub.CategoryRegulationPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categoriesRegulations.restApi.secured.CategoryRegulationSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.document.restApi.pub.DocumentPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.document.restApi.secured.DocumentSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.pub.RegulationPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.secured.RegulationSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub.ResolutionPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.secured.ResolutionSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.reputations.pub.ReputationPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.reputations.secured.ReputationSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.users.restapi.pub.UserPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApi;

import javax.ws.rs.core.Application;
import java.util.Arrays;

import java.util.LinkedHashSet;
import java.util.Set;


public class TSAllRestApi extends Application {
    public TSAllRestApi() {
    }

    @Override
    public Set<Class<?>> getClasses() {
        return new LinkedHashSet<>(Arrays.asList(
                CategoryPublicRestApi.class,
                CategorySecuredRestApi.class,
                ResolutionPublicRestApi.class,
                ResolutionSecuredRestApi.class,
                UserPublicRestApi.class,
                UserSecuredRestApi.class,
                IAdministrationPublicRestApi.class,
                IAdministrationSecuredRestApi.class,
                IAuthPublicRestApi.class,
                CategoryRegulationPublicRestApi.class,
                CategoryRegulationSecuredRestApi.class,
                RegulationPublicRestApi.class,
                RegulationSecuredRestApi.class,
                IOnlineHelpPublicRestApi.class,
                ReputationPublicRestApi.class,
                ReputationSecuredRestApi.class,
                DocumentPublicRestApi.class,
                DocumentSecuredRestApi.class,
                SaosPublicRestApi.class
                ));
    }
}
