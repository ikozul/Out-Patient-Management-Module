package Repo;

import Models.FullReport;
import Models.MiniReport;

import java.util.List;

public interface IRepo {
    MiniReport GetMiniReportByID(int id);
    List<MiniReport> GetAllMiniReports();
    MiniReport InsertMiniReport(MiniReport minireport);
    MiniReport EditMiniReport(MiniReport minireport);
    boolean DeleteMiniReport(int id);

    FullReport GetFullReportByID(int id);
    List<FullReport> GetAllFullReports();
    FullReport InsertFullReport(FullReport fullReport);
    FullReport EditFullReport(FullReport fullReport);
    boolean DeleteFullReport(int id);

    String GetDoctorNameByID(int id);
    String GetSectionNameByID(int id);
}
