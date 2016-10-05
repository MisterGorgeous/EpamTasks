package com.slabadniak.task2.airline;

import com.slabadniak.task2.airport.Airoport;
import com.slabadniak.task2.airport.AirportsClass;
import com.slabadniak.task2.airport.AirportLocationCity;
import com.slabadniak.task2.aviation.Aviation;
import com.slabadniak.task2.exeption.UncorrectDataExeption;
import com.slabadniak.task2.plane.Plane;
import com.slabadniak.task2.report.Report;
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
import java.util.*;

public class EpamAirlines {
    private static final Logger LOGGER = LogManager.getLogger(EpamAirlines.class);
    private static final String LOG_PATH = "src\\main\\resources\\log4j2";
    private static final String AIRPORT_PATH = "src\\main\\resources\\Airports";
    private static final String HALL_WAYS = "src\\main\\resources\\Hallways";
    private static EpamAirlines airline;
    private AirportsSystem airoports;
    private Aviation aviation;

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(LOG_PATH).toURI());
    }

    {
        aviation = new Aviation();
        aviation.addPlanes();
        airoports = new AirportsSystem();
        airoports.setAirports();
        airoports.setHallWays();
    }

    private EpamAirlines(){}

    public static EpamAirlines getAirline() {
        if(airline == null){
            airline = new EpamAirlines();
        }
        return airline;
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
                FileReader fr = new FileReader(AIRPORT_PATH);
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    String[] values = str.split(" ");
                    if (values.length != 5)
                        throw new UncorrectDataExeption();
                    routeSystem.addVertex(new Airoport(values[0], AirportLocationCity.getLocation(values[1]), values[2], Integer.parseInt(values[3]), AirportsClass.getAirport(values[4])));
                }

            }catch (UncorrectDataExeption e) {
                LOGGER.log(Level.ERROR, "Invalid airport", e);
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.ERROR, e);
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }

        public void setHallWays() {
            try{
                Scanner scanner = new Scanner(new File(HALL_WAYS));
                while (scanner.hasNext()){
                    String[] values = scanner.nextLine().split(",");
                    if (values.length != 3)
                        throw new UncorrectDataExeption();
                    addHallway(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2]));
                }
            } catch (UncorrectDataExeption e) {
                LOGGER.log(Level.ERROR, "Invalid route", e);
            } catch (FileNotFoundException e) {
                LOGGER.log(Level.ERROR, e);
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }

        public Report flyFromTo(AirportLocationCity origin, AirportLocationCity destination, TicketClass ticketClass) {
            try {
                GraphPath<Airoport,DefaultWeightedEdge> route  = shortestPathBetweenAiroports(findAirportByName(origin),findAirportByName(destination));
                Plane plane = aviation.getSparePlane();
                int flyDistance = (int)route.getWeight();
                plane.takeOff();
                float routeTime =  plane.fly(flyDistance);
                plane.landOn();
                return new Report(findAirportByName(origin).getLocationCity(), findAirportByName(destination).getLocationCity(),plane.getName(),
                        flyDistance,routeTime, calculateFlyCost(flyDistance, ticketClass));
            } catch (UncorrectDataExeption e) {
                LOGGER.log(Level.ERROR, "There is no spare plane.", e);
            }
           return new Report();
        }

        private float calculateFlyCost(int flyDistance, TicketClass ticketClass) {
            return flyDistance / FUEL_COST * TicketClass.getClassCoefitient(ticketClass);
        }

        private Airoport findAirportByName(AirportLocationCity locationCity) {
            Airoport airoport = new Airoport();
            Iterator<Airoport> iterator = airoports.iterator();
            while(iterator.hasNext()){
                airoport = iterator.next();
                if(airoport.getLocationCity().equals(locationCity)) {
                    return airoport;
                }
            }
            return airoport;
        }

        private Airoport findAirportById(int id) throws UncorrectDataExeption {
            Iterator<Airoport> iterator = airoports.iterator();
            while(iterator.hasNext()) {
                Airoport airoport = iterator.next();
                if(airoport.getId() == id) {
                    return airoport;
                }
            }
            throw new UncorrectDataExeption("There is no airoport with such id");
        }

        private void addHallway(int firstAirId, int seccondAirId,int edgeWeight) throws UncorrectDataExeption {
            if(edgeWeight < 0) {
                throw new UncorrectDataExeption("Hallway can't be negative.");
            }
            DefaultWeightedEdge edge = routeSystem.addEdge(findAirportById(firstAirId), findAirportById(seccondAirId));
            routeSystem.setEdgeWeight(edge, edgeWeight);
        }

        private GraphPath<Airoport,DefaultWeightedEdge> shortestPathBetweenAiroports(Airoport firstAirport, Airoport seccondAirport) throws UncorrectDataExeption {
            AStarShortestPath<Airoport,DefaultWeightedEdge> algoritm = new AStarShortestPath<Airoport, DefaultWeightedEdge>(routeSystem);
            return algoritm.getShortestPath(firstAirport,seccondAirport, new AStarAdmissibleHeuristic<Airoport>() {
                public double getCostEstimate(Airoport airoport, Airoport v1) {
                    return 0;
                }
            });
        }
    }
}