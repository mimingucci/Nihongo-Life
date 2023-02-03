package com.nihongo.admin.student;

import com.nihongo.common.entity.AuthenticationType;
import com.nihongo.common.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    @Query("UPDATE Student st SET st.totalScore = :score WHERE st.id = :id")
    @Modifying
    public void updateTotalScore(Integer score, Integer id);
    @Query("UPDATE Student st SET st.authType = :authType WHERE st.id = :id")
    @Modifying
    public void updateAuthenticationType(AuthenticationType authType, Integer id);

    @Query("UPDATE Student st SET st.enabled = :enable WHERE st.id = :id")
    @Modifying
    public void updateEnabledStatus(Integer id, Boolean enable);

    @Query("SELECT st FROM Student st WHERE st.name LIKE %:keyword% OR st.email LIKE %:keyword%")
    public Page<Student> listByPage(String keyword, Pageable pageable);

    public Page<Student> findAll(Pageable pageable);

    public Student findStudentByEmail(String email);

    public Long countById(Integer id);
}
