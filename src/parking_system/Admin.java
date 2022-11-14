package parking_system;

public class Admin extends Account
{
    public Admin(String userName, String password, AccountStatus status, Person person)
    {
        super(userName, password, status, person);
    }

    public boolean addParkingFloor(ParkingFloor floor)
    {
        // TODO
        return false;
    }
    public boolean addParkingSpot(String floorName, ParkingSpot spot)
    {
        // TODO
        return false;
    }
    public boolean addParkingDisplayBoard(String floorName, ParkingDisplayBoard displayBoard)
    {
        // TODO
        return false;
    }
    public boolean addCustomerInfoPanel(String floorName, CustomerInfoPortal infoPanel)
    {
        // TODO
        return false;
    }

    public boolean addEntrancePanel(EntrancePanel entrancePanel)
    {
        // TODO
        return false;
    }
    public boolean addExitPanel(ExitPanel exitPanel)
    {
        // TODO
        return false;
    }
}

