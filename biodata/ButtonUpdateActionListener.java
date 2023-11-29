package Pertemuan10.biodata;

import Pertemuan10.biodata.Biodata;
import Pertemuan10.dao.BiodataDao;
import Pertemuan10.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class ButtonUpdateActionListener implements ActionListener{
    private MainFrame mainFrame;
    private BiodataDao biodataDao;

    public ButtonUpdateActionListener(MainFrame mainFrame, BiodataDao bidoataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = new BiodataDao();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JTable table = this.mainFrame.getTable();

        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong lakukan proses ubah terlebih dahulu!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(mainFrame.getNama().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi nama!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(mainFrame.getNoHp().equals("")) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong isi nomor hp!", "Warning", JOptionPane.WARNING_MESSAGE);
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

        int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Apakah kamu yakin ingin menyimpan data yang baru ini?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirmStatus != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Data berhasil diperbarui!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();

        String nama = this.mainFrame.getNama();
        String noHp = this.mainFrame.getNoHp();
        String jenisKelamin = this.mainFrame.getJenisKelamin();
        String alamat = this.mainFrame.getAlamat();
        String id = this.mainFrame.getId();
        Biodata biodata = new Biodata();
        biodata.setId(id);
        biodata.setNama(nama);
        biodata.setNoHp(noHp);
        biodata.setJenisKelamin(jenisKelamin);
        biodata.setAlamat(alamat);

        this.biodataDao.update(biodata);
        this.mainFrame.updateBiodata(row, col, biodata);
        this.mainFrame.clearForm();
    }
}
