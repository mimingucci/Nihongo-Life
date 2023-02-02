package com.nihongo.common.entity;


import java.util.Objects;

public class StudentDTO {
    private String email;
    private String name;
    private String password;

    public StudentDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public StudentDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static StudentDTO convertStudentToDTO(Student student){
        return new StudentDTO(student.getEmail(), student.getName(), student.getPassword());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDTO that = (StudentDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(name, that.name) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }
}
