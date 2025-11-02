package dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString

public class Contact {
    private String id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String address;
    private String description;
}
