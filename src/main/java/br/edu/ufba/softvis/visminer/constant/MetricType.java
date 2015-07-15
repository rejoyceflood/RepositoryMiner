package br.edu.ufba.softvis.visminer.constant;

/**
 * @version 0.9
 * Metric type.
 */
public enum MetricType {

	SIMPLE(1), COMPLEX(2);
	
	private int id;
	
	private MetricType(int id){
		this.id = id;
	}
	
	/**
	 * @return the metric type id.
	 */
	public int getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @param id
	 * @return MetricType
	 * Finds the metric type with given id. 
	 */
	public static MetricType parse(int id){
		
		for(MetricType type : MetricType.values()){
			if(type.getId() == id){
				return type;
			}
		}
		
		return null;
		
	}
	
}
