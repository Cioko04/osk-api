package com.example.osk.model;


import com.example.osk.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String secondName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private LocalDate dob;
    @Transient
    private Integer age;

    @OneToMany(mappedBy = "student")
    private List<Course> courses = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "instructors_category",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList = new ArrayList<>();

    public User(UserRequest userRequest){
        this.name = userRequest.getName();
        this.secondName = userRequest.getSecondName();
        this.lastName = userRequest.getLastName();
        this.email = userRequest.getEmail();
        this.password = userRequest.getPassword();
        this.dob = userRequest.getDob();
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

}
