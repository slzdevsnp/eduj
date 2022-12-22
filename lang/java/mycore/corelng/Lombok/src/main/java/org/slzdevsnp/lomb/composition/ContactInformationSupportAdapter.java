package org.slzdevsnp.lomb.composition;

import lombok.Data;

@Data
public class ContactInformationSupportAdapter implements HasContactInformation {

    private String firstName;
    private String lastName;
    private String phoneNr;

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
