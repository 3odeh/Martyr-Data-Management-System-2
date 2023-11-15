package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// This class for save the data of Martyr
public class Martyr implements Comparable<Martyr> {

	// Attributes of Martyr
	private String name;
	private byte age;
	private Date dateOfDeath;
	private boolean isMale;

	// Constructor to make objects of Martyr
	public Martyr(String name, byte age, Date dateOfDeath, boolean isMale) {
		super();
		this.name = name;
		this.age = age;
		this.dateOfDeath = dateOfDeath;
		this.isMale = isMale;
	}

	// This method to get martyr name
	public String getName() {
		return name;
	}

	// This method to get martyr age
	public byte getAge() {
		return age;
	}

	// This method to get martyr gender
	public char getGender() {
		return (isMale) ? 'M' : 'F';
	}

	// This method to update martyr gender
	public void setGender(char isMale) {
		this.isMale = isMale == 'M';
	}

	// This method to get martyr date of death
	public Date getDateOfDeath() {
		return dateOfDeath;
	}

	// This method to get if martyr is male or not
	public boolean isMale() {
		return isMale;
	}

	// This method to update martyr name
	public void setName(String name) {
		this.name = name;
	}

	// This method to update martyr age
	public void setAge(byte age) {
		this.age = age;
	}

	// This method to update martyr date of death
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	// This method to set if martyr is male or not
	public void setMale(boolean isMale) {
		this.isMale = isMale;
	}

	// This method to get martyr simple date of death (month/day/year)
	public String getSimpleDateOfDeath() {
		if (dateOfDeath != null)
			return new SimpleDateFormat("MM/dd/yyyy").format(dateOfDeath);
		return "No data";
	}

	// This method to update martyr date of death
	public void setSimpleDateOfDeath(String date) throws ParseException {
		try {
			dateOfDeath = new SimpleDateFormat("MM/dd/yyyy").parse(date);
		} catch (ParseException e) {
			throw e;
		}
	}

	// This method to get all formation of martyr
	public String getInfo(String location) {
		return name + "," + age + "," + location + "," + getSimpleDateOfDeath() + "," + getGender();
	}

	// This method to compare between two martyrs depend on the name 
	@Override
	public int compareTo(Martyr o) {
		return this.name.compareTo(o.getName());
	}

	// This method to compare between two martyrs depend on the date 
	public int compareTo(Date o) {
		if (o != null && getDateOfDeath() != null)
			return  getDateOfDeath().compareTo(o);
		else if (o == null && getDateOfDeath() == null) {
			return  0;
		}else if (o == null) {
			return  1;
		}else {
			return  -1;
		}
		
	}

	// This method to get the all data of martyr as string
	@Override
	public String toString() {
		return name + " , " + age + " , " + getSimpleDateOfDeath() + " , " + getGender();
	}

}
