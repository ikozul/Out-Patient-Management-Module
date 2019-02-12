import DALLibrary.DateTimeConverter;
import Models.FullReport;
import Models.IShowReport;
import Models.MiniReport;
import Models.ReportFilter;
import Repo.RepoFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Home {

    String[] tableTitles= {"Report ID","Doctor's Name","Patient's Name", "Section","Time Filled","Date Filled","Report Type"};
    Object[][] TableData;

    JFrame frame;
    private JPanel pnlMain;
    private JTable tblReports;
    private JScrollPane scrollPane;
    private JPanel pnlSearchBar;
    private JPanel pnlTable;
    private ReportFilter dataFilter;

    private JLabel lblReportIDSearch;
    private JTextField tfReportIDSearch;
    private JButton btnReportIDSearch;

    private JLabel lblPatientNameSearch;
    private JTextField tfPatientNameSearch;;
    private JButton btnPatientNameSearch;;

    private JPanel pnlAddBar;
    private JButton btnNewMini;
    private JButton btnNewFull;
    private JButton btnRefresh;




    public Home(){

        btnRefresh = new JButton("Refresh Data");
        btnNewMini = new JButton("Add New Mini Report");
        btnNewFull = new JButton("Add New Full Report");
        pnlAddBar = new JPanel(new FlowLayout());

        pnlAddBar.add(btnRefresh);
        pnlAddBar.add(btnNewMini);
        pnlAddBar.add(btnNewFull);

        lblReportIDSearch = new JLabel("Search By Report ID: ");
        tfReportIDSearch = new JTextField();
        tfReportIDSearch.setColumns(5);
        btnReportIDSearch = new JButton("Search By ID");

        lblPatientNameSearch = new JLabel("        Patient's Name: ");
        tfPatientNameSearch = new JTextField();
        tfPatientNameSearch.setColumns(10);
        btnPatientNameSearch = new JButton("Search By Name");

        getReportData();

        frame = new JFrame();
        pnlMain.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnlMain = new JPanel();

        pnlSearchBar = new JPanel(new FlowLayout());

        pnlSearchBar.add(lblReportIDSearch);
        pnlSearchBar.add(tfReportIDSearch);
        pnlSearchBar.add(btnReportIDSearch);

        pnlSearchBar.add(lblPatientNameSearch);
        pnlSearchBar.add(tfPatientNameSearch);
        pnlSearchBar.add(btnPatientNameSearch);

        pnlMain.add(pnlSearchBar);
        pnlMain.add(pnlAddBar);

        pnlTable= new JPanel();
        pnlTable.setLayout( new GridLayout(1,1));
        DefaultTableModel model = new DefaultTableModel(TableData,tableTitles);
        tblReports = new JTable(model);
        scrollPane = new JScrollPane(tblReports);
        pnlTable.add(scrollPane);
        pnlMain.add(pnlTable);

        tblReports.setPreferredSize(new Dimension(700,500));

        tblReports.setPreferredScrollableViewportSize(tblReports.getPreferredSize());
        tblReports.setFillsViewportHeight(true);

        frame.setContentPane(pnlMain);
        //pnlMain.add(btnOne);

        frame.setSize(780,700);
        centerFrame(frame);

        setListeners();
        setTableListeners();
        setTableSettings();

        frame.setTitle("Java 1 Project");
        frame.setVisible(true);

        Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();
        FullReport fr = repo.GetFullReportByID(1);
        fr.setSectionID(2);
        System.out.println(fr);
        fr=repo.EditFullReport(fr);
        System.out.println(fr);
    }

    private void setTableSettings() {
        tblReports.setPreferredSize(new Dimension(700,500));

        tblReports.setPreferredScrollableViewportSize(tblReports.getPreferredSize());
        tblReports.setFillsViewportHeight(true);
    }

    private void setTableListeners() {
        tblReports.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(event.getValueIsAdjusting()) {
                    reportSelected(tblReports.getValueAt(tblReports.getSelectedRow(), 0).toString(),
                            tblReports.getValueAt(tblReports.getSelectedRow(), 6).toString());
                }
            }
        });
    }

    private void setListeners() {
        btnReportIDSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ReportFilter reportFilter = new ReportFilter(Integer.parseInt(tfReportIDSearch.getText()), null);
                    applyDataFilter(reportFilter);
                }catch(Exception c) {
                    applyDataFilter(null);
                }
            }
        });

        btnPatientNameSearch.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ReportFilter reportFilter = new ReportFilter(null, tfPatientNameSearch.getText());
                    applyDataFilter(reportFilter);
                }catch(Exception ee) {
                    ee.printStackTrace();
                    applyDataFilter(null);
                }
            }
        });

        btnNewMini.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    SwingUtilities.invokeLater(() -> {
                        final MiniReportForm newMiniReport = new MiniReportForm();
                        newMiniReport.frame.addWindowListener (new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                getReportData();
                            }
                        });
                    });
                }catch(Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        btnNewFull.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    System.out.println( "Full");
                }catch(Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        btnRefresh.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try {
                    ReportFilter reportFilter = null;
                    applyDataFilter(reportFilter);
                }catch(Exception c) {
                    applyDataFilter(null);
                }
            }
        });
    }

    private void applyDataFilter(ReportFilter reportFilter) {
        dataFilter=reportFilter;
        getReportData();
/*        System.out.println("ApplyFilterEnd: ");
        System.out.println("Filter: " + dataFilter.getIDFilter().toString()+" " + dataFilter.getPatientNameFilter());*/
        tblReports.repaint();
    }

    private void reportSelected(String s, String type) {
        Integer reportID;
        try {
            reportID = Integer.parseInt(s);

            if(type == "Mini Report") {
                Integer finalReportID = reportID;
                SwingUtilities.invokeLater(() -> {
                    final MiniReportForm editingMiniReport = new MiniReportForm(finalReportID);
                    editingMiniReport.frame.addWindowListener (new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            getReportData();
                        }
                    });
                });
            }
            if(type == "Full Report") {
                Integer finalReportID = reportID;
                /*SwingUtilities.invokeLater(() -> {
                    final MiniReportForm editingMiniReport = new MiniReportForm(finalReportID);
                    editingMiniReport.frame.addWindowListener (new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            getReportData();
                        }
                    });
                });*/
                JOptionPane.showMessageDialog(this.frame,"ID: " + finalReportID + ", Type: " + type);
            }
        } catch (Exception e) {
          reportID=1;
        }
        //JOptionPane.showMessageDialog(this.frame,reportID + " " + type);
    }

    private void getReportData() {
        Repo.Repo repo = (Repo.Repo)RepoFactory.getRepository();

        //Generate mini reports List
        List<MiniReport> miniReports = repo.GetAllMiniReports();
        if(dataFilter!=null) {
            if(dataFilter.getIDFilter()!=null) {
                miniReports = new ArrayList<>();
                miniReports.add(repo.GetMiniReportByID(dataFilter.getIDFilter()));
            }
            else{
                if(dataFilter.getPatientNameFilter()!=null){
                    List<MiniReport> CompleteReports = miniReports;
                    miniReports = new ArrayList<>();
                    for (MiniReport report : CompleteReports
                         ) {
                        if(report.GetPatientName().equals(dataFilter.getPatientNameFilter())){
                            miniReports.add(report);
                        }
                    }
                    if(miniReports.size()==0){
                        miniReports = CompleteReports;
                    }
                }
            }
        }
        //generate full reports list
        List<FullReport> fullReports = repo.GetAllFullReports();
        if(dataFilter!=null) {
            if(dataFilter.getIDFilter()!=null) {
                fullReports = new ArrayList<>();
                fullReports.add(repo.GetFullReportByID(dataFilter.getIDFilter()));
            }
            else{
                if(dataFilter.getPatientNameFilter()!=null){
                    List<FullReport> completeReports = fullReports;
                    fullReports = new ArrayList<>();
                    for (FullReport report : completeReports
                            ) {
                        if(report.GetPatientName().equals(dataFilter.getPatientNameFilter())){
                            fullReports.add(report);
                        }
                    }
                    if(fullReports.size()==0){
                        fullReports = completeReports;
                    }
                }
            }
        }


        TableData = new Object[miniReports.size()+fullReports.size()][7];
        for (int i =0;i<miniReports.size();i++){
            MiniReport report = miniReports.get(i);
            TableData[i][0] = report.getIDReport();
            TableData[i][1] = report.GetDoctorName();
            TableData[i][2] = report.GetPatientName();
            TableData[i][3] = report.GetSectionName();
            TableData[i][4] = report.GetTimeFilled();
            TableData[i][5] = report.GetDateFilled();
            TableData[i][6] = "Mini Report";
        }

        for (int i =miniReports.size();i<fullReports.size()+miniReports.size();i++){
            FullReport report = fullReports.get(i-miniReports.size());
            TableData[i][0] = report.getIDReport();
            TableData[i][1] = report.GetDoctorName();
            TableData[i][2] = report.GetPatientName();
            TableData[i][3] = report.GetSectionName();
            TableData[i][4] = report.GetTimeFilled();
            TableData[i][5] = report.GetDateFilled();
            TableData[i][6] = "Full Report";
        }

        if(tblReports != null){
            pnlMain.remove(pnlTable);

            pnlTable= new JPanel();
            pnlTable.setLayout( new GridLayout(1,1));
            DefaultTableModel model = new DefaultTableModel(TableData,tableTitles);
            tblReports = new JTable(model);
            scrollPane = new JScrollPane(tblReports);
            pnlTable.add(scrollPane);
            pnlMain.add(pnlTable);
            tblReports.setFillsViewportHeight(true);

            SwingUtilities.updateComponentTreeUI(frame);
            setTableListeners();
            setTableSettings();
            //frame.repaint();

        }
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
