package org.launchcode.hellospring.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class EventDetails extends AbstractEntity{

    @Size(max = 500, message = "Descrierea e prea lunga")
    private String description;

    @NotBlank(message = "Mailul trebuie dat")
    @Email(message = "Nu este formatul de mail corect")
    private String contactEmail;



    public EventDetails(@Size(max = 500, message = "Descrierea e prea lunga") String description, @NotBlank(message = "Mailul trebuie dat") @Email(message = "Nu este formatul de mail corect") String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }



    public EventDetails(){}



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
