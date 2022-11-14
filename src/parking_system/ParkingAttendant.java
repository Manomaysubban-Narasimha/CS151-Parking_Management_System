package parking_system;

public class ParkingAttendant extends Account
{
    public ParkingAttendant(String userName, String password, AccountStatus status, Person person)
    {
        super(userName, password, status, person);
    }
    public boolean processTicket(String TicketNumber)
    {
        // TODO
        return false;
    }
}
