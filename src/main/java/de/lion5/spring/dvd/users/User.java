package de.lion5.spring.dvd.users;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Entity
@JsonIgnoreProperties({"password", "fullName", "phoneNumber", "role", "authorities", "id", "enabled",
        "accountNonLocked", "credentialsNonExpired", "accountNonExpired"})
@Table(name = "Users", schema = "public")
public class User implements UserDetails {          // interface from Spring Security

    private final String username;
    private final String password;
    private String fullName;
    private String phoneNumber;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public User(String username, String password, String fullName, String phoneNumber, String role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
