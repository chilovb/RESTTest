package com.example.demo;

public interface ExternalApiService {

    Object getCountryData(String countryCode) throws Exception;
}