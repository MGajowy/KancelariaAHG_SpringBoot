package pl.kancelaria.AHG.administration.configuration.jwt.model;
import java.io.Serializable;
/**
 * @author Michal
 * @created 01/09/2020
 */
public class JwtResponse implements Serializable{
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }
    public String getToken() {
        return this.jwttoken;
    }
}
