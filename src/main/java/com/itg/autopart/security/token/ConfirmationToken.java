package com.itg.autopart.security.token;

import com.itg.autopart.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "confirmationtokens")
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="token")
    private String token;
    @Column(name="local_date_time")
    private LocalDateTime localDateTime;
    @Column(name="expires_at")
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public ConfirmationToken(String token,
                             LocalDateTime localDateTime,
                             LocalDateTime expiredAt,
                             User user) {
        this.token = token;
        this.localDateTime = localDateTime;
        this.expiresAt = expiredAt;
        this.user=user;
    }
}
