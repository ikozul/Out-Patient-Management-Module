package Repo;

import Models.FullReport;
import Models.MiniReport;
import SQL.DataBaseSingleton;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    DataSource dataSource = DataBaseSingleton.getInstance();

    @Override
    public String GetSectionNameByID(int id) {
        final String GET_SECTIONNAME = "{ CALL dbo.usp_SectionSelect (?) }";

        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_SECTIONNAME)){
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("SectionName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String GetDoctorNameByID(int id) {
        final String GET_DOCTORNAME = "{ CALL dbo.usp_TestDoctorSelect (?) }";

        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_DOCTORNAME)){
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("DoctorName");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public MiniReport GetMiniReportByID(int id) {
        final String GET_MINIREPORT = "{ CALL dbo.usp_MiniRegistrationFormsSelect (?) }";

        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_MINIREPORT)){
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new MiniReport(
                            //[IDReport], [FullName], [DateOfBirth], [BriefStatement], [ContactPhone1], [ContactPhone2], [NextOfKin], [RelationsToPatient], [DateFiled]
                            resultSet.getInt("IDReport"),
                            resultSet.getString("FullName"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("BriefStatement"),
                            resultSet.getString("ContactPhone1"),
                            resultSet.getString("ContactPhone2"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MiniReport> GetAllMiniReports() {

        final String GET_MINIREPORTS = "{ CALL dbo.usp_MiniRegistrationFormsSelectAll }";
        List<MiniReport> minireports = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_MINIREPORTS)){
            try(ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    minireports.add(new MiniReport(
                            //[IDReport], [FullName], [DateOfBirth], [BriefStatement], [ContactPhone1], [ContactPhone2], [NextOfKin], [RelationsToPatient], [DateFiled]
                            resultSet.getInt("IDReport"),
                            resultSet.getString("FullName"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("BriefStatement"),
                            resultSet.getString("ContactPhone1"),
                            resultSet.getString("ContactPhone2"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    ));
                }
                return minireports;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minireports;
    }

    @Override
    public MiniReport InsertMiniReport(MiniReport minireport) {
        final String INSERT_MINIREPORT = "{ CALL usp_MiniRegistrationFormsInsert (?,?,?,?,?,?,?,?,?,?) }";
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(INSERT_MINIREPORT)) {
            stmt.setString(1, minireport.getFullName());
            stmt.setString(2, minireport.getDateOfBirth());
            stmt.setString(3, minireport.getBriefStatement());
            stmt.setString(4, minireport.getContactPhone1());
            stmt.setString(5, minireport.getContactPhone2());
            stmt.setString(6, minireport.getNextOfKin());
            stmt.setString(7, minireport.getRelationsToPatient());
            stmt.setString(8, minireport.getDateFiled());
            stmt.setInt(9, minireport.getDoctorID());
            stmt.setInt(10, minireport.getSectionID());

            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new MiniReport(
                            //[IDReport], [FullName], [DateOfBirth], [BriefStatement], [ContactPhone1], [ContactPhone2], [NextOfKin], [RelationsToPatient], [DateFiled]
                            resultSet.getInt("IDReport"),
                            resultSet.getString("FullName"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("BriefStatement"),
                            resultSet.getString("ContactPhone1"),
                            resultSet.getString("ContactPhone2"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MiniReport EditMiniReport(MiniReport minireport) {

        final String EDIT_MINIREPORT = "{ CALL usp_MiniRegistrationFormsUpdate (?,?,?,?,?,?,?,?,?,?,?) }";
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(EDIT_MINIREPORT)) {
            stmt.setInt(1, minireport.getIDReport());
            stmt.setString(2, minireport.getFullName());
            stmt.setString(3, minireport.getDateOfBirth());
            stmt.setString(4, minireport.getBriefStatement());
            stmt.setString(5, minireport.getContactPhone1());
            stmt.setString(6, minireport.getContactPhone2());
            stmt.setString(7, minireport.getNextOfKin());
            stmt.setString(8, minireport.getRelationsToPatient());
            stmt.setString(9, minireport.getDateFiled());
            stmt.setInt(10, minireport.getDoctorID());
            stmt.setInt(11, minireport.getSectionID());

            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new MiniReport(
                            //[IDReport], [FullName], [DateOfBirth], [BriefStatement], [ContactPhone1], [ContactPhone2], [NextOfKin], [RelationsToPatient], [DateFiled]
                            resultSet.getInt("IDReport"),
                            resultSet.getString("FullName"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("BriefStatement"),
                            resultSet.getString("ContactPhone1"),
                            resultSet.getString("ContactPhone2"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean DeleteMiniReport(int id) {
        final String DELETE_MINIREPORT = "{ CALL usp_MiniRegistrationFormsDelete (?) }";

        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_MINIREPORT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public FullReport GetFullReportByID(int id) {
        final String GET_FULLREPORT = "{ CALL dbo.usp_FullRegistrationFormsSelect (?) }";

        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_FULLREPORT)){
            stmt.setInt(1, id);
            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {

                    return new FullReport(
                            resultSet.getInt("IDReport"),
                            resultSet.getInt("IDOutpatient"),
                            resultSet.getString("FullName"),
                            resultSet.getString("Sex"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("PresentAdress"),
                            resultSet.getString("PermanentAdress"),
                            resultSet.getString("ContactPhoneWork"),
                            resultSet.getString("ContactPhoneHome"),
                            resultSet.getString("ContactMobile"),

                            resultSet.getString("Pager"),
                            resultSet.getString("Fax"),
                            resultSet.getString("Email"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("NoKAdress"),
                            resultSet.getString("NoKContactPhoneWork"),
                            resultSet.getString("NoKContactPhoneHome"),
                            resultSet.getString("NoKContactMobile"),
                            resultSet.getString("NoKPager"),

                            resultSet.getString("NoKFax"),
                            resultSet.getString("NoKEmail"),
                            resultSet.getString("MaritalStatus"),
                            resultSet.getString("NumberOfDependants"),
                            resultSet.getString("Height"),
                            resultSet.getString("Weight"),
                            resultSet.getString("BloodType"),
                            resultSet.getString("Occupation"),
                            resultSet.getString("GrossAnnualIncome"),
                            resultSet.getString("Vegetarian"),
                            resultSet.getString("Smoker"),

                            resultSet.getFloat("SmokerCigarettesPerDay"),
                            resultSet.getString("ConsumesAlcohol"),
                            resultSet.getFloat("DrinksPerDay"),
                            resultSet.getString("Stimulants"),
                            resultSet.getString("StimulantsDetails"),
                            resultSet.getFloat("CoffeeTeaPerDay"),
                            resultSet.getFloat("SoftDrinkPerDay"),
                            resultSet.getString("RegularMeals"),
                            resultSet.getString("EatingHabits"),
                            resultSet.getString("StatementOfComplaint"),
                            resultSet.getString("HistoryOfPreviousTreatment"),
                            resultSet.getString("PhysiciansTreated"),
                            resultSet.getString("HospitalsTreated"),
                            resultSet.getString("Diabetic"),
                            resultSet.getString("HyperTensive"),
                            resultSet.getString("CardiacCondition"),

                            resultSet.getString("OrthopedicCondition"),
                            resultSet.getString("MuscularCondition"),
                            resultSet.getString("NeurologicalCondition"),
                            resultSet.getString("KnownAlergies"),
                            resultSet.getString("KnownAdverseReactionsToDrugs"),
                            resultSet.getString("MajorSurgeries"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<FullReport> GetAllFullReports() {
        final String GET_FULLREPORT = "{ CALL dbo.usp_FullRegistrationFormsSelectAll }";
        List<FullReport> fullReports = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             CallableStatement stmt = con.prepareCall(GET_FULLREPORT)){
            try(ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    fullReports.add(new FullReport(
                            resultSet.getInt("IDReport"),
                            resultSet.getInt("IDOutpatient"),
                            resultSet.getString("FullName"),
                            resultSet.getString("Sex"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("PresentAdress"),
                            resultSet.getString("PermanentAdress"),
                            resultSet.getString("ContactPhoneWork"),
                            resultSet.getString("ContactPhoneHome"),
                            resultSet.getString("ContactMobile"),

                            resultSet.getString("Pager"),
                            resultSet.getString("Fax"),
                            resultSet.getString("Email"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("NoKAdress"),
                            resultSet.getString("NoKContactPhoneWork"),
                            resultSet.getString("NoKContactPhoneHome"),
                            resultSet.getString("NoKContactMobile"),
                            resultSet.getString("NoKPager"),

                            resultSet.getString("NoKFax"),
                            resultSet.getString("NoKEmail"),
                            resultSet.getString("MaritalStatus"),
                            resultSet.getString("NumberOfDependants"),
                            resultSet.getString("Height"),
                            resultSet.getString("Weight"),
                            resultSet.getString("BloodType"),
                            resultSet.getString("Occupation"),
                            resultSet.getString("GrossAnnualIncome"),
                            resultSet.getString("Vegetarian"),
                            resultSet.getString("Smoker"),

                            resultSet.getFloat("SmokerCigarettesPerDay"),
                            resultSet.getString("ConsumesAlcohol"),
                            resultSet.getFloat("DrinksPerDay"),
                            resultSet.getString("Stimulants"),
                            resultSet.getString("StimulantsDetails"),
                            resultSet.getFloat("CoffeeTeaPerDay"),
                            resultSet.getFloat("SoftDrinkPerDay"),
                            resultSet.getString("RegularMeals"),
                            resultSet.getString("EatingHabits"),
                            resultSet.getString("StatementOfComplaint"),
                            resultSet.getString("HistoryOfPreviousTreatment"),
                            resultSet.getString("PhysiciansTreated"),
                            resultSet.getString("HospitalsTreated"),
                            resultSet.getString("Diabetic"),
                            resultSet.getString("HyperTensive"),
                            resultSet.getString("CardiacCondition"),

                            resultSet.getString("OrthopedicCondition"),
                            resultSet.getString("MuscularCondition"),
                            resultSet.getString("NeurologicalCondition"),
                            resultSet.getString("KnownAlergies"),
                            resultSet.getString("KnownAdverseReactionsToDrugs"),
                            resultSet.getString("MajorSurgeries"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    ));
                }
                return fullReports;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullReports;
    }

    @Override
    public FullReport InsertFullReport(FullReport fullReport) {
        final String INSERT_FULLREPORT = "{ CALL usp_FullRegistrationFormsInsert (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(INSERT_FULLREPORT)) {
            stmt.setInt(1, fullReport.getIDOutpatient());
            stmt.setString(2, fullReport.GetPatientName());
            stmt.setString(3, fullReport.getSex());
            stmt.setString(4, fullReport.getDateOfBirth());
            stmt.setString(5, fullReport.getPresentAdress());
            stmt.setString(6, fullReport.getPermanentAdress());
            stmt.setString(7, fullReport.getContactPhoneWork());
            stmt.setString(8, fullReport.getContactPhoneHome());
            stmt.setString(9,fullReport.getContactMobile());
            stmt.setString(10, fullReport.getPager());
            stmt.setString(11, fullReport.getFax());
            stmt.setString(12, fullReport.getEmail());

            stmt.setString(13, fullReport.getNextOfKin());
            stmt.setString(14, fullReport.getRelationsToPatient());
            stmt.setString(15, fullReport.getNoKAdress());
            stmt.setString(16, fullReport.getNoKContactPhoneWork());
            stmt.setString(17, fullReport.getNoKContactPhoneHome());
            stmt.setString(18, fullReport.getNoKContactMobile());
            stmt.setString(19, fullReport.getNoKPager());
            stmt.setString(20, fullReport.getNoKFax());
            stmt.setString(21, fullReport.getNoKEmail());

            stmt.setString(22, fullReport.getMaritalStatus());
            stmt.setString(23, fullReport.getNumberOfDependants());
            stmt.setString(24, fullReport.getHeight());
            stmt.setString(25, fullReport.getWeight());
            stmt.setString(26, fullReport.getBloodType());
            stmt.setString(27, fullReport.getOccupation());
            stmt.setString(28, fullReport.getGrossAnnualIncome());

            stmt.setString(29, fullReport.getVegetarian());
            stmt.setString(30, fullReport.getSmoker());
            stmt.setFloat(31, fullReport.getSmokerCigarettesPerDay());
            stmt.setString(32, fullReport.getConsumesAlcohol());
            stmt.setFloat(33, fullReport.getDrinksPerDay());
            stmt.setString(34, fullReport.getStimulants());
            stmt.setString(35, fullReport.getStimulantsDetails());
            stmt.setFloat(36, fullReport.getCoffeeTeaPerDay());
            stmt.setFloat(37, fullReport.getSoftDrinkPerDay());
            stmt.setString(38, fullReport.getRegularMeals());
            stmt.setString(39, fullReport.getEatingHabits());
            stmt.setString(40, fullReport.getStatementOfComplaint());
            stmt.setString(41, fullReport.getHistoryOfPreviousTreatment());
            stmt.setString(42, fullReport.getPhysiciansTreated());

            stmt.setString(43, fullReport.getHospitalsTreated());
            stmt.setString(44, fullReport.getDiabetic());
            stmt.setString(45, fullReport.getHyperTensive());
            stmt.setString(46, fullReport.getCardiacCondition());
            stmt.setString(47, fullReport.getOrthopedicCondition());
            stmt.setString(48, fullReport.getMuscularCondition());
            stmt.setString(49, fullReport.getNeurologicalCondition());
            stmt.setString(50, fullReport.getKnownAlergies());
            stmt.setString(51, fullReport.getKnownAdverseReactionsToDrugs());
            stmt.setString(52, fullReport.getMajorSurgeries());
            stmt.setString(53, fullReport.getDateFiled());

            stmt.setInt(54, fullReport.getDoctorID());
            stmt.setInt(55, fullReport.getSectionID());

            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {

                    return new FullReport(
                            resultSet.getInt("IDReport"),
                            resultSet.getInt("IDOutpatient"),
                            resultSet.getString("FullName"),
                            resultSet.getString("Sex"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("PresentAdress"),
                            resultSet.getString("PermanentAdress"),
                            resultSet.getString("ContactPhoneWork"),
                            resultSet.getString("ContactPhoneHome"),
                            resultSet.getString("ContactMobile"),

                            resultSet.getString("Pager"),
                            resultSet.getString("Fax"),
                            resultSet.getString("Email"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("NoKAdress"),
                            resultSet.getString("NoKContactPhoneWork"),
                            resultSet.getString("NoKContactPhoneHome"),
                            resultSet.getString("NoKContactMobile"),
                            resultSet.getString("NoKPager"),

                            resultSet.getString("NoKFax"),
                            resultSet.getString("NoKEmail"),
                            resultSet.getString("MaritalStatus"),
                            resultSet.getString("NumberOfDependants"),
                            resultSet.getString("Height"),
                            resultSet.getString("Weight"),
                            resultSet.getString("BloodType"),
                            resultSet.getString("Occupation"),
                            resultSet.getString("GrossAnnualIncome"),
                            resultSet.getString("Vegetarian"),
                            resultSet.getString("Smoker"),

                            resultSet.getFloat("SmokerCigarettesPerDay"),
                            resultSet.getString("ConsumesAlcohol"),
                            resultSet.getFloat("DrinksPerDay"),
                            resultSet.getString("Stimulants"),
                            resultSet.getString("StimulantsDetails"),
                            resultSet.getFloat("CoffeeTeaPerDay"),
                            resultSet.getFloat("SoftDrinkPerDay"),
                            resultSet.getString("RegularMeals"),
                            resultSet.getString("EatingHabits"),
                            resultSet.getString("StatementOfComplaint"),
                            resultSet.getString("HistoryOfPreviousTreatment"),
                            resultSet.getString("PhysiciansTreated"),
                            resultSet.getString("HospitalsTreated"),
                            resultSet.getString("Diabetic"),
                            resultSet.getString("HyperTensive"),
                            resultSet.getString("CardiacCondition"),

                            resultSet.getString("OrthopedicCondition"),
                            resultSet.getString("MuscularCondition"),
                            resultSet.getString("NeurologicalCondition"),
                            resultSet.getString("KnownAlergies"),
                            resultSet.getString("KnownAdverseReactionsToDrugs"),
                            resultSet.getString("MajorSurgeries"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    );
                }
            }
        } catch(Exception sqle){
            sqle.printStackTrace();
        }
        return null;
    }

    @Override
    public FullReport EditFullReport(FullReport fullReport) {
        final String EDIT_FULLREPORT = "{ CALL usp_FullRegistrationFormsUpdate (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
        try(Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(EDIT_FULLREPORT)) {
            stmt.setInt(1, fullReport.getIDReport());
            stmt.setInt(2, fullReport.getIDOutpatient());
            stmt.setString(3, fullReport.GetPatientName());
            stmt.setString(4, fullReport.getSex());
            stmt.setString(5, fullReport.getDateOfBirth());
            stmt.setString(6, fullReport.getPresentAdress());
            stmt.setString(7, fullReport.getPermanentAdress());
            stmt.setString(8, fullReport.getContactPhoneWork());
            stmt.setString(9, fullReport.getContactPhoneHome());
            stmt.setString(10,fullReport.getContactMobile());
            stmt.setString(11, fullReport.getPager());
            stmt.setString(12, fullReport.getFax());
            stmt.setString(13, fullReport.getEmail());

            stmt.setString(14, fullReport.getNextOfKin());
            stmt.setString(15, fullReport.getRelationsToPatient());
            stmt.setString(16, fullReport.getNoKAdress());
            stmt.setString(17, fullReport.getNoKContactPhoneWork());
            stmt.setString(18, fullReport.getNoKContactPhoneHome());
            stmt.setString(19, fullReport.getNoKContactMobile());
            stmt.setString(20, fullReport.getNoKPager());
            stmt.setString(21, fullReport.getNoKFax());
            stmt.setString(22, fullReport.getNoKEmail());

            stmt.setString(23, fullReport.getMaritalStatus());
            stmt.setString(24, fullReport.getNumberOfDependants());
            stmt.setString(25, fullReport.getHeight());
            stmt.setString(26, fullReport.getWeight());
            stmt.setString(27, fullReport.getBloodType());
            stmt.setString(28, fullReport.getOccupation());
            stmt.setString(29, fullReport.getGrossAnnualIncome());

            stmt.setString(30, fullReport.getVegetarian());
            stmt.setString(31, fullReport.getSmoker());
            stmt.setFloat(32, fullReport.getSmokerCigarettesPerDay());
            stmt.setString(33, fullReport.getConsumesAlcohol());
            stmt.setFloat(34, fullReport.getDrinksPerDay());
            stmt.setString(35, fullReport.getStimulants());
            stmt.setString(36, fullReport.getStimulantsDetails());
            stmt.setFloat(37, fullReport.getCoffeeTeaPerDay());
            stmt.setFloat(38, fullReport.getSoftDrinkPerDay());
            stmt.setString(39, fullReport.getRegularMeals());
            stmt.setString(40, fullReport.getEatingHabits());
            stmt.setString(41, fullReport.getStatementOfComplaint());
            stmt.setString(42, fullReport.getHistoryOfPreviousTreatment());
            stmt.setString(43, fullReport.getPhysiciansTreated());

            stmt.setString(44, fullReport.getHospitalsTreated());
            stmt.setString(45, fullReport.getDiabetic());
            stmt.setString(46, fullReport.getHyperTensive());
            stmt.setString(47, fullReport.getCardiacCondition());
            stmt.setString(48, fullReport.getOrthopedicCondition());
            stmt.setString(49, fullReport.getMuscularCondition());
            stmt.setString(50, fullReport.getNeurologicalCondition());
            stmt.setString(51, fullReport.getKnownAlergies());
            stmt.setString(52, fullReport.getKnownAdverseReactionsToDrugs());
            stmt.setString(53, fullReport.getMajorSurgeries());
            stmt.setString(54, fullReport.getDateFiled());

            stmt.setInt(55, fullReport.getDoctorID());
            stmt.setInt(56, fullReport.getSectionID());

            try(ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return new FullReport(
                            resultSet.getInt("IDReport"),
                            resultSet.getInt("IDOutpatient"),
                            resultSet.getString("FullName"),
                            resultSet.getString("Sex"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("PresentAdress"),
                            resultSet.getString("PermanentAdress"),
                            resultSet.getString("ContactPhoneWork"),
                            resultSet.getString("ContactPhoneHome"),
                            resultSet.getString("ContactMobile"),

                            resultSet.getString("Pager"),
                            resultSet.getString("Fax"),
                            resultSet.getString("Email"),
                            resultSet.getString("NextOfKin"),
                            resultSet.getString("RelationsToPatient"),
                            resultSet.getString("NoKAdress"),
                            resultSet.getString("NoKContactPhoneWork"),
                            resultSet.getString("NoKContactPhoneHome"),
                            resultSet.getString("NoKContactMobile"),
                            resultSet.getString("NoKPager"),

                            resultSet.getString("NoKFax"),
                            resultSet.getString("NoKEmail"),
                            resultSet.getString("MaritalStatus"),
                            resultSet.getString("NumberOfDependants"),
                            resultSet.getString("Height"),
                            resultSet.getString("Weight"),
                            resultSet.getString("BloodType"),
                            resultSet.getString("Occupation"),
                            resultSet.getString("GrossAnnualIncome"),
                            resultSet.getString("Vegetarian"),
                            resultSet.getString("Smoker"),

                            resultSet.getFloat("SmokerCigarettesPerDay"),
                            resultSet.getString("ConsumesAlcohol"),
                            resultSet.getFloat("DrinksPerDay"),
                            resultSet.getString("Stimulants"),
                            resultSet.getString("StimulantsDetails"),
                            resultSet.getFloat("CoffeeTeaPerDay"),
                            resultSet.getFloat("SoftDrinkPerDay"),
                            resultSet.getString("RegularMeals"),
                            resultSet.getString("EatingHabits"),
                            resultSet.getString("StatementOfComplaint"),
                            resultSet.getString("HistoryOfPreviousTreatment"),
                            resultSet.getString("PhysiciansTreated"),
                            resultSet.getString("HospitalsTreated"),
                            resultSet.getString("Diabetic"),
                            resultSet.getString("HyperTensive"),
                            resultSet.getString("CardiacCondition"),

                            resultSet.getString("OrthopedicCondition"),
                            resultSet.getString("MuscularCondition"),
                            resultSet.getString("NeurologicalCondition"),
                            resultSet.getString("KnownAlergies"),
                            resultSet.getString("KnownAdverseReactionsToDrugs"),
                            resultSet.getString("MajorSurgeries"),
                            resultSet.getString("DateFiled"),
                            resultSet.getInt("DoctorID"),
                            resultSet.getInt("SectionID")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean DeleteFullReport(int id) {
            final String DELETE_FULLREPORT = "{ CALL usp_FullRegistrationFormsDelete (?) }";

            try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_FULLREPORT)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            } catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
    }
}
