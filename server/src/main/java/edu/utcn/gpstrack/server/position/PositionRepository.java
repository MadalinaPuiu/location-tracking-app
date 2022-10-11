package edu.utcn.gpstrack.server.position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;


/**
 * @author Radu Miron
 * @version 1
 */
public interface PositionRepository extends JpaRepository<Position, Integer> {

    @Query("SELECT position FROM Position position WHERE position.terminalId LIKE :terminalId " +
            "AND position.creationDate BETWEEN :startDate AND :endDate")
    Collection<Position> getTerminalPositionsBetweenTwoDates(
            @Param("terminalId") String terminalId,
            @Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate
    );
}
