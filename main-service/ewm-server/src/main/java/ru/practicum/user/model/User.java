package ru.practicum.user.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS", schema = "PUBLIC")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_EMAIL", unique = true)
    private String email;

    /*private String lastName;
    private String nickName;
    private LocalDate birthDay;*/

}
