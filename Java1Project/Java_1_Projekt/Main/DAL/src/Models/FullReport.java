package Models;

import DALLibrary.DateTimeConverter;
import Repo.RepoFactory;

import java.sql.Date;
import java.sql.Time;

public class FullReport implements IShowReport {
    private Integer ReportID;

    private int IDOutpatient;
    private String FullName;
    private String Sex;
    private String DateOfBirth;
    private String PresentAdress;
    private String PermanentAdress;

    private String ContactPhoneWork;
    private String ContactPhoneHome;
    private String ContactMobile;
    private String Pager;
    private String Fax;
    private String Email;

    private String NextOfKin;
    private String RelationsToPatient;
    private String NoKAdress;
    private String NoKContactPhoneWork;
    private String NoKContactPhoneHome;
    private String NoKContactMobile;
    private String NoKPager;
    private String NoKFax;
    private String NoKEmail;

    private String MaritalStatus;
    private String NumberOfDependants;

    private String Height;
    private String Weight;
    private String BloodType;
    private String Occupation;
    private String GrossAnnualIncome;
    private String Vegetarian;

    private String Smoker;
    private Float SmokerCigarettesPerDay;
    private String ConsumesAlcohol;
    private Float DrinksPerDay;
    private String Stimulants;
    private String StimulantsDetails;
    private Float CoffeeTeaPerDay;
    private Float SoftDrinkPerDay;
    private String RegularMeals;
    private String EatingHabits;

    private String StatementOfComplaint;
    private String HistoryOfPreviousTreatment;
    private String PhysiciansTreated;
    private String HospitalsTreated;
    private String Diabetic;
    private String HyperTensive;

    private String CardiacCondition;
    private String OrthopedicCondition;
    private String MuscularCondition;
    private String NeurologicalCondition;
    private String KnownAlergies;
    private String KnownAdverseReactionsToDrugs;
    private String MajorSurgeries;
    private String DateFiled;
    private int DoctorID;
    private int SectionID;

    public FullReport(Integer reportID, int IDOutpatient, String fullName, String sex, String dateOfBirth, String presentAdress, String permanentAdress, String contactPhoneWork, String contactPhoneHome, String contactMobile, String pager, String fax, String email, String nextOfKin, String relationsToPatient, String noKAdress, String noKContactPhoneWork, String noKContactPhoneHome, String noKContactMobile, String noKPager, String noKFax, String noKEmail, String maritalStatus, String numberOfDependants, String height, String weight, String bloodType, String occupation, String grossAnnualIncome, String vegetarian, String smoker, Float smokerCigarettesPerDay, String consumesAlcohol, Float drinksPerDay, String stimulants, String stimulantsDetails, Float coffeeTeaPerDay, Float softDrinkPerDay, String regularMeals, String eatingHabits, String statementOfComplaint, String historyOfPreviousTreatment, String physiciansTreated, String hospitalsTreated, String diabetic, String hyperTensive, String cardiacCondition, String orthopedicCondition, String muscularCondition, String neurologicalCondition, String knownAlergies, String knownAdverseReactionsToDrugs, String majorSurgeries, String dateFiled, int doctorID, int sectionID) {
        ReportID = reportID;
        this.IDOutpatient = IDOutpatient;
        FullName = fullName;
        Sex = sex;
        DateOfBirth = dateOfBirth;
        PresentAdress = presentAdress;
        PermanentAdress = permanentAdress;
        ContactPhoneWork = contactPhoneWork;
        ContactPhoneHome = contactPhoneHome;
        ContactMobile = contactMobile;

        Pager = pager;
        Fax = fax;
        Email = email;
        NextOfKin = nextOfKin;
        RelationsToPatient = relationsToPatient;
        NoKAdress = noKAdress;
        NoKContactPhoneWork = noKContactPhoneWork;
        NoKContactPhoneHome = noKContactPhoneHome;
        NoKContactMobile = noKContactMobile;
        NoKPager = noKPager;

        NoKFax = noKFax;
        NoKEmail = noKEmail;
        MaritalStatus = maritalStatus;
        NumberOfDependants = numberOfDependants;
        Height = height;
        Weight = weight;
        BloodType = bloodType;
        Occupation = occupation;
        GrossAnnualIncome = grossAnnualIncome;
        Vegetarian = vegetarian;
        Smoker = smoker;

        SmokerCigarettesPerDay = smokerCigarettesPerDay;
        ConsumesAlcohol = consumesAlcohol;
        DrinksPerDay = drinksPerDay;
        Stimulants = stimulants;
        StimulantsDetails = stimulantsDetails;
        CoffeeTeaPerDay = coffeeTeaPerDay;
        SoftDrinkPerDay = softDrinkPerDay;
        RegularMeals = regularMeals;
        EatingHabits = eatingHabits;
        StatementOfComplaint = statementOfComplaint;
        HistoryOfPreviousTreatment = historyOfPreviousTreatment;
        PhysiciansTreated = physiciansTreated;
        HospitalsTreated = hospitalsTreated;
        Diabetic = diabetic;
        HyperTensive = hyperTensive;
        CardiacCondition = cardiacCondition;

        OrthopedicCondition = orthopedicCondition;
        MuscularCondition = muscularCondition;
        NeurologicalCondition = neurologicalCondition;
        KnownAlergies = knownAlergies;
        KnownAdverseReactionsToDrugs = knownAdverseReactionsToDrugs;
        MajorSurgeries = majorSurgeries;
        DateFiled = dateFiled;
        DoctorID = doctorID;
        SectionID = sectionID;
    }


