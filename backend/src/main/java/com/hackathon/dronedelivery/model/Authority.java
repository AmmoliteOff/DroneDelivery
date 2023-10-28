package com.hackathon.dronedelivery.model;

import com.hackathon.dronedelivery.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @ManyToOne(optional = false)
    private User user;

    @Override
    public String getAuthority() {
        return userRole.name();
    }

    public Authority(UserRole userRole) {
        this.userRole = userRole;
    }
}
