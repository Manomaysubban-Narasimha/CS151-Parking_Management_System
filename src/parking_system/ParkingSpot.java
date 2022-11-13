package parking_system;

public class ParkingSpot {
	private String id;
	private ParkingLotOptions.SpotOptions spotType;
	boolean isEmpty;
	private Vehicle vehicle;

	public ParkingSpot(ParkingLotOptions.SpotOptions spotType) {
		this.spotType = spotType;
	}
	
	public boolean parkVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		return isEmpty = false;
	}

	public boolean removeVehcile() {
		this.vehicle = null;
		return isEmpty = true;
	}
}