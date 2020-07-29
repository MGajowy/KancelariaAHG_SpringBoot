package pl.kancelaria.AHG.shared.restapi.modules.categories.restapi.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.comon.model.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.comon.model.resolutions.categories.repository.CategoriesRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author Michal
 * @created 29/07/2020
 */
//@Path()
@RequestMapping(value = CategoryPublicRestApiUrl.KATEGORIE)

public interface CategoryPublicRestApi {

}
