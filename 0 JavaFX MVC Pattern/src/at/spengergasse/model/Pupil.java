package at.spengergasse.model;
/**
 * 
 */


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Leo Fanzott
 *
 */
public class Pupil {
	
	private String lastname;
	private String firstname;
	private Integer yearOfBirth;

	/**
	 * 
	 */
	public Pupil() {
		// TODO Auto-generated constructor stub
		this("default_lastname","default_firstname",2001);
	}

	/**
	 * @param lastname
	 * @param firstname
	 * @param yearOfBirth
	 */
	public Pupil(String lastname, String firstname, Integer yearOfBirth) {
		this.setLastname(lastname);
		this.setFirstname(firstname);
		this.setYearOfBirth(yearOfBirth);
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the yearOfBirth
	 */
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}

	/**
	 * @param yearOfBirth the yearOfBirth to set
	 */
	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public void save(boolean text) throws IOException{
		// binary
		if (!text){
			DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(new FileOutputStream("P"+this.hashCode()+".dat")));
			dos.writeUTF(lastname);dos.writeUTF(firstname);dos.writeInt(yearOfBirth);
			dos.close();
		}
		// text file extension txt
		else{
			BufferedWriter bw=new BufferedWriter(new FileWriter("P"+this.hashCode()+".txt"));
			bw.write(lastname+";"+firstname+";"+yearOfBirth);
			bw.close();
		}
	}

	/**
	 * 
	 * @param filename
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void read(String filename) throws NumberFormatException, IOException{
		// read text file
		if (filename.contains("txt")){
			BufferedReader br=new BufferedReader(new FileReader(filename));
			String pupilAsString=br.readLine();
			String[] pupilParts=pupilAsString.split(";");
			lastname=pupilParts[0];
			firstname=pupilParts[1];
			yearOfBirth=Integer.parseInt(pupilParts[2]);
		}
		// read binary file
		else{
			DataInputStream dis=new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
			lastname=dis.readUTF();firstname=dis.readUTF();yearOfBirth=dis.readInt();
			dis.close();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pupil [lastname=" + lastname + ", firstname=" + firstname + ", yearOfBirth=" + yearOfBirth + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((yearOfBirth == null) ? 0 : yearOfBirth.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pupil other = (Pupil) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (yearOfBirth == null) {
			if (other.yearOfBirth != null)
				return false;
		} else if (!yearOfBirth.equals(other.yearOfBirth))
			return false;
		return true;
	}

	
}
