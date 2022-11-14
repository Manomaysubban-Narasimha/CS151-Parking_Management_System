package parking_system;

public class Vehicle {
	private String licenseNumber;
	private Options.VehicleOptions vehicleType;
	
	public Vehicle(Options.VehicleOptions vehicleType, String licenseNumber) {
		this.licenseNumber = licenseNumber;
		this.vehicleType = vehicleType;
	}

}
