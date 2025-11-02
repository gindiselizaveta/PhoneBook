package utils;

import dto.Contact;
import net.datafaker.Faker;

public class ContactFactory {
    static Faker faker = new Faker();

    public static void main(String[] args) {
        System.out.println(positiveContact());
    }

    public static Contact positiveContact() {

        return Contact.builder().name(faker.name().firstName()).lastname(faker.name().lastName()).email(faker.internet().emailAddress()).phone(faker.number().digits(14)).address(faker.address().fullAddress()).description("It's my friend's contact").build();
    }

    //        private String name;
//        private String lastname;
//        private String email;
//        private String phone;
//        private String address;
//        private String description;
}
