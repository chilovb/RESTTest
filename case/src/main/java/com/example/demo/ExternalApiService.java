package com.example.demo;

public interface ExternalApiService {

    Product getCountryData(String countryCode) throws Exception;
}