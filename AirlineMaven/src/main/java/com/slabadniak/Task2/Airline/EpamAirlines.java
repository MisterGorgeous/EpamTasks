package com.slabadniak.Task2.Airline;

import com.slabadniak.Task2.Airport.Airoport;
import com.slabadniak.Task2.Airport.ClassOfAirport.classOfAirport;
import com.slabadniak.Task2.Comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.Task2.Exeption.InvalidArgumentExeption;
import com.slabadniak.Task2.Plane.Plane;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AStarShortestPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class EpamAirlines {
    private ArrayList<Plane> planes;
    private Route route;

    public EpamAirlines(){
        planes = new ArrayList<Plane>();
        route = new Route();
    }

    public void addPlane(Plane plane){
        planes.add(plane);
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public int totalCapacity() {
        int totalCap = 0;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            totalCap += iterator.next().getCapacity();
        }
        return totalCap;
    }

    public float totalTonnage() {
        float totalTon = 0;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            totalTon += iterator.next().getTonnage();
        }
        return totalTon;
    }

    public void sortByRangeOfFlying(){
        Collections.sort(planes, new PlaneRangeOfFlyComparator());
    }

    public Plane fuelConsumptionLimit(int lowValue, int highValue){
        if(lowValue >= highValue){
            //throw InvalidArgumentExeption("low >= high');
        }
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();
            if(plane.getConsumtionOfFuel() > lowValue && plane.getConsumtionOfFuel() < highValue)
                return plane;
        }
        return null;//TO Do
    }

    class Route  {
        private SimpleWeightedGraph<Airoport,DefaultWeightedEdge> routeSystem;
        private Set<Airoport> airoports;

        Route(){
            routeSystem =  new SimpleWeightedGraph<Airoport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            routeSystem.addVertex(new Airoport("Heathrow Airport", "London.United Kingdom",5, classOfAirport.I));
            routeSystem.addVertex(new Airoport("Charles de Gaulle Airport", "Paris.France",4, classOfAirport.II));
            routeSystem.addVertex(new Airoport("Amsterdam Airport Schiphol", "Amsterdam.Netherlands",4, classOfAirport.II));
            routeSystem.addVertex(new Airoport("Istanbul Atatürk Airport", "Istanbul.Turkey",5, classOfAirport.II));
            routeSystem.addVertex(new Airoport("Berlin Tegel Airport", "Berlin.Germany",3, classOfAirport.III));
            routeSystem.addVertex(new Airoport("Sheremetyevo International Airport", "Moscow.Russia",4, classOfAirport.III));
            routeSystem.addVertex(new Airoport("Helsinki Airport", "Helsinki.Finland",3, classOfAirport.IV));
            routeSystem.addVertex(new Airoport("Frederic Chopin Airport", "Warsaw.Poland",2, classOfAirport.IV));
            routeSystem.addVertex(new Airoport("Riga International Airport", "Riga.Latvia",1, classOfAirport.V));
            routeSystem.addVertex(new Airoport("Boryspil International Airport", "Kyiv.Ukraine",1, classOfAirport.V));
            routeSystem.addVertex(new Airoport("Minsk National Airport", "Minsk.Belarus",1, classOfAirport.V));

            airoports = routeSystem.vertexSet();
            try {
                addHallway(1,2,285);
                addHallway(1,3,330);
                addHallway(2,5,1054);
                addHallway(5,3,654);
                addHallway(5,8,571);
                addHallway(8,9,663);
                addHallway(8,11,553);
                addHallway(8,10,781);
                addHallway(9,7,395);
                addHallway(9,11,483);
                addHallway(6,11,717);
                addHallway(10,11,556);
                addHallway(10,6,853);
                addHallway(10,4,1821);
               // System.out.println("Ok");

            } catch (InvalidArgumentExeption invalidArgumentExeption) {
                invalidArgumentExeption.printStackTrace();
            }
            //  routeSystem.addEdge()
        }

        public Airoport findAirport(int id) throws InvalidArgumentExeption {
            Iterator<Airoport> iterator = airoports.iterator();
            while(iterator.hasNext()){
                Airoport airoport = iterator.next();
                if(airoport.getId() == id) {
                    return airoport;
                }
            }
            throw new InvalidArgumentExeption("There is no airoport with such id");
        }

        public void addHallway(int firstAirId, int seccondAirId,int edgeWeight) throws InvalidArgumentExeption {
            if(edgeWeight < 0){
                throw new InvalidArgumentExeption("Hallway can't be negative.");
            }
            DefaultWeightedEdge edge = routeSystem.addEdge(findAirport(firstAirId),findAirport(seccondAirId));
            routeSystem.setEdgeWeight(edge, edgeWeight);
        }

        public GraphPath<Airoport,DefaultWeightedEdge> shortestPathBetweenAir(int firstAirId, int seccondAirId) throws InvalidArgumentExeption {
            AStarShortestPath<Airoport,DefaultWeightedEdge> algoritm = new AStarShortestPath<Airoport, DefaultWeightedEdge>(routeSystem);
            return algoritm.getShortestPath(findAirport(firstAirId), findAirport(seccondAirId), new AStarAdmissibleHeuristic<Airoport>() {
                public double getCostEstimate(Airoport airoport, Airoport v1) {
                    return 0;
                }
            });
        }
    }

}
