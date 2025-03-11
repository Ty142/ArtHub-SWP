package Arthub.service;

import Arthub.dto.RankDTO;

import java.text.ParseException;

public interface RankService {
    void AddRankToUser(RankDTO rankDTO) throws ParseException;
}
