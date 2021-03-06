package org.dev.datafetch.model;

import com.google.gson.annotations.SerializedName;

public class GlobalData{

	@SerializedName("recovered")
	private long recovered;

	@SerializedName("cases")
	private long cases;

	@SerializedName("deaths")
	private long deaths;




	public long getRecovered() {
		return recovered;
	}

	public long getCases() {
		return cases;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setRecovered(long recovered) {
		this.recovered = recovered;
	}

	public void setCases(long cases) {
		this.cases = cases;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}


	@Override
	public String toString() {
		return "GlobalData{" +
				", cases=" + cases +
				", deaths=" + deaths +
				"recovered=" + recovered +
				'}';
	}
}

