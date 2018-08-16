import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.FileReader;
import java.io.IOException;

public class Kruskals 
{
	
	public static String line(String str,int n) {
		String intial = "";
		for(int i=0;i<=n;i++) {
			intial +=str;
		}
		return intial;
	}
	
	public static class Edge implements Comparable<Edge>
	{
		
		String city1 ;
		String city2 ;
		int distance ;
		
		Edge(int distance , String city1 , String city2)
		{
			this.distance = distance ;
			this.city1 = city1 ;
			this.city2 = city2 ;
		}
	
		public int compareTo(Edge edge)
		{
			
		  if (edge.distance < this.distance)
				return 1 ;
		  else if (this.distance < edge.distance)
				return -1 ;
			
			else 
				return 0 ;
		}
	}

   // Kruskals' algorithm
	public void kruskal ()
	{
		
		int totalDis = 0;
		ArrayList<Edge> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
    	BufferedReader file = null;
    	String str2 = "";
    	
    	
    try
        {
            
          file = new BufferedReader(new FileReader("assn9_data2.csv")); 
            
          while ((str2 = file.readLine()) != null)
            {
                
             String[] CityName = str2.split(",");
             list2.add(CityName[0]);
             list1.add(new Edge(Integer.parseInt(CityName[2]),CityName[0],CityName[1] ));
             for (int i = 3 ; i < CityName.length ; i++)
               {
               list1.add(new Edge(Integer.parseInt(CityName[i+1]),CityName[0],CityName[i]));
               i++ ;}
                }
        } 
    
        catch (Exception e) 
    	{
            e.printStackTrace();
        }
         
         
        finally
        {
            try 
            {
            	file.close();
                
            } 
            catch (IOException e)
            {
                e.printStackTrace();
                //System.out.println("IO exception");
            }
        }        

         
	
		DisjSets set = new DisjSets(list2.size());
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		
		System.out.println("City1" + "   City2" + "  Distance") ;
		System.out.println(line("*",30));
		
		for (Edge edge2 : list1)
		{
			queue.add(edge2);							
		}
		
		int accept = 0 ;
		int size = list2.size()-1;
	
		while (accept < size )
		{
			Edge edge3 = queue.remove();	//deletemin function 		
			 
			//System.out.println("error");
			String a1 = new String(edge3.city1);
			String a2 = new String(edge3.city2);
			int find1 =set.find(list2.indexOf(a1));
			int find2 =set.find(list2.indexOf(a2));
			boolean compare = find1 != find2;
			if (compare)
			{
				accept ++ ;
				set.union(find1,find2);
				int finDistance = edge3.distance;
				totalDis = totalDis + finDistance ;
				System.out.println(edge3.city1+"->"+edge3.city2+": " + edge3.distance);
			}
		
		}
		System.out.println(line("*",40));
		System.out.println("Total Distance for all cities: "+totalDis);
		

	}
    public static void main( String [ ] args )
    {
    	
    	    	Kruskals kruskals = new Kruskals();
    	    	kruskals.kruskal();   	

    }

}