package Arthub.api;

import Arthub.entity.TypeOfRank;
import Arthub.repository.TypeOfRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/Rank/")
public class RankAPI {

    @Autowired
    TypeOfRankRepository typeOfRankRepository;

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
}
