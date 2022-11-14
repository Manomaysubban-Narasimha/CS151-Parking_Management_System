package parking_system;

public abstract class Vehicle
{
    private String licenseNumber;
    private final VehicleType type;
    private ParkingTicket ticket;

    public Vehicle(VehicleType type)
    {
        this.type = type;
    }

    public void assignTicket(ParkingTicket ticket)
    {
        this.ticket = ticket;
    }

    public VehicleType getType()
    {
        return type;
    }

    public String getLicenseNumber()
    {
        return licenseNumber;
    }

    public ParkingTicket getTicket()
    {
        return ticket;
    }
}
