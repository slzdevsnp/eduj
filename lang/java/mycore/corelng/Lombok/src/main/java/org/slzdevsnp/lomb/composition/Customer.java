package org.slzdevsnp.lomb.composition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;

@NoArgsConstructor
@Getter
@Setter
public class Customer implements HasContactInformation{
    private int age;

    @Delegate(types ={HasContactInformation.class})
    private final ContactInformationSupportAdapter contactInformation =
            new ContactInformationSupportAdapter();

    ///this is not needed Lombok takes care for it
    /*
    public String getFirstName(){
        return "info_support:" + contactInformation.getFirstName();
    }
    public void setFirstName(String name){
        contactInformation.setFirstName(name);
    }
    public String getLastName(){
        return "info_support:" + contactInformation.getLastName();
    }
    public void setLastName(String name){
        contactInformation.setLastName(name);
    }
    public String getPhoneNr(){
        return "info_support:" + contactInformation.getPhoneNr();
    }
    public void setPhoneNr(String name){
        contactInformation.setPhoneNr(name);
    }
*/

}
