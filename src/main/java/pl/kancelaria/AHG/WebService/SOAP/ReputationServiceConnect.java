package pl.kancelaria.AHG.WebService.SOAP;

import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.ReputationInformationService;

import java.net.MalformedURLException;

public interface ReputationServiceConnect {

    ReputationInformationService getReputationService() throws MalformedURLException;
}
