package com.greenerself.sustainability.userlanding.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_security_answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSecurityAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private SecurityQuestion question;

    @Column(nullable = false)
    private String answer;

}
