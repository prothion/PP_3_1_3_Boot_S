package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import javax.persistence.*;
import java.util.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Поле name не должно быть пустым.")
    @Size(min = 3, max = 32, message = "Число символов в имени должно быть от 3 до 32")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Поле lastname не должно быть пустым.")
    @Size(min = 3, max = 32, message = "Число символов в фамилии должно быть от 3 до 32")
    private String lastName;

    @Column(name = "age")
    @Min(value = 0, message = "Минимальное значение 0")
    @Max(value = 150, message = "Максимальное значение возраста - 150")
    private Integer age;

    @Column(name="car")
    @NotEmpty(message = "Поле не должно быть пустым.")
    @Size(min = 1, max = 32, message = "Количество допустимых символов от 1 до 32.")
    private String car;

    @Column(name = "email")
    @NotEmpty(message = "Поле email не может быть пустым")
    @Email(message = "Некорректная запись почты")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="timeRegistration")
    private LocalDateTime timeRegistration;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String password, String firstName, String lastName, Integer age, String car, String email, Set<Role> roles) {
        this.name = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.age = age;
        this.car = car;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = new BCryptPasswordEncoder(8).encode(password);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public LocalDateTime getTimeRegistration() {
        return LocalDateTime.now();
    }

    public LocalDateTime setTimeRegistration() {
        return timeRegistration;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getRolesToString() {
        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            sb.append(role.toString());
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age +
                ", car='" + car + '\'' +
                ", timeRegistration='" + timeRegistration + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';}
}
