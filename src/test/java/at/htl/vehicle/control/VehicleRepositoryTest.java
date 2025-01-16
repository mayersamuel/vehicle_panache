package at.htl.vehicle.control;

import at.htl.vehicle.entity.Vehicle;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.assertj.db.output.Outputs;
import org.assertj.db.type.AssertDbConnection;
import org.assertj.db.type.AssertDbConnectionFactory;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.db.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class VehicleRepositoryTest {

    @Inject
    VehicleRepository vehicleRepository;

    @Inject
    AgroalDataSource ds;

    @Order(1000)
    @Test
    void givenAVehicleRepository_whenEmptyThenReturnTrue() {
        // arrange

        // act
        boolean actualIsEmpty = vehicleRepository.isEmpty();

        // assert
//        assertThat(actualIsEmpty).isTrue();
   }

   @Transactional
    @Order(1010)
    @Test
    void givenAVehicleRepository_whenAddingVehicle_thenVehicleIsPersistedInRepo() {
        // arrange
        Vehicle kadett = new Vehicle("Opel", "Kadett");

        // act
        vehicleRepository.persist(kadett);

        // assert
//        assertThat(vehicleRepository.isEmpty()).isFalse();
    }
//
//    @Order(1030)
//    @Test
//    void givenVehicleRepository_whenAddingVehicleTwice_thenVehicleIsNotPersistetInRepo() {
//        // arrange
//        vehicleRepository.deleteAll();
//
//        Vehicle blitz = new Vehicle("Opel", "Blitz");
//        Vehicle blitz2 = new Vehicle("Opel", "Blitz");
//
//        // act
//        vehicleRepository.persist(blitz);
//        vehicleRepository.persist(blitz2);
//
//        // assert
//        System.out.println(vehicleRepository.getAll());
//        assertThat(vehicleRepository.isEmpty()).isFalse();
//        assertThat(vehicleRepository.getAll()).hasSize(1);
//
//    }


    @Test
    @Transactional
    void givenEmptyDb_whenInvokingCreateData_thenFiveRecordsArePersisted() {
        vehicleRepository.createData();

        AssertDbConnection connection = AssertDbConnectionFactory.of(ds).create();
        Table vehicleTable = connection.table("V_VEHICLE").build();

        Outputs.output(vehicleTable).toConsole();

        assertThat(vehicleTable).hasNumberOfRows(5);
    }
}