package ru.croc.task16.core.models;

public record Coordinates(double latitude, double longitude) {

	public static double getDistance(Coordinates a, Coordinates b) {
		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(b.latitude - a.latitude);
		double lonDistance = Math.toRadians(b.longitude - a.longitude);
		double d = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
			+ Math.cos(Math.toRadians(a.latitude)) * Math.cos(Math.toRadians(b.latitude))
			* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(d), Math.sqrt(1 - d));
		return R * c * 1000; // convert to meters
	}

	@Override
	public String toString() {
		return String.format("%.4f, %.4f", latitude, longitude);
	}
}
