package Arthub.api;

import Arthub.dto.RankDTO;
import Arthub.entity.TypeOfRank;
import Arthub.repository.TypeOfRankRepository;
import Arthub.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/Rank/")
public class RankAPI {

    @Autowired
    TypeOfRankRepository typeOfRankRepository;

    @Autowired
    RankService rankService;


    @GetMapping
    public ResponseEntity<List<TypeOfRank>> getRanks() {
        List<TypeOfRank> typeOfRanks = typeOfRankRepository.getAllTypeOfRanks();
        if (typeOfRanks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(typeOfRanks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeOfRank> getRankById(@PathVariable int id) {
        TypeOfRank typeOfRank = typeOfRankRepository.getTypeOfRankById(id);

        if (typeOfRank == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(typeOfRank);
    }

    @GetMapping("/Current/{accountId}")
    public ResponseEntity<RankDTO> getCurrentRankByAccountId(@PathVariable int accountId) {
        RankDTO rankdto = typeOfRankRepository.getCurrentRankByAccountId(accountId);
        if (rankdto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rankdto);
    }

    @PostMapping("Packages/")
    public ResponseEntity<String> addRankToPackages(@RequestBody RankDTO rankDTO) throws ParseException {
        rankService.AddRankToUser(rankDTO);
        return ResponseEntity.ok("upgrade successfully");
    }

    @DeleteMapping("Packages/Expired/{UserID}/{RankID}")
    public ResponseEntity<String> deleteExpiredRank(@PathVariable int UserID, @PathVariable int RankID) {
        rankService.removeRankToExpired(UserID, RankID);
        return ResponseEntity.ok("Rank deleted successfully");
    }

}
