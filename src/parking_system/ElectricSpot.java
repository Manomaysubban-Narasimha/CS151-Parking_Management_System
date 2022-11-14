package parking_system;

public class ElectricSpot extends ParkingSpot
{
    public ElectricSpot(int number)
    {
        super(ParkingSpotType.ELECTRIC, number);
    }
}
