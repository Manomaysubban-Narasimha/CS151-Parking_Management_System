package parking_system;

public class ParkingSpot {
	private String id;
	private Options.SpotOptions spotType;
	boolean isEmpty;
	private Vehicle vehicle;

	public ParkingSpot(Options.SpotOptions spotType) {
		this.spotType = spotType;
	}
	
	public boolean parkVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		return isEmpty;
	}

	public boolean removeVehcile() {
		this.vehicle = null;
		return isEmpty;
	}
}