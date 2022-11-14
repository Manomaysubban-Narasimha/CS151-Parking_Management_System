package parking_system;

public class ParkingDisplayBoard
{
    private String id;
    private HandicappedSpot handicappedFreeSpot;
    private CompactSpot compactFreeSpot;
    private LargeSpot largeFreeSpot;
    private MotorbikeSpot motorbikeFreeSpot;
    private ElectricSpot electricFreeSpot;

    public void showEmptySpotNumber()
    {
        StringBuilder message = new StringBuilder();
        if(handicappedFreeSpot.isFree())
            message.append("Free Handicapped: ").append(handicappedFreeSpot.getNumber());
        else
            message.append("Handicapped is full");

        message.append('\n');

        if(compactFreeSpot.isFree())
            message.append("Free Compact: ").append(compactFreeSpot.getNumber());
         else
            message.append("Compact is full");

        message.append('\n');

        if(largeFreeSpot.isFree())
            message.append("Free Large: ").append(largeFreeSpot.getNumber());
        else
            message.append("Large is full");

        message.append('\n');

        if(motorbikeFreeSpot.isFree())
            message.append("Free Motorbike: ").append(motorbikeFreeSpot.getNumber());
        else
            message.append("Motorbike is full");

        message.append('\n');

        if(electricFreeSpot.isFree())
            message.append("Free Electric: ").append(electricFreeSpot.getNumber());
        else
            message.append("Electric is full");

        show(message.toString());
    }

    public void setElectricFreeSpot(ElectricSpot electricFreeSpot)
    {
        this.electricFreeSpot = electricFreeSpot;
    }

    public void setCompactFreeSpot(CompactSpot compactFreeSpot)
    {
        this.compactFreeSpot = compactFreeSpot;
    }

    public void setMotorbikeFreeSpot(MotorbikeSpot motorbikeFreeSpot)
    {
        this.motorbikeFreeSpot = motorbikeFreeSpot;
    }

    public void setHandicappedFreeSpot(HandicappedSpot handicappedFreeSpot)
    {
        this.handicappedFreeSpot = handicappedFreeSpot;
    }

    public void setLargeFreeSpot(LargeSpot largeFreeSpot)
    {
        this.largeFreeSpot = largeFreeSpot;
    }

    public void show(String displayInformation)
    {
        System.out.println(displayInformation);
    }

    public HandicappedSpot getHandicappedFreeSpot()
    {
        return handicappedFreeSpot;
    }

    public CompactSpot getCompactFreeSpot()
    {
        return compactFreeSpot;
    }

    public LargeSpot getLargeFreeSpot()
    {
        return largeFreeSpot;
    }

    public MotorbikeSpot getMotorbikeFreeSpot()
    {
        return motorbikeFreeSpot;
    }

    public ElectricSpot getElectricFreeSpot()
    {
        return electricFreeSpot;
    }
}

