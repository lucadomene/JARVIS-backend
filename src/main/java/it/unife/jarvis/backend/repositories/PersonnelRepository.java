package it.unife.jarvis.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import it.unife.jarvis.backend.models.Personnel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonnelRepository extends JpaRepository<Personnel, String> {
   /* @Query("SELECT p\n" +
            "FROM Personnel p\n" +
          //  "         JOIN p.sector s ON p.name = s.name\n" +
            "WHERE p.sector = 'Intrattenimento'\n" +
            "  AND p.name NOT IN (\n" +
            "    SELECT DISTINCT p.name\n" +
            "    FROM Personnel AS p\n" +
            "             JOIN  AS wf ON p.name = wf.personnel_name\n" +
            "             JOIN bookings AS b ON wf.booking_id = b.id\n" +
            "    WHERE b.date = '2024-05-16'\n" +
            "      AND ((b.start_time < '17:00:00.000000' AND b.end_time > '14:00:00.000000'))\n" +
            "      AND ((DAYOFWEEK('2024-05-16') NOT IN (1, 7) AND p.weekday_open <= '14:00:00.000000' AND p.weekday_close >= '17:00:00.000000')\n" +
            "        OR (DAYOFWEEK('2024-05-16') IN (1, 7) AND p.weekend_open <= '14:00:00.000000' AND p.weekend_close >= '17:00:00.000000'))\n" +
            ")")
    List<Personnel> getPersonnelDisponibility(); */
}
