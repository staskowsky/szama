package pl.szama.user;

import lombok.Getter;
import lombok.Setter;

public enum Role {
    USER("Użytkownik"),
    PREMIUM("Użytkownik+"),
    MOD("Moderator"),
    ADMIN("Administrator");

    @Getter
    @Setter
    private String name;

    Role(String name) {
        this.name = name;
    }
}
