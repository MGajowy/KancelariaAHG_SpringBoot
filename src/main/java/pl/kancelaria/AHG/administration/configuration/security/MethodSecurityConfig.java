package pl.kancelaria.AHG.administration.configuration.security;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.vote.AffirmativeBased;
//import org.springframework.security.access.vote.RoleVoter;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
//import org.springframework.security.config.core.GrantedAuthorityDefaults;

/**
 * @author Michal
 * @created 29/07/2020
 */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = false, jsr250Enabled = false)
public class MethodSecurityConfig
        //extends GlobalMethodSecurityConfiguration
{
//
//    private final String ROLE_PREFIX = "";
//
//    @Bean
//    GrantedAuthorityDefaults grantedAuthorityDefaults(){
//        return new GrantedAuthorityDefaults(ROLE_PREFIX);
//    }
//
//    protected AccessDecisionManager accessDecisionManager(){
//        AffirmativeBased accessDecisionManager = (AffirmativeBased) super.accessDecisionManager();
//
//        accessDecisionManager.getDecisionVoters().stream().filter(RoleVoter.class::isInstance).map(RoleVoter.class::cast).forEach(it -> it.setRolePrefix(ROLE_PREFIX));
//        return accessDecisionManager;
//    }

}
