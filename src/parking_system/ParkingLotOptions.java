package parking_system;

public class ParkingLotOptions {
	public enum SpotOptions { EV, COMPACT, NORMAL, MOTORCYCLE, HANDICAPPED, SHORTTERM, EMPLOYEE}
	public enum VehicleOptions {MOTORCYCLE, EV, TRUCK, CAR, SUV}
	public enum TicketStatusOptions {LOST, ACTIVE, PAID}
	public enum AccountStatusOptions {ACTIVE, INACTIVE, FLAGGED}
}
