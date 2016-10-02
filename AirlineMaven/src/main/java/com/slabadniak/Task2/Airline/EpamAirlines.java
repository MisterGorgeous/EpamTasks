package com.slabadniak.Task2.Airline;

import com.slabadniak.Task2.Airport.Airoport;
import com.slabadniak.Task2.Airport.AirportsClass.AirportsClass;
import com.slabadniak.Task2.Airport.LocationOfAirportsCity.AirportLocationCity;
import com.slabadniak.Task2.Comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.Task2.Exeption.InvalidArgumentExeption;
import com.slabadniak.Task2.Exeption.InvalidInputData;
import com.slabadniak.Task2.Plane.Plane;
import com.slabadniak.Task2.PlaneFactory.AirlinerFactory.Boeing747Factory;
import com.slabadniak.Task2.PlaneFactory.AirlinerFactory.Ty154Factory;
import com.slabadniak.Task2.PlaneFactory.SkyTruckFactory.AH124Factory;
import com.slabadniak.Task2.PlaneFactory.SkyTruckFactory.C130Factory;
import com.slabadniak.Task2.TicketClass.TicketClass;
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
    private AirportsSystem airoports;
    private Aviation aviation;

    public EpamAirlines(){
        airoports = new AirportsSystem();
        aviation = new Aviation();
    }

    public AirportsSystem getAiroports() {
        return airoports;
    }

    public Aviation getAviation() {
        return aviation;
    }

    public class Aviation {
        private ArrayList<Plane> planes;

        Aviation(){
            planes = new ArrayList<Plane>();
            planes.add(new Boeing747Factory().createPlane());
            planes.add(new Ty154Factory().createPlane());
            planes.add(new AH124Factory().createPlane());
            planes.add( new C130Factory().createPlane());
        }

        public void addPlane(Plane plane){
            planes.add(plane);
        }

        public ArrayList<Plane> getPlanes() {
            return planes;
        }

        public Plane getSparePlane() throws InvalidArgumentExeption{
            Iterator<Plane> iterator = planes.iterator();
            while (iterator.hasNext()) {
                Plane plane = iterator.next();
                if(!plane.isPlaneFlying())
                    return plane;
            }
            throw new InvalidArgumentExeption("There is no available planes.All planes are flying.");
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

 /*   public <T extends Number> T totalValue(planeAtribute atribute){
        T result;
        Iterator<Plane> iterator = planes.iterator();
        while (iterator.hasNext()) {
            result += iterator.next().getAtribute(atribute).;
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
    }



    public class AirportsSystem  {
        private SimpleWeightedGraph<Airoport,DefaultWeightedEdge> routeSystem;
        private Set<Airoport> airoports;
        private final int FUEL_COST = 3;

        AirportsSystem(){
            try {
                routeSystem =  new SimpleWeightedGraph<Airoport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
                FileReader fr = new FileReader("src\\main\\java\\com\\slabadniak\\Task2\\File\\Airports");
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    String[] values = str.split(" ");
                    if (values.length != 5)
                        throw new InvalidInputData();
                    routeSystem.addVertex(new Airoport(values[0], AirportLocationCity.getLocation(values[1]),values[2],Integer.parseInt(values[3]), AirportsClass.getAirport(values[4])));
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

        public String flyFromTo(AirportLocationCity origin, AirportLocationCity destination, TicketClass ticketClass)throws InvalidArgumentExeption{
            GraphPath<Airoport,DefaultWeightedEdge> route = shortestPathBetweenAiroports(findAirportByName(origin),findAirportByName(destination));
            Plane plane = aviation.getSparePlane();
            int flyDistance = (int)route.getWeight();
            float routeTime =  plane.fly(flyDistance);
            String flyInfo = "Your have flied from" + findAirportByName(origin) + "\nto " + findAirportByName(destination) + ".\n"+
                            "On the palne " + plane.getName() + ".The distance was " + flyDistance + ".\n Flying time was " + routeTime
                            +" hours.\nThe flight cost " + calculateFlyCost(flyDistance, ticketClass);
            return flyInfo;
        }

        private float calculateFlyCost(int flyDistance, TicketClass ticketClass){
            return flyDistance / FUEL_COST * TicketClass.getClassCoefitient(ticketClass);
        }

        private Airoport findAirportByName(AirportLocationCity locationCity) throws InvalidArgumentExeption {
            Iterator<Airoport> iterator = airoports.iterator();
            while(iterator.hasNext()){
                Airoport airoport = iterator.next();
                if(airoport.getLocationCity().equals(locationCity)) {
                    return airoport;
                }
            }
            throw new InvalidArgumentExeption("There is no airoport with such name");
        }

        private Airoport findAirportById(int id) throws InvalidArgumentExeption {
            Iterator<Airoport> iterator = airoports.iterator();
            while(iterator.hasNext()){
                Airoport airoport = iterator.next();
                if(airoport.getId() == id) {
                    return airoport;
                }
            }
            throw new InvalidArgumentExeption("There is no airoport with such id");
        }

        private void addHallway(int firstAirId, int seccondAirId,int edgeWeight) throws InvalidArgumentExeption {
            if(edgeWeight < 0){
                throw new InvalidArgumentExeption("Hallway can't be negative.");
            }
            DefaultWeightedEdge edge = routeSystem.addEdge(findAirportById(firstAirId), findAirportById(seccondAirId));
            routeSystem.setEdgeWeight(edge, edgeWeight);
        }

        private GraphPath<Airoport,DefaultWeightedEdge> shortestPathBetweenAiroports(Airoport firstAirport, Airoport seccondAirport) throws InvalidArgumentExeption {
            AStarShortestPath<Airoport,DefaultWeightedEdge> algoritm = new AStarShortestPath<Airoport, DefaultWeightedEdge>(routeSystem);
            return algoritm.getShortestPath(firstAirport,seccondAirport, new AStarAdmissibleHeuristic<Airoport>() {
                public double getCostEstimate(Airoport airoport, Airoport v1) {
                    return 0;
                }
            });
        }
    }

}
