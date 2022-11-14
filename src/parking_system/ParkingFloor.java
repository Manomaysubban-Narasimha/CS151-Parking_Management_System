package parking_system;

import java.util.HashMap;
import java.util.Map;

public class ParkingFloor
{
    private final int floorNumber;
    private int freeLargeSpotCount;
    private int freeHandicappedSpotCount;
    private int freeElectricSpotCount;
    private int freeMotorbikeSpotCount;
    private int freeCompactSpotCount;
    private Map<Integer, HandicappedSpot> handicappedSpots;
    private Map<Integer, ElectricSpot> electricSpots;
    private Map<Integer, LargeSpot> largeSpots;
    private Map<Integer, CompactSpot> compactSpots;
    private Map<Integer, MotorbikeSpot> motorbikeSpots;
    private Map<String, CustomerInfoPortal> infoPortals;
    private ParkingDisplayBoard displayBoard;

    public ParkingFloor(int floorNumber)
    {
        this.floorNumber = floorNumber;
        handicappedSpots = new HashMap<>();
        electricSpots = new HashMap<>();
        largeSpots = new HashMap<>();
        compactSpots = new HashMap<>();
        motorbikeSpots = new HashMap<>();
        infoPortals = new HashMap<>();
    }

    public void addParkingSpot(ParkingSpot spot)
    {
        switch(spot.getType())
        {
            case LARGE -> largeSpots.put(spot.getNumber(), (LargeSpot) spot);
            case HANDICAPPED -> handicappedSpots.put(spot.getNumber(), (HandicappedSpot) spot);
            case ELECTRIC -> electricSpots.put(spot.getNumber(), (ElectricSpot) spot);
            case COMPACT -> compactSpots.put(spot.getNumber(), (CompactSpot) spot);
            case MOTORBIKE -> motorbikeSpots.put(spot.getNumber(), (MotorbikeSpot) spot);
            default -> System.out.println("Wrong parking spot type");
        }
    }

    public boolean assignVehicleToSpot(Vehicle vehicle, ParkingSpot spot)
    {
        if(!spot.isFree())
            return false;
        spot.assignVehicle(vehicle);
        boolean assigned;
        switch(spot.getType())
        {
            case LARGE -> assigned = updateDisplayBoardForLarge(spot);
            case COMPACT -> assigned = updateDisplayBoardForCompact(spot);
            case MOTORBIKE -> assigned = updateDisplayBoardForMotorbike(spot);
            case HANDICAPPED -> assigned = updateDisplayBoardForHandicapped(spot);
            case ELECTRIC -> assigned = updateDisplayBoardForElectric(spot);
            default -> assigned = false;
        }
        return assigned;
    }

    private boolean updateDisplayBoardForHandicapped(ParkingSpot spot)
    {
        boolean updated = false;
        if(displayBoard.getHandicappedFreeSpot().getNumber() == spot.getNumber())
        {
            for(int spotNumber: handicappedSpots.keySet())
            {
                if(handicappedSpots.get(spotNumber).isFree())
                {
                    updated = true;
                    displayBoard.setHandicappedFreeSpot(handicappedSpots.get(spotNumber));
                }
            }
            displayBoard.showEmptySpotNumber();
        }
        return updated;
    }

    private boolean updateDisplayBoardForCompact(ParkingSpot spot)
    {
        boolean updated = false;
        if(displayBoard.getCompactFreeSpot().getNumber() == spot.getNumber())
        {
            for(int spotNumber: compactSpots.keySet())
            {
                if(compactSpots.get(spotNumber).isFree())
                {
                    displayBoard.setCompactFreeSpot(compactSpots.get(spotNumber));
                    updated = true;
                }
            }
            displayBoard.showEmptySpotNumber();
        }
        return updated;
    }

    private boolean updateDisplayBoardForLarge(ParkingSpot spot)
    {
        boolean updated = false;
        if(displayBoard.getLargeFreeSpot().getNumber() == spot.getNumber())
        {
            for(int spotNumber: largeSpots.keySet())
            {
                if(largeSpots.get(spotNumber).isFree())
                {
                    displayBoard.setLargeFreeSpot(largeSpots.get(spotNumber));
                    updated = true;
                }
            }
            displayBoard.showEmptySpotNumber();
        }
        return updated;
    }

    private boolean updateDisplayBoardForMotorbike(ParkingSpot spot)
    {
        boolean updated = false;
        if(displayBoard.getMotorbikeFreeSpot().getNumber() == spot.getNumber())
        {
            for(int spotNumber: motorbikeSpots.keySet())
            {
                if(motorbikeSpots.get(spotNumber).isFree())
                {
                    displayBoard.setMotorbikeFreeSpot(motorbikeSpots.get(spotNumber));
                    updated = true;
                }
            }
            displayBoard.showEmptySpotNumber();
        }
        return updated;
    }

    private boolean updateDisplayBoardForElectric(ParkingSpot spot)
    {
        boolean updated = false;
        if(displayBoard.getElectricFreeSpot().getNumber() == spot.getNumber())
        {
            for(int spotNumber: electricSpots.keySet())
            {
                if(electricSpots.get(spotNumber).isFree())
                {
                    displayBoard.setElectricFreeSpot(electricSpots.get(spotNumber));
                    updated = true;
                }
            }
            displayBoard.showEmptySpotNumber();
        }
        return updated;
    }

    public void freeSpot(ParkingSpot spot)
    {
        spot.removeVehicle();
        switch (spot.getType())
        {
            case LARGE -> freeLargeSpotCount++;
            case COMPACT -> freeCompactSpotCount++;
            case ELECTRIC -> freeElectricSpotCount++;
            case MOTORBIKE -> freeMotorbikeSpotCount++;
            case HANDICAPPED -> freeHandicappedSpotCount++;
            default -> System.out.println("Wrong type of parking spot");
        }
    }

    public boolean isFull()
    {
        return freeLargeSpotCount + freeHandicappedSpotCount + freeElectricSpotCount + freeMotorbikeSpotCount
                + freeCompactSpotCount != 0;
    }
}
