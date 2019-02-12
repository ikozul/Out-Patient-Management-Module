package Models;

public class ReportFilter {
    private Integer IDFilter;
    private String PatientNameFilter;

    public ReportFilter(Integer iDFilter, String patientNameFilter){
        IDFilter=iDFilter;
        PatientNameFilter = patientNameFilter;
    }

    public Integer getIDFilter() {
        return IDFilter;
    }

    public void setIDFilter(Integer IDFilter) {
        this.IDFilter = IDFilter;
    }

    public String getPatientNameFilter() {
        return PatientNameFilter;
    }

    public void setPatientNameFilter(String patientNameFilter) {
        PatientNameFilter = patientNameFilter;
    }
}
