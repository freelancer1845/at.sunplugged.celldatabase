package at.sunplugged.celldatabase.labviewimport.ui.wizard;

public final class LabviewDataFile {

	private final String absolutPath;

	private final String name;

	private Double area;

	private Double powerInput;

	public LabviewDataFile(String absolutPath, String name) {
		super();
		this.absolutPath = absolutPath;
		this.name = name;
	}

	public String getAbsolutPath() {
		return absolutPath;
	}

	public String getName() {
		return name;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getPowerInput() {
		return powerInput;
	}

	public void setPowerInput(Double powerInput) {
		this.powerInput = powerInput;
	}

}
