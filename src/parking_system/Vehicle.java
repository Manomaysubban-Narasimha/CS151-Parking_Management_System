package parking_system;

public class Vehicle {
	private String licenseNumber;
	private ParkingLotOptions.VehicleOptions vehicleType;
	
	public Vehicle(ParkingLotOptions.VehicleOptions vehicleType, String licenseNumber) {
		this.licenseNumber = licenseNumber;
		this.vehicleType = vehicleType;
	}

}
