package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Personnel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {
   @Query("SELECT p\n" +
            "FROM Personnel p\n" +
           "         JOIN p.sector s\n" +
            "WHERE p.sector = 'Intrattenimento'\n" +
            "  AND p.name NOT IN (\n" +
            "    SELECT DISTINCT p.name\n" +
            "    FROM Booking b JOIN b.personnel p\n" +
            "    WHERE b.date = '2024-05-16'\n" +
            "      AND ((b.duration.start < '17:00:00.000000' AND b.duration.end > '14:00:00.000000'))\n" +
            "      AND ((DAYOFWEEK('2024-05-16') NOT IN (1, 7) AND p.weekdayHours.start <= '14:00:00.000000' AND p.weekdayHours.end >= '17:00:00.000000')\n" +
            "        OR (DAYOFWEEK('2024-05-16') IN (1, 7) AND p.weekdayHours.start <= '14:00:00.000000' AND p.weekdayHours.end >= '17:00:00.000000'))\n" +
            ")")
    List<Personnel> getPersonnelDisponibility();
}
