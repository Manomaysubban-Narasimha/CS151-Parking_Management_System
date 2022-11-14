package parking_system;

import java.util.Objects;

public abstract class ParkingSpot
{
    private final int number;
    private boolean free;
    private Vehicle vehicle;
    private final ParkingSpotType type;

    public boolean isFree()
    {
        return this.free;
    }

    public ParkingSpot(ParkingSpotType type, int number)
    {
        this.type = type;
        this.free = true;
        this.number = number;
    }

    public ParkingSpotType getType()
    {
        return type;
    }

    public boolean assignVehicle(Vehicle vehicle)
    {
        if(isFree())
        {
            this.vehicle = vehicle;
            free = false;
            return true;
        }
        return false;
    }

    public boolean removeVehicle()
    {
        this.vehicle = null;
        return free = true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSpot that = (ParkingSpot) o;
        return number == that.number && free == that.free && vehicle.equals(that.vehicle) && type == that.type;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(number, free, vehicle, type);
    }

    @Override
    public String toString()
    {
        return "ParkingSpot{" +
                "number=" + number +
                ", free=" + free +
                ", vehicle=" + vehicle +
                ", type=" + type +
                '}';
    }

    public int getNumber()
    {
        return this.number;
    }
}
