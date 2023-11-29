package Pertemuan10.biodata;

import Pertemuan10.biodata.Biodata;
import Pertemuan10.dao.BiodataDao;
import Pertemuan10.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class ButtonAddActionListener implements ActionListener{
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonAddActionListener(MainFrame mainFrame, BiodataDao bidoataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = new BiodataDao();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!mainFrame.getId().equals("")) {
            int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Form sedang digunakan, apakah kamu ingin menambah data baru?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmStatus != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            this.mainFrame.clearForm();
            return;
        }

        JTable table = this.mainFrame.getTable();

        if(mainFrame.getNama().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi nama!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(mainFrame.getNoHp().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi nomor hp!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        } else if(mainFrame.getNoHp().toString().length() > 13) {
            JOptionPane.showMessageDialog(mainFrame, "Nomor hp harus dibawah 13 digit!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String jk = "";
        if(mainFrame.getJenisKelamin() == null ) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong pilih jenis kelamin!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(mainFrame.getAlamat().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi alamat!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Apakah kamu yakin ingin menyimpan data ini?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirmStatus != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Data berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        String nama = this.mainFrame.getNama();
        String noHp = this.mainFrame.getNoHp();
        String jenisKelamin = this.mainFrame.getJenisKelamin();
        String alamat = this.mainFrame.getAlamat();
        Biodata biodata = new Biodata();
        biodata.setId(UUID.randomUUID().toString());
        biodata.setNama(nama);
        biodata.setNoHp(noHp);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setAlamat(alamat);

        this.mainFrame.addBiodata(biodata);
        this.biodataDao.insert(biodata);
        this.mainFrame.clearForm();

    }
}
