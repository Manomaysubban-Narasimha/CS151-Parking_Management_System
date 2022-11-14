package parking_system;

public class ParkingFullException extends Exception
{
    public ParkingFullException()
    {
        super("Parking is full");
    }
}
