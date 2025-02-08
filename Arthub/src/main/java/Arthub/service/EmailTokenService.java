package Arthub.service;


public interface EmailTokenService {

    String generateAndSendToken(String email);
}