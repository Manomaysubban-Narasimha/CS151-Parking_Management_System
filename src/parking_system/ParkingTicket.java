package parking_system;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ParkingTicket
{
    private static final Set<Integer> ASSIGNED_NUMBERS = new HashSet<>();
    private int ticketNumber;
    private ParkingTicketStatus status;

    public ParkingTicket(ParkingTicketStatus status)
    {
        assignTicketNumber();
        this.status = status;
    }

    private void assignTicketNumber()
    {
        Random generator = new Random();
        int newTicketNumber;
        do
            newTicketNumber = generator.nextInt(Integer.MAX_VALUE);
        while(ASSIGNED_NUMBERS.contains(newTicketNumber));
        ASSIGNED_NUMBERS.add(newTicketNumber);
    }

    public void setStatus(ParkingTicketStatus status)
    {
        this.status = status;
    }

    public int getTicketNumber()
    {
        return ticketNumber;
    }

    public void saveInDB()
    {
        // TODO: Save this ticket in Database
    }
}
