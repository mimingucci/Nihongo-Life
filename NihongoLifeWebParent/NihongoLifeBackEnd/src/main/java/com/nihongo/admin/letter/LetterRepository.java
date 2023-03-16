package com.nihongo.admin.letter;

import com.nihongo.common.entity.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LetterRepository extends CrudRepository<Letter, Integer> {
    @Query(value = "SELECT * FROM letters WHERE MATCH(japanese_letter, latinh_letter) "
            + "AGAINST (?1)", nativeQuery = true)
    public List<Letter> findLetterByJapaneseOrLatinhStyle(String letter);

    @Query("SELECT l FROM Letter l WHERE l.japaneseLetter = ?1")
    public Letter findByJapaneseStyle(String letter);
    @Query("DELETE FROM Letter WHERE japaneseLetter = ?1")
    public void delete(String letter);
}
