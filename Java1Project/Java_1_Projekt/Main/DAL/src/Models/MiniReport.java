package Models;

import DALLibrary.DateTimeConverter;
import Repo.RepoFactory;

import javax.print.Doc;
import java.sql.Date;
import java.sql.Time;

public class MiniReport implements IShowReport {
    private int IDReport;
    private String FullName;
    private String DateOfBirth;
    private String BriefStatement;
    private String ContactPhone1;
    private String ContactPhone2;
    private String NextOfKin;
    private String RelationsToPatient;
    private String DateFiled;
    private int DoctorID;
    private int SectionID;

    public MiniReport(){
    }

    public MiniReport(int IDReport, String fullName, String dateOfBirth, String briefStatement, String contactPhone1, String contactPhone2, String nextOfKin, String relationsToPatient, String dateFiled, int doctorID, int sectionID) {
        this.IDReport = IDReport;
        FullName = fullName;
        DateOfBirth = dateOfBirth;
        BriefStatement = briefStatement;
        ContactPhone1 = contactPhone1;
        ContactPhone2 = contactPhone2;
        NextOfKin = nextOfKin;
        RelationsToPatient = relationsToPatient;
        DateFiled = dateFiled;
        DoctorID = doctorID;
        SectionID = sectionID;
    }

    public int getIDReport() {
        return IDReport;
    }

    public void setIDReport(int IDReport) {
        this.IDReport = IDReport;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getBriefStatement() {
        return BriefStatement;
    }

    public void setBriefStatement(String briefStatement) {
        BriefStatement = briefStatement;
    }

    public String getContactPhone1() {
        return ContactPhone1;
    }

    public void setContactPhone1(String contactPhone1) {
        ContactPhone1 = contactPhone1;
    }

    public String getContactPhone2() {
        return ContactPhone2;
    }

    public void setContactPhone2(String contactPhone2) {
        ContactPhone2 = contactPhone2;
    }

    public String getNextOfKin() {
        return NextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        NextOfKin = nextOfKin;
    }

    public String getRelationsToPatient() {
        return RelationsToPatient;
    }

    public void setRelationsToPatient(String relationsToPatient) {
        RelationsToPatient = relationsToPatient;
    }

    public String getDateFiled() {
        return DateFiled;
    }

    public void setDateFiled(String dateFiled) {
        DateFiled = dateFiled;
    }

    @Override
    public String toString() {
        return String.format(IDReport +" "+ FullName +" "+ DateOfBirth +" "+ BriefStatement +" "+ ContactPhone1 +" "+ ContactPhone2 +" "+ NextOfKin +" "+ RelationsToPatient +" "+ DateFiled +" "+ DoctorID +" "+ SectionID);
    }

    public int getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }

    public int getSectionID() {
        return SectionID;
    }

    public void setSectionID(int sectionID) {
        SectionID = sectionID;
    }

    @Override
    public String GetDoctorName() {
        Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
        return repo.GetDoctorNameByID(this.DoctorID);
    }

    @Override
    public String GetSectionName() {
        Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
        return repo.GetSectionNameByID(this.SectionID);
    }

    @Override
    public Time GetTimeFilled() {
        return DateTimeConverter.GetTimeFromDTString(this.DateFiled);
    }

    @Override
    public Date GetDateFilled() {
        return DateTimeConverter.GetDateFromDTString(this.DateFiled);
    }

    @Override
    public String GetPatientName() {
        return this.FullName;
    }
}
