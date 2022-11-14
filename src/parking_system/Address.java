package parking_system;

import java.util.Objects;

public class Address
{
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    public Address(String streetAddress, String city, String state, String zipCode, String country)
    {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getStreetAddress()
    {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetAddress.equals(address.streetAddress) && city.equals(address.city) && state.equals(address.state)
                && zipCode.equals(address.zipCode) && country.equals(address.country);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(streetAddress, city, state, zipCode, country);
    }

    @Override
    public String toString()
    {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
