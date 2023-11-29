package Pertemuan10.main;

import Pertemuan10.biodata.*;
import Pertemuan10.dao.BiodataDao;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainFrame extends JFrame{
    private List<Biodata> biodataList;

    private JLabel namaLabel;
    private JTextField namaTextField;

    private JLabel alamatLabel;
    private JTextArea alamatTextArea;

    private JLabel noHpLabel;
    private JTextField noHpTextField;

    private JLabel jenisKelaminLabel;
    private JRadioButton lakiLakiRadioButton;
    private JRadioButton perempuanRadioButton;
    private ButtonGroup bg;

    private JButton simpanButton;
    private JButton ubahButton;
    private JButton hapusButton;
    private JButton updateButton;
    private JButton simpanTxt;

    private JTable table;
    private JScrollPane scrollableTable;

    private BiodataDao biodataDao;
    private BiodataTableModel tableModel;

    private String[] id;
    private String idActive = "";

    public MainFrame( BiodataDao biodataDao) {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(
                        MainFrame.this,
                        "Apakah anda yakin ingin keluar?",
                        "Exit", JOptionPane.YES_NO_OPTION
                ) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        this.biodataDao = biodataDao;
        this.biodataList = biodataDao.findAll();

        namaLabel = new JLabel("Nama");
        namaLabel.setBounds(15, 40, 550, 10);

        namaTextField = new JTextField();
        namaTextField.setBounds(15, 60, 550, 30);

        noHpLabel = new JLabel("No HP");
        noHpLabel.setBounds(15, 100, 550, 10);

        noHpTextField = new JTextField();
        noHpTextField.setBounds(15, 120, 550, 30);

        jenisKelaminLabel = new JLabel("Jenis Kelamin");
        jenisKelaminLabel.setBounds(15, 160, 550, 10);

        lakiLakiRadioButton = new JRadioButton("Laki-laki");
        lakiLakiRadioButton.setBounds(15, 180, 550, 20);

        perempuanRadioButton = new JRadioButton("Perempuan");
        perempuanRadioButton.setBounds(15, 210, 550, 20);

        bg = new ButtonGroup();
        bg.add(lakiLakiRadioButton);
        bg.add(perempuanRadioButton);

        alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(15, 290, 550, 10);

        alamatTextArea = new JTextArea();
        alamatTextArea.setBounds(15, 310, 550, 90);

        simpanButton = new JButton("Simpan");
        simpanButton.setBounds(15, 410, 100, 40);

        ubahButton = new JButton("Ubah");
        ubahButton.setBounds(120, 410, 100, 40);

        hapusButton = new JButton("Hapus");
        hapusButton.setBounds(225, 410, 100, 40);

        updateButton = new JButton("Update");
        updateButton.setBounds(330, 410, 100, 40);

        simpanTxt = new JButton("Simpan ke TXT");
        simpanTxt.setBounds(435, 410, 130, 40);

        table = new JTable();
        scrollableTable = new JScrollPane(table);
        scrollableTable.setBounds(15, 460, 550, 200);

        tableModel = new BiodataTableModel(this.biodataList);
        table.setModel(tableModel);

        ButtonAddActionListener addActionListener = new ButtonAddActionListener(this, biodataDao);
        ButtonUbahActionListener ubahActionListener = new ButtonUbahActionListener(this);
        ButtonDeleteActionListener deleteActionListener = new ButtonDeleteActionListener(this, biodataDao);
        ButtonUpdateActionListener updateActionListener = new ButtonUpdateActionListener(this, biodataDao);
        ButtonSimpanTxtActionListener simpanTxtActionListener = new ButtonSimpanTxtActionListener(this, biodataDao);

        simpanButton.addActionListener(addActionListener);
        ubahButton.addActionListener(ubahActionListener);
        hapusButton.addActionListener(deleteActionListener);
        updateButton.addActionListener(updateActionListener);
        simpanTxt.addActionListener(simpanTxtActionListener);

        this.add(namaLabel);
        this.add(namaTextField);
        this.add(noHpLabel);
        this.add(noHpTextField);
        this.add(jenisKelaminLabel);
        this.add(lakiLakiRadioButton);
        this.add(perempuanRadioButton);
        this.add(alamatLabel);
        this.add(alamatTextArea);
        this.add(simpanButton);
        this.add(ubahButton);
        this.add(hapusButton);
        this.add(updateButton);
        this.add(simpanTxt);
        this.add(scrollableTable);

        this.setSize(600, 800);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setId(String idActive) {
        this.idActive = idActive;
    }

    public String getId() {
        return this.idActive;
    }

    public String getNama() {
        return this.namaTextField.getText();
    }

    public void setNama(String nama) {
        this.namaTextField.setText(nama);
    }

    public String getAlamat() {
        return this.alamatTextArea.getText();
    }

    public void setAlamat(String alamat) {
        this.alamatTextArea.setText(alamat);
    }

    public String getNoHp() {
        return this.noHpTextField.getText();
    }

    public void setNoHp(String noHp) {
        this.noHpTextField.setText(noHp);
    }

    public String getJenisKelamin() {
        if(this.lakiLakiRadioButton.isSelected()) {
            return "Laki-laki";
        }
        else if(this.perempuanRadioButton.isSelected()) {
            return "Perempuan";
        }
        else {
            return "";
        }
    }

    public void setJenisKelamin(String jenisKelamin) {
        if(jenisKelamin.equals("Laki-laki")) {
            this.lakiLakiRadioButton.setSelected(true);
        }
        else if(jenisKelamin.equals("Perempuan")) {
            this.perempuanRadioButton.setSelected(true);
        }
    }

    public JTable getTable() {
        return this.table;
    }

    public void clearForm() {
        this.idActive = "";
        this.namaTextField.setText("");
        this.alamatTextArea.setText("");
        this.noHpTextField.setText("");
        this.bg.clearSelection();
    }

    public void addBiodata(Biodata biodata) {
        this.tableModel.add(biodata);
    }

    public void updateBiodata(int row, int col, Biodata biodata) {
        this.tableModel.update(row, col, biodata);
    }

    public void deleteBiodata() {
        this.tableModel.delete(this.table.getSelectedRow());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame f = new MainFrame(new BiodataDao());
                f.setVisible(true);
            }
        });
    }

}
