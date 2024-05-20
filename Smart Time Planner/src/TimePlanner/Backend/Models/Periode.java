package TimePlanner.Backend.Models;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Periode implements Serializable {

    private LocalDate startDate;
    private LocalDate endDate;
    private Map<LocalDate, List<Creneau>> creneauxPerDay;

    public Periode(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.creneauxPerDay = new LinkedHashMap<>();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Map<LocalDate, List<Creneau>> getCreneaux() {
        return creneauxPerDay;
    }

    public void setCreneauxPerDay(Map<LocalDate, List<Creneau>> creneauxPerDay) {
        this.creneauxPerDay = creneauxPerDay;
    }
    public void addCreneaux(LocalDate date, List<Creneau> creneaux) {
        creneauxPerDay.put(date, creneaux);
    }
}
