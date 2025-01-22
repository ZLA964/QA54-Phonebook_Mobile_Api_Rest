package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString

public class ContactDtoLombok {
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String description;

}
/*
id	string
name*	string
lastName*	string
email	string
phone	string
pattern: ^\d{10,15}$
address*	string
description	string
"id": "string",
  "name": "string",
  "lastName": "string",
  "email": "string",
  "phone": "244839279287394",
  "address": "string",
  "description": "string"

 */