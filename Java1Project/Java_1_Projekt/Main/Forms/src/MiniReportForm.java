import DALLibrary.DateTimeConverter;
import Models.MiniReport;
import Models.ReportFilter;
import Repo.RepoFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;

public class MiniReportForm {

    MiniReport currentMiniReport;

    JFrame frame;
    JPanel grid;
    JPanel pnlBody;

    JButton btnSend;
    JButton btnEnableEditing;
    JButton btnDeleteForm;

    JLabel lblReport;
    JLabel lblFullName;
    JLabel lblDateOfBirth;
    JLabel lblBreif;
    JLabel lblContact1;
    JLabel lblContact2;
    JLabel lblNoK;
    JLabel lblRelation;
    JLabel lblDoctorID;
    JLabel lblSection;

    JTextField tfReport;
    JTextField tfFullName;
    JTextField tfDateOfBirth;
    JTextField tfBreif;
    JTextField tfContact1;
    JTextField tfContact2;
    JTextField tfNoK;
    JTextField tfRelation;
    JTextField tfDoctorID;
    JTextField tfSection;

    private void createCommonElements() {

        lblReport = new JLabel("Report ID: ");
        lblFullName = new JLabel("Patients Full Name: ");
        lblDateOfBirth = new JLabel("Date Of Birth: ");
        lblBreif = new JLabel("Brief Statement: ");
        lblContact1 = new JLabel("Contact Phone 1: ");
        lblContact2 = new JLabel("Contact Phone 2: ");
        lblNoK = new JLabel("Next Of Kin: ");
        lblRelation = new JLabel("Relationship to NoK: ");
        lblDoctorID = new JLabel("Doctor ID: ");
        lblSection = new JLabel("Section ID: ");

        tfReport = new JTextField();
        tfFullName = new JTextField();
        tfDateOfBirth = new JTextField();
        tfBreif = new JTextField();
        tfContact1 = new JTextField();
        tfContact2 = new JTextField();
        tfNoK = new JTextField();
        tfRelation = new JTextField();
        tfDoctorID = new JTextField();
        tfSection = new JTextField();
    }

    private void addCommonElements() {
        grid.add(lblReport);
        grid.add(tfReport);
        grid.add(lblFullName);
        grid.add(tfFullName);
        grid.add(lblDateOfBirth);
        grid.add(tfDateOfBirth);
        grid.add(lblBreif);
        grid.add(tfBreif);
        grid.add(lblContact1);
        grid.add(tfContact1);
        grid.add(lblContact2);
        grid.add(tfContact2);
        grid.add(lblNoK);
        grid.add(tfNoK);
        grid.add(lblRelation);
        grid.add(tfRelation);
        grid.add(lblDoctorID);
        grid.add(tfDoctorID);
        grid.add(lblSection);
        grid.add(tfSection);

        tfDateOfBirth.setToolTipText("format: YYYY-MM-DD");
    }

    public MiniReportForm(){
        frame= new JFrame();
        pnlBody = new JPanel(new BorderLayout(20,20));
        pnlBody.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
        frame.setContentPane(pnlBody);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Adding New Mini Report");
        frame.setSize(new Dimension(500,750));
        centerFrame(frame);
        createCommonElements();
        grid = new JPanel(new GridLayout(13,2,10,10));

        btnSend= new JButton("Add Report");

        frame.setLayout(new BorderLayout(10, 10));
        pnlBody.add(grid, BorderLayout.CENTER);
        addCommonElements();
        grid.add(new JPanel());
        grid.add(btnSend);
        tfReport.setEnabled(false);

        frame.setVisible(true);
        setListeners();
    }

