package simfinder;

import java.util.*;

public class Calculator {
    
    private Map<FieldValue, Set<String>> data;
    private String[] fields;
    
    /** Takes a mapping of object names/ids to mapping of
	Should fill out the fields and data variables. */
    public Calculator(ArrayList<InputObject> input) {
	data = new HashMap<FieldValue, Set<String>>();
	Set<String> fieldset = new HashSet<String>(input.get(0).getFields().length);
	for(InputObject i : input) {
	    for (FieldValue f : i.getFields()) {
		if (!data.containsKey(f)) {
		    data.put(f, new HashSet<String>(input.size() / 2));
		}
		data.get(f).add(i.getId());
		fieldset.add(f.getHeader());
	    }
	}
	fields = new String[fieldset.size()];
	fieldset.toArray(fields);
    }

    /** Awards points to schools that appear in the data for the given
	FieldValues */

    
    public Map<String, Float> calculate(FieldValue[] target) {
    	Map<String, Float> points = new HashMap<String, Float>();
        for(int i =0; i<target.length;i++){
            Set<String> matchedIds = data.get(target[i]);
            for(String currentId: matchedIds){
                if(points.containsKey(currentId)){
                        points.put(currentId,points.get(currentId)+1.0f); //1 is random number of points that is added if it matches, not sure what 
                    }else{                                               //we want to do
                        points.put(currentId,1.0f);
                    }
            }
        }
    	return points;
    }

    /** Return an array of blank FieldValues, copies from fields. */
    public FieldValue[] getBlankFields() {
	FieldValue[] a = new FieldValue[fields.length];
	for ( int i = 0; i < a.length; i++ ) {
	    a[i] = new FieldValue(fields[i]);
	}
	return a;
    }
}
