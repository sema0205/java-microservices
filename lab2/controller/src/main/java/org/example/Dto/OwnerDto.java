package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Cat;
import org.example.model.Owner;
import org.joda.time.DateTime;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {

    private String name;
    private DateTime birthDate;

    private List<Cat> cats;

    public Owner ToModel() {
        Owner owner = new Owner();

        owner.setName(name);
        owner.setBirthDate(birthDate);
        owner.setCats(cats);

        return owner;
    }

}
