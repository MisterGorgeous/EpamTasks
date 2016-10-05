package com.slabadniak.task2.airline;

import com.slabadniak.task2.airport.Airoport;
import com.slabadniak.task2.airport.airportsclass.AirportsClass;
import com.slabadniak.task2.airport.airportlocation.AirportLocationCity;
import com.slabadniak.task2.aviation.Aviation;
import com.slabadniak.task2.comparator.PlaneRangeOfFlyComparator;
import com.slabadniak.task2.exeption.InvalidArgumentExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.planefactory.airlinerfactory.AirlinerFactory;
import com.slabadniak.task2.planefactory.skytruckfactory.SkyTruckFactory;
import com.slabadniak.task2.planename.PlaneName;
import com.slabadniak.task2.report.Report;
import com.slabadniak.task2.ticketclass.TicketClass;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.AStarShortestPath;
import org.jgrapht.alg.interfaces.AStarAdmissibleHeuristic;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EpamAirlines {
    private static final Logger LOGGER = LogManager.getLogger(EpamAirlines.class);
    private final static String LOG_PATH = "src\\main\\resources\\log4j2";
    private final static String AIRPORT_PATH = "src\\main\\java\\com\\slabadniak\\task2\\file\\Airports";
    private final static String HALL_WAYS = "src\\main\\java\\com\\slabadniak\\task2\\file\\Hallways";
    private AirportsSystem airoports;
    private Aviation aviation;
    private static EpamAirlines airline;

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(LOG_PATH).toURI());
    }

    private EpamAirlines(){
    }

    public static EpamAirlines getAirline(){
        if(airline == null){
            airline = new EpamAirlines();
        }
        return airline;
    }

     {
        aviation = new Aviation();
        aviation.addPlanes();
        airoports = new AirportsSystem();
        airoports.setAirports();
        airoports.setHallWays();
    }

    public AirportsSystem getAiroports() {
        return airoports;
    }

    public Aviation getAviation() {
        return aviation;
    }


    public class AirportsSystem {
        private SimpleWeightedGraph<Airoport,DefaultWeightedEdge> routeSystem;
        private Set<Airoport> airoports;
        private final int FUEL_COST = 3;

        AirportsSystem(){
            routeSystem =  new SimpleWeightedGraph<Airoport, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            airoports = routeSystem.vertexSet();
        }

        public void setAirports(){
            try {

                List<String> atributes = new ArrayList<>();
                Stream<String> stream = Files.lines(Paths.get(AIRPORT_PATH));
                atributes = stream.collect(Collectors.toList());


                FileReader fr = new FileReader(AIRPORT_PATH);
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    String[] values = str.split(" ");
                    if (values.length != 5)
                        throw new InvalidArgumentExeption();
                    routeSystem.addVertex(new Airoport(values[0], AirportLocationCity.getLocation(values[1]), values[2], Integer.parseInt(values[3]), AirportsClass.getAirport(values[4])));
                }

              /*  stream.forEach(atribute -> {
                    if (atribute.split(" ").length != 5)
                        try {
                            throw new InvalidArgumentExeption();
                        } catch (InvalidArgumentExeption invalidInputData) {
                            invalidInputData.printStackTrace();
                        }
                    try {
                        routeSystem.addVertex(new Airoport(atribute.split(" ")[0], AirportLocationCity.getLocation(atribute.split(" ")[1]), atribute.split(" ")[2], Integer.parseInt(atribute.split(" ")[3]), AirportsClass.getAirport(atribute.split(" ")[4])));
                    } catch (InvalidArgumentExeption invalidArgumentExeption) {
                        invalidArgumentExeption.printStackTrace();
                    }
                });*/
            }catch (InvalidArgumentExeption invalidArgumentExeption) {
                    invalidArgumentExeption.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void setHallWays() {
            try{
                Scanner scanner = new Scanner(new File(HALL_WAYS));
                while (scanner.hasNext()){
                    String[] values = scanner.nextLine().split(",");
                    if (values.length != 3)
                        throw new InvalidArgumentExeption();
                    addHallway(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2]));
                }
            } catch (InvalidArgumentExeption invalidArgumentExeption) {
                invalidArgumentExeption.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Report flyFromTo(AirportLocationCity origin, AirportLocationCity destination, TicketClass ticketClass) throws InvalidArgumentExeption{
            GraphPath<Airoport,DefaultWeightedEdge> route = shortestPathBetweenAiroports(findAirportByName(origin),findAirportByName(destination));
            Plane plane = aviation.getSparePlane();
            int flyDistance = (int)route.getWeight();
            plane.takeOff();
            float routeTime =  plane.fly(flyDistance);
            plane.landOn();
            return new Report(findAirportByName(origin).getLocationCity(), findAirportByName(destination).getLocationCity(),plane.getName(),
                                flyDistance,routeTime, calculateFlyCost(flyDistance, ticketClass));
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
