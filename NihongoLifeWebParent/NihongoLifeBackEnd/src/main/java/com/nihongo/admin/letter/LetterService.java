package com.nihongo.admin.letter;

import com.nihongo.common.entity.Letter;
import com.nihongo.common.exception.LetterExistedException;
import com.nihongo.common.exception.LetterNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LetterService {
    private LetterRepository repo;

    public LetterService(LetterRepository repo) {
        this.repo = repo;
    }

    public void persist(Letter letter) throws LetterExistedException {
         Letter existLetter= repo.findByJapaneseStyle(letter.getJapaneseLetter());
         if(existLetter!=null || existLetter.getJapaneseLetter().equalsIgnoreCase(letter.getJapaneseLetter())){
             throw new LetterExistedException("Letter "+letter.getJapaneseLetter()+" exists");
         }
         repo.save(letter);
    }

    public Letter get(String letter) throws LetterNotFoundException {
        Letter existLetter=repo.findByJapaneseStyle(letter);
        if(existLetter==null){
            throw new LetterNotFoundException("Letter "+letter+" could not find");
        }
        return existLetter;
    }

    public List<Letter> search(String letter){
        List<Letter> letters=repo.findLetterByJapaneseOrLatinhStyle(letter);
        return letters;
    }

    public void delete(String letter){
         repo.delete(letter);
    }

    public void update(String letter, Letter newLetter){
        Letter existLetter=repo.findByJapaneseStyle(letter);
        if(existLetter!=null){
            existLetter.setJapaneseLetter(newLetter.getJapaneseLetter());
            existLetter.setAlphabets(newLetter.getAlphabets());
            existLetter.setExamples(newLetter.getExamples());
            existLetter.setPronunciation(newLetter.getPronunciation());
            existLetter.setLatinhLetter(newLetter.getLatinhLetter());
            repo.save(existLetter);
        }else{
            repo.save(newLetter);
        }
    }

}
