package com.nihongo.admin.level;

import com.nihongo.common.entity.Lesson;
import com.nihongo.common.entity.Level;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LevelRepository extends PagingAndSortingRepository<Level, Integer> {
    @Query("SELECT lv FROM Level lv WHERE lv.name = :level")
    public Level levelByName(String level);


}
