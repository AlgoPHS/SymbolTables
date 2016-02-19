package simfinder;

/**
   Field header and value data type
   Contains the String field identifier and the field value.
*/
public class FieldValue {
	private String header;
	private String value;
    
	public FieldValue(String header,String value){
		this.header = header;
		this.value = value;
	}

	public FieldValue(String header){
		this.header = header;
		this.value = null;
	}
    
    //Overrides equals method to make the FieldValue objects equal if the header and values are equal.
	public boolean equals(Object o){
	    if (!(o instanceof FieldValue)) {
		return false;
	    }
	    if (!header.equals(((FieldValue)o).getHeader())) {
		return false;
	    }
	    if (!value.equals(((FieldValue)o).getValue())) {
		return false;
	    }
	    return true;
	}
    //Overrides hashcode method
	public int hashCode(){
		return header.hashCode() + value.hashCode();
	}
    //setValue method for the client's input.
	public void setValue(String v){
		value = v;
	}

	public String getHeader(){
		return header;
	}
	public String getValue(){
		return value;
	}

    
}
