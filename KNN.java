
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class KNN{
	public static void main(String[] args){
				
		
		Scanner sn = new Scanner(System.in);
		
		ArrayList<KNN.DataEntry> data = new ArrayList<KNN.DataEntry>();
		//data.add(new DataEntry(new double[]{1,8.1,89} "safe"));
		data.add(new DataEntry(new double[]{1,8.1,89}, "safe"));
		data.add(new DataEntry(new double[]{1,8.5,122}, "safe"));
		data.add(new DataEntry(new double[]{1,9.0,195}, "safe"));
		data.add(new DataEntry(new double[]{1,9.2,207}, "safe"));
		data.add(new DataEntry(new double[]{1.3,9.5,236}, "safe"));
		data.add(new DataEntry(new double[]{1.4,8.8,336}, "safe"));
		data.add(new DataEntry(new double[]{2.1,7.9,380}, "safe"));
		data.add(new DataEntry(new double[]{1.8,8.3,408}, "safe"));
		data.add(new DataEntry(new double[]{20.2,0.8,899}, "unsafe"));
		data.add(new DataEntry(new double[]{13.1,0.9,714}, "unsafe"));
		data.add(new DataEntry(new double[]{14.5,4.1,1162}, "unsafe"));
		data.add(new DataEntry(new double[]{8.7,6.9,1211}, "unsafe"));
		data.add(new DataEntry(new double[]{8.9,6.4,1132}, "unsafe"));
		data.add(new DataEntry(new double[]{9.6,9.3,1252}, "unsafe"));
		data.add(new DataEntry(new double[]{17.7,5.0,1174}, "unsafe"));
		data.add(new DataEntry(new double[]{9.7,9.5,1044}, "unsafe"));
		data.add(new DataEntry(new double[]{8.7,10.5,640}, "unsafe"));
		data.add(new DataEntry(new double[]{4.0,9.4,470}, "safe"));
		data.add(new DataEntry(new double[]{1,4,800}, "safe"));
		data.add(new DataEntry(new double[]{1,5,900}, "safe"));
		data.add(new DataEntry(new double[]{1,6,1000}, "safe"));
		data.add(new DataEntry(new double[]{1,7,1100}, "safe"));
		data.add(new DataEntry(new double[]{1,8,1200}, "safe"));
		data.add(new DataEntry(new double[]{1,9,1300}, "safe"));
		data.add(new DataEntry(new double[]{1,10,1400}, "safe"));
		data.add(new DataEntry(new double[]{1,11,1500}, "safe"));
		data.add(new DataEntry(new double[]{1,12,1600}, "safe"));
		data.add(new DataEntry(new double[]{1,13,1700}, "safe"));
		data.add(new DataEntry(new double[]{1,14,1800}, "safe"));
		data.add(new DataEntry(new double[]{1,1,100}, "unsafe"));
		data.add(new DataEntry(new double[]{1,2,200}, "unsafe"));
		data.add(new DataEntry(new double[]{1,3,300}, "unsafe"));
		data.add(new DataEntry(new double[]{1,4,400}, "unsafe"));
		data.add(new DataEntry(new double[]{1,5,500}, "unsafe"));
		data.add(new DataEntry(new double[]{1,6,600}, "unsafe"));
		data.add(new DataEntry(new double[]{1,7,700}, "unsafe"));
		data.add(new DataEntry(new double[]{1,8,800}, "unsafe"));
		data.add(new DataEntry(new double[]{1,9,900}, "unsafe"));
		data.add(new DataEntry(new double[]{1,10,1000}, "unsafe"));
		data.add(new DataEntry(new double[]{1,11,1100}, "unsafe"));
		
		KNN nn = new KNN(data, 3); //3 neighbours
		
		//System.out.println("Enter the tuple");
		//double [] temp = 
		
		BufferedReader br = null;

		try {

			String sCurrentLine;
			//double temp = (Double.parsesCurrentLine;
			

			br = new BufferedReader(new FileReader("/home/deepak/deepak/tcsdisq/file2"));
			int correct = 0;
			int incorrect = 0;
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				//double temp = Double.parseDouble(sCurrentLine);
				
				 String[] StringOftuple = sCurrentLine.split(",");
			        double[] tuple = new double[StringOftuple.length];

/*
			         for(double i = 0; i < StringOftuple.length; i++)
			         {
			             tuple[(int) i] = StringOftuple[i];
			         }*/
				
			         for(int i = 0; i < StringOftuple.length; i++)
			         {
			        	 tuple[i] = Double.parseDouble(StringOftuple[i]);
			         }
			         
			//	nn.classify(tuple[]);
				
				System.out.println("Classified as: "+nn.classify(new DataEntry(tuple,"Ignore")));
			         if(nn.classify(new DataEntry(tuple,"Ignore")) == "safe"){
			        	 correct++;
			        	 
			         }
			         if(nn.classify(new DataEntry(tuple,"Ignore")) == "unsafe"){
			        	 incorrect++;
			        	 
			         }
			         System.out.println(correct);
			         System.out.println(incorrect);
			         
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
		
		
		
		//System.out.println("Classified as: "+nn.classify(new DataEntry(new double[]{20,10,1000},"Ignore")));
	}
	
	
	
	
	
	
	private int k;
	private ArrayList<Object> classes;
	private ArrayList<DataEntry> dataSet;
	
	public KNN(ArrayList<DataEntry> dataSet, int k){
		this.classes = new ArrayList<Object>();
		this.k = k;
		this.dataSet = dataSet;
		
		//Load different classes
		for(DataEntry entry : dataSet){
			if(!classes.contains(entry.getY())) classes.add(entry.getY());
		}
	}
	
	private DataEntry[] getNearestNeighbourType(DataEntry x){
		DataEntry[] retur = new DataEntry[this.k];
		double fjernest = Double.MIN_VALUE;
		int index = 0;
		for(DataEntry tse : this.dataSet){
			double distance = distance(x,tse);
			if(retur[retur.length-1] == null){ //Hvis ikke fyldt
				int j = 0;
				while(j < retur.length){
					if(retur[j] == null){
						retur[j] = tse; break;
					}
					j++;
				}
				if(distance > fjernest){
					index = j;
					fjernest = distance;
				}
			}
			else{
				if(distance < fjernest){
					retur[index] = tse;
					double f = 0.0;
					int ind = 0;
					for(int j = 0; j < retur.length; j++){
						double dt = distance(retur[j],x);
						if(dt > f){
							f = dt;
							ind = j;
						}
					}
					fjernest = f;
					index = ind;
				}
			}
		}
		return retur;
	}
	
	private static double convertDistance(double d){
		return 1.0/d;
	}

	
	public static double distance(DataEntry a, DataEntry b){
		double distance = 0.0;
		int length = a.getX().length;
		for(int i = 0; i < length; i++){
			double t = a.getX()[i]-b.getX()[i];
			distance = distance+t*t;
		}
		return Math.sqrt(distance);
	}
	
	public Object classify(DataEntry e){
		HashMap<Object,Double> classcount = new HashMap<Object,Double>();
		DataEntry[] de = this.getNearestNeighbourType(e);
		for(int i = 0; i < de.length; i++){
			double distance = KNN.convertDistance(KNN.distance(de[i], e));
			if(!classcount.containsKey(de[i].getY())){
				classcount.put(de[i].getY(), distance);
			}
			else{
				classcount.put(de[i].getY(), classcount.get(de[i].getY())+distance);
			}
		}
		//Find right choice
		Object o = null;
		double max = 0;
		for(Object ob : classcount.keySet()){
			if(classcount.get(ob) > max){
				max = classcount.get(ob);
				o = ob;
			}
		}
		
		return o;
	}

public static class DataEntry{
	private double[] x;
	private Object y;
	
	public DataEntry(double[] x, Object y){
		this.x = x;
		this.y = y;
	}
	
		public double[] getX(){
			return this.x;
		}
	
		public Object getY(){
			return this.y;
		}
	}
}
