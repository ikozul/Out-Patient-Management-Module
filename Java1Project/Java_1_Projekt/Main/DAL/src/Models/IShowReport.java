package Models;

import java.sql.Date;
import java.sql.Time;

public interface IShowReport {
    String GetDoctorName();
    String GetSectionName();
    Time GetTimeFilled();
    Date GetDateFilled();
    String GetPatientName();
}
