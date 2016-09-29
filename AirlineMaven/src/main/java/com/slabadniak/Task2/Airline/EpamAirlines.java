package com.slabadniak.Task2.Airline;

import com.slabadniak.Task2.Airport.Airoport;
import com.slabadniak.Task2.Airport.ClassOfAirport.classOfAirport;
import com.slabadniak.Task2.Comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.Task2.Exeption.InvalidArgumentExeption;
import com.slabadniak.Task2.Exeption.InvalidInputData;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.Plane.PlaneAtribute.planeAtribute;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AStarShortestPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

   /* public <T extends Number> T totalValue(planeAtribute atribute){
        T result;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            result += iterator.next().getAtribute(atribute);
        }
        return result;
    }*/

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
            try {
                routeSystem =  new SimpleWeightedGraph<Airoport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
                FileReader fr = new FileReader("src\\main\\java\\com\\slabadniak\\Task2\\File\\Airports");
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    String[] values = str.split(" ");
                    if (values.length != 4)
                        throw new InvalidInputData();
                    routeSystem.addVertex(new Airoport(values[0], values[1],Integer.parseInt(values[2]), classOfAirport.getAirport(values[3])));
                }

                airoports = routeSystem.vertexSet();

                fr = new FileReader("src\\main\\java\\com\\slabadniak\\Task2\\File\\Hallways");
                br = new BufferedReader(fr);
                while ((str = br.readLine()) != null) {
                    String[] values = str.split(",");
                    if (values.length != 3)
                        throw new InvalidInputData();
                    addHallway(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2]));
                }
            } catch (InvalidArgumentExeption invalidArgumentExeption) {
                invalidArgumentExeption.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidInputData invalidInputData) {
                invalidInputData.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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
