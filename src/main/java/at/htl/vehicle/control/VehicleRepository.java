package at.htl.vehicle.control;

import at.htl.vehicle.entity.Vehicle;
import io.quarkus.logging.Log;
import io.quarkus.runtime.LaunchConfig;
import io.quarkus.runtime.LaunchMode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class VehicleRepository {

    @Inject
    EntityManager em;

//    private List<Vehicle> vehicles = new LinkedList<>();
//
//    public List<Vehicle> getAll() {
//        return Collections.unmodifiableList(vehicles);
//    }
//
//    public void deleteAll() {
//        vehicles.clear();
//    }

    public List<Vehicle> getAll() {
        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findAll", Vehicle.class);

        return query.getResultList();
    }

    public boolean isEmpty() {
        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findAll", Vehicle.class);

        List<Vehicle> vehicles = query.getResultList();

        return vehicles.isEmpty();
    }

    public void persist(Vehicle vehicle) {
        em.persist(vehicle);
    }

    @Transactional
    void createData() {
        Log.info("Create example data");
        persist(new Vehicle("Opel", "Kadett"));
        persist(new Vehicle("Opel", "Kapitän"));
        persist(new Vehicle("VW", "Käfer"));
        persist(new Vehicle("Ford", "Explorer"));
    }
}
