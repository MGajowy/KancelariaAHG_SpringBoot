package pl.kancelaria.AHG.shared.restapi;

import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub.CategoryPublicRestApi;
import pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.secured.CategorySecuredRestApi;

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
        return new LinkedHashSet<>(Arrays.asList(CategoryPublicRestApi.class, CategorySecuredRestApi.class));
    }
}
