package pl.kancelaria.AHG.shared.restapi.reputations.pub;

import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.REPUTATION;
import static pl.kancelaria.AHG.shared.restapi.RestApiUrlStale.REST_PATH_PUBLIC;


public class ReputationPublicRestApiUrl {
    public static final String PATH_REPUTATION = REPUTATION + REST_PATH_PUBLIC;
    public static final String REPUTATION_GET_ONE = "/getOne";
    public static final String REPUTATION_GET_ALL = "/getAll";
    public static final String REPUTATION_ADD = "/addReputation";
    public static final String REPUTATION_ADD_LIKE = "/addLike";
    public static final String REPUTATION_ADD_NOT_LIKE = "/addNotLike";
}
