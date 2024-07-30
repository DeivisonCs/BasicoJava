package ifba.gsort.partohumano.dtos;

import java.util.UUID;

import lombok.Getter;

@Getter
public class TokenDTO {

    private UUID myUuid;
    private String token;

    public TokenDTO(UUID uuid, String token) {
        this.myUuid = uuid;
        this.token = token;
    }
}
