package ru.stqa.selenium.model;

public class Customers {
    public static Builder newEntity() {
        return new Customers().new Builder();
    }

    private String firstname;
    private String lastname;
    private String address;
    private String postcode;
    private String city;
    private String country;
    private String zone;
    private String email;
    private String phone;
    private String password;

    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public String getAddress() {
        return address;
    }
    public String getPostcode() {
        return postcode;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getZone() {
        return zone;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getPassword() {
        return password;
    }

    public class Builder {
        private Builder() {
        }

        public Builder withFirstname(String firstname) {
            Customers.this.firstname = firstname;
            return this;
        }
        public Builder withLastname(String lastname) {
            Customers.this.lastname = lastname;
            return this;
        }
        public Builder withAddress(String address) {
            Customers.this.address = address;
            return this;
        }
        public Builder withPostcode(String postcode) {
            Customers.this.postcode = postcode;
            return this;
        }
        public Builder withCity(String city) {
            Customers.this.city = city;
            return this;
        }
        public Builder withCountry(String country) {
            Customers.this.country = country;
            return this;
        }
        public Builder withZone(String zone) {
            Customers.this.zone = zone;
            return this;
        }
        public Builder withEmail(String email) {
            Customers.this.email = email;
            return this;
        }
        public Builder withPhone(String phone) {
            Customers.this.phone = phone;
            return this;
        }
        public Builder withPassword(String password) {
            Customers.this.password = password;
            return this;
        }
        public Customers build() {
            return Customers.this;
        }
    }
}
