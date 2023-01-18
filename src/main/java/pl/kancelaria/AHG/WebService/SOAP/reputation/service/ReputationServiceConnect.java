package pl.kancelaria.AHG.WebService.SOAP.reputation.service;

import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.ReputationInformationService;

import java.net.MalformedURLException;

public interface ReputationServiceConnect {

    ReputationInformationService getReputationService() throws MalformedURLException;
}
