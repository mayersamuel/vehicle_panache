package at.htl.vehicle.control;

import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;


public class InitBean {

    @Inject
    VehicleRepository vehicleRepository;

    void init(@Observes StartupEvent event) {
        if (LaunchMode.current().equals(LaunchMode.DEVELOPMENT)) {
            vehicleRepository.createData();
        }
    }

}