    private void setListeners() {
        btnSend.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Random r = new Random();
                String dateFilled =DateTimeConverter.ConsolidateDateTime(new java.sql.Date(Calendar.getInstance().getTime().getTime()),
                        new java.sql.Time(Calendar.getInstance().getTime().getTime()));
                        try{
                            Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
                            MiniReport success= repo.InsertMiniReport (new MiniReport(
                                    0,tfFullName.getText(), tfDateOfBirth.getText(), tfBreif.getText(), tfContact1.getText(),
                                    tfContact2.getText(), tfNoK.getText(), tfReport.getText(), dateFilled, Integer.parseInt(tfDoctorID.getText()), Integer.parseInt(tfSection.getText())
                            ));
                            JOptionPane.showMessageDialog(frame,"Form Added with ID: !" + success.getIDReport());
                            frame.dispose();
                        }catch(Exception ea) {

                        }
            }
        });
    }

    public MiniReportForm(Integer reportID){
        Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
        MiniReport miniReport = repo.GetMiniReportByID(reportID);

        frame= new JFrame();
        pnlBody = new JPanel(new BorderLayout(20,20));
        pnlBody.setBorder(BorderFactory.createEmptyBorder(20,30,20,30));
        frame.setContentPane(pnlBody);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Editing Minireport ID: " + miniReport.getIDReport() + ", Patient: " + miniReport.GetPatientName());
        frame.setSize(new Dimension(500,750));
        centerFrame(frame);
        createCommonElements();
        grid = new JPanel(new GridLayout(13,2,10,10));

        btnEnableEditing= new JButton("Enable Editing");
        btnDeleteForm= new JButton("Delete Form");
        btnSend= new JButton("Edit Report");

        frame.setLayout(new BorderLayout(10, 10));
        pnlBody.add(grid, BorderLayout.CENTER);
        grid.add(btnEnableEditing);
        grid.add(btnDeleteForm);
        addCommonElements();
        grid.add(new JPanel());
        grid.add(btnSend);

        loadMiniReport(miniReport);
        setEditListeners();

        frame.setVisible(true);
    }

    private void setEditListeners() {
        btnEnableEditing.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                tfFullName.setEnabled(true);
                tfDateOfBirth.setEnabled(true);
                tfContact1.setEnabled(true);
                tfContact2.setEnabled(true);
                tfNoK.setEnabled(true);
                tfBreif.setEnabled(true);
                tfRelation.setEnabled(true);
                tfDoctorID.setEnabled(true);
                tfSection.setEnabled(true);
            }
        });
        btnSend.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(tfFullName.isEnabled()){
                    try{

                        currentMiniReport.setFullName(tfFullName.getText());
                        currentMiniReport.setDateOfBirth(tfDateOfBirth.getText());
                        currentMiniReport.setContactPhone1(tfContact1.getText());
                        currentMiniReport.setNextOfKin(tfNoK.getText());
                        currentMiniReport.setBriefStatement(tfBreif.getText());
                        currentMiniReport.setRelationsToPatient(tfRelation.getText());
                        currentMiniReport.setDoctorID(Integer.parseInt(tfDoctorID.getText()));
                        currentMiniReport.setSectionID(Integer.parseInt(tfSection.getText()));

                        Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
                        System.out.println("edit");
                        MiniReport r= repo.EditMiniReport(currentMiniReport);
                        JOptionPane.showMessageDialog(frame,"Succesfully edited Report with ID: " + r.getIDReport());
                        frame.dispose();
                    }catch(Exception ea){

                    }
                }
            }
        });
        btnDeleteForm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(tfFullName.isEnabled()){
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (frame, "Do you really want to delete this report?","Warning",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        try{
                            Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
                            boolean success= repo.DeleteMiniReport(currentMiniReport.getIDReport());
                            JOptionPane.showMessageDialog(frame,"Form Deleted!");
                            frame.dispose();
                        }catch(Exception ea){

                        }
                    }
                }
            }
        });
    }

    private void loadMiniReport(MiniReport miniReport) {
        currentMiniReport = miniReport;

        tfReport.setText(String.valueOf(miniReport.getIDReport()));
        tfFullName.setText(currentMiniReport.getFullName());
        tfDateOfBirth.setText(currentMiniReport.getDateOfBirth());
        tfContact1.setText(currentMiniReport.getContactPhone1());
        tfContact2.setText(currentMiniReport.getContactPhone2());
        tfNoK.setText(currentMiniReport.getNextOfKin());
        tfRelation.setText(currentMiniReport.getRelationsToPatient());
        tfDoctorID.setText(String.valueOf(miniReport.getDoctorID()));
        tfSection.setText(String.valueOf(miniReport.getSectionID()));
        tfReport.setEnabled(false);

        tfFullName.setEnabled(false);
        tfDateOfBirth.setEnabled(false);
        tfContact1.setEnabled(false);
        tfContact2.setEnabled(false);
        tfNoK.setEnabled(false);
        tfBreif.setEnabled(false);
        tfRelation.setEnabled(false);
        tfDoctorID.setEnabled(false);
        tfSection.setEnabled(false);
    }


    private void centerFrame(JFrame form) {

        Dimension windowSize = frame.getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();

        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;
        frame.setLocation(dx, dy);
    }
}
