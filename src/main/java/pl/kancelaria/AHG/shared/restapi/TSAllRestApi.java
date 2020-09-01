package pl.kancelaria.AHG.shared.restapi;

import pl.kancelaria.AHG.shared.restapi.administration.restapi.pub.AdministrationPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.administration.restapi.secured.AdministrationSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.auth.restApi.pub.AuthPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured.CategorySecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub.ResolutionPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.secured.ResolutionSecuredRestApi;
import pl.kancelaria.AHG.shared.restapi.users.restapi.pub.UserPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApi;

import javax.ws.rs.core.Application;
import java.util.Arrays;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Michal
 * @created 11/08/2020
 */
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
                AdministrationPublicRestApi.class,
                AdministrationSecuredRestApi.class,
                AuthPublicRestApi.class
                ));
    }
}