    @Override
    public String toString() {
        return String.format(GetDoctorName() + " " + GetPatientName() + " " + GetSectionName() + " " + GetTimeFilled() + " " + GetDateFilled());
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

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getPresentAdress() {
        return PresentAdress;
    }

    public void setPresentAdress(String presentAdress) {
        PresentAdress = presentAdress;
    }

    public String getPermanentAdress() {
        return PermanentAdress;
    }

    public void setPermanentAdress(String permanentAdress) {
        PermanentAdress = permanentAdress;
    }

    public String getContactPhoneWork() {
        return ContactPhoneWork;
    }

    public void setContactPhoneWork(String contactPhoneWork) {
        ContactPhoneWork = contactPhoneWork;
    }

    public String getContactPhoneHome() {
        return ContactPhoneHome;
    }

    public void setContactPhoneHome(String contactPhoneHome) {
        ContactPhoneHome = contactPhoneHome;
    }

    public String getContactMobile() {
        return ContactMobile;
    }

    public void setContactMobile(String contactMobile) {
        ContactMobile = contactMobile;
    }

    public String getPager() {
        return Pager;
    }

    public void setPager(String pager) {
        Pager = pager;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public String getNoKAdress() {
        return NoKAdress;
    }

    public void setNoKAdress(String noKAdress) {
        NoKAdress = noKAdress;
    }

    public String getNoKContactPhoneWork() {
        return NoKContactPhoneWork;
    }

    public void setNoKContactPhoneWork(String noKContactPhoneWork) {
        NoKContactPhoneWork = noKContactPhoneWork;
    }

    public String getNoKContactPhoneHome() {
        return NoKContactPhoneHome;
    }

    public void setNoKContactPhoneHome(String noKContactPhoneHome) {
        NoKContactPhoneHome = noKContactPhoneHome;
    }

    public String getNoKContactMobile() {
        return NoKContactMobile;
    }

    public void setNoKContactMobile(String noKContactMobile) {
        NoKContactMobile = noKContactMobile;
    }

    public String getNoKPager() {
        return NoKPager;
    }

    public void setNoKPager(String noKPager) {
        NoKPager = noKPager;
    }

    public String getNoKFax() {
        return NoKFax;
    }

    public void setNoKFax(String noKFax) {
        NoKFax = noKFax;
    }

    public String getNoKEmail() {
        return NoKEmail;
    }

    public void setNoKEmail(String noKEmail) {
        NoKEmail = noKEmail;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }

    public String getNumberOfDependants() {
        return NumberOfDependants;
    }

    public void setNumberOfDependants(String numberOfDependants) {
        NumberOfDependants = numberOfDependants;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getGrossAnnualIncome() {
        return GrossAnnualIncome;
    }

    public void setGrossAnnualIncome(String grossAnnualIncome) {
        GrossAnnualIncome = grossAnnualIncome;
    }

    public String getVegetarian() {
        return Vegetarian;
    }

    public void setVegetarian(String vegetarian) {
        Vegetarian = vegetarian;
    }

    public String getSmoker() {
        return Smoker;
    }

    public void setSmoker(String smoker) {
        Smoker = smoker;
    }

    public Float getSmokerCigarettesPerDay() {
        return SmokerCigarettesPerDay;
    }

    public void setSmokerCigarettesPerDay(Float smokerCigarettesPerDay) {
        SmokerCigarettesPerDay = smokerCigarettesPerDay;
    }

    public String getConsumesAlcohol() {
        return ConsumesAlcohol;
    }

    public void setConsumesAlcohol(String consumesAlcohol) {
        ConsumesAlcohol = consumesAlcohol;
    }

    public Float getDrinksPerDay() {
        return DrinksPerDay;
    }

    public void setDrinksPerDay(Float drinksPerDay) {
        DrinksPerDay = drinksPerDay;
    }

    public String getStimulants() {
        return Stimulants;
    }

    public void setStimulants(String stimulants) {
        Stimulants = stimulants;
    }

    public String getStimulantsDetails() {
        return StimulantsDetails;
    }

    public void setStimulantsDetails(String stimulantsDetails) {
        StimulantsDetails = stimulantsDetails;
    }

    public Float getCoffeeTeaPerDay() {
        return CoffeeTeaPerDay;
    }

    public void setCoffeeTeaPerDay(Float coffeeTeaPerDay) {
        CoffeeTeaPerDay = coffeeTeaPerDay;
    }

    public Float getSoftDrinkPerDay() {
        return SoftDrinkPerDay;
    }

    public void setSoftDrinkPerDay(Float softDrinkPerDay) {
        SoftDrinkPerDay = softDrinkPerDay;
    }

    public String getRegularMeals() {
        return RegularMeals;
    }

    public void setRegularMeals(String regularMeals) {
        RegularMeals = regularMeals;
    }

    public String getEatingHabits() {
        return EatingHabits;
    }

    public void setEatingHabits(String eatingHabits) {
        EatingHabits = eatingHabits;
    }

    public String getStatementOfComplaint() {
        return StatementOfComplaint;
    }

    public void setStatementOfComplaint(String statementOfComplaint) {
        StatementOfComplaint = statementOfComplaint;
    }

    public String getHistoryOfPreviousTreatment() {
        return HistoryOfPreviousTreatment;
    }

    public void setHistoryOfPreviousTreatment(String historyOfPreviousTreatment) {
        HistoryOfPreviousTreatment = historyOfPreviousTreatment;
    }

    public String getPhysiciansTreated() {
        return PhysiciansTreated;
    }

    public void setPhysiciansTreated(String physiciansTreated) {
        PhysiciansTreated = physiciansTreated;
    }

    public String getHospitalsTreated() {
        return HospitalsTreated;
    }

    public void setHospitalsTreated(String hospitalsTreated) {
        HospitalsTreated = hospitalsTreated;
    }

    public String getDiabetic() {
        return Diabetic;
    }

    public void setDiabetic(String diabetic) {
        Diabetic = diabetic;
    }

    public String getHyperTensive() {
        return HyperTensive;
    }

    public void setHyperTensive(String hyperTensive) {
        HyperTensive = hyperTensive;
    }

    public String getCardiacCondition() {
        return CardiacCondition;
    }

    public void setCardiacCondition(String cardiacCondition) {
        CardiacCondition = cardiacCondition;
    }

    public String getOrthopedicCondition() {
        return OrthopedicCondition;
    }

    public void setOrthopedicCondition(String orthopedicCondition) {
        OrthopedicCondition = orthopedicCondition;
    }

    public String getMuscularCondition() {
        return MuscularCondition;
    }

    public void setMuscularCondition(String muscularCondition) {
        MuscularCondition = muscularCondition;
    }

    public String getNeurologicalCondition() {
        return NeurologicalCondition;
    }

    public void setNeurologicalCondition(String neurologicalCondition) {
        NeurologicalCondition = neurologicalCondition;
    }

    public String getKnownAlergies() {
        return KnownAlergies;
    }

    public void setKnownAlergies(String knownAlergies) {
        KnownAlergies = knownAlergies;
    }

    public String getKnownAdverseReactionsToDrugs() {
        return KnownAdverseReactionsToDrugs;
    }

    public void setKnownAdverseReactionsToDrugs(String knownAdverseReactionsToDrugs) {
        KnownAdverseReactionsToDrugs = knownAdverseReactionsToDrugs;
    }

    public String getMajorSurgeries() {
        return MajorSurgeries;
    }

    public void setMajorSurgeries(String majorSurgeries) {
        MajorSurgeries = majorSurgeries;
    }

    public void setDateFiled(String dateFiled) {
        DateFiled = dateFiled;
    }
    public String getDateFiled() {
        return DateFiled;
    }
    public void setDoctorID(int doctorID) {
        DoctorID = doctorID;
    }
    public int getDoctorID() {
        return DoctorID;
    }
    public void setSectionID(int sectionID) {
        SectionID = sectionID;
    }
    public int getSectionID() {
        return SectionID;
    }

    public int getIDOutpatient() {
        return IDOutpatient;
    }

    public void setIDOutpatient(int IDOutpatient) {
        this.IDOutpatient = IDOutpatient;
    }

    public Integer getReportID() {
        return ReportID;
    }
    public Integer getIDReport() {
        return ReportID;
    }

    public void setReportID(Integer reportID) {
        ReportID = reportID;
    }
}
