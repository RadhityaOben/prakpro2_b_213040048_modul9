package Pertemuan10.biodata;

import Pertemuan10.dao.BiodataDao;
import Pertemuan10.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonUbahActionListener implements ActionListener {
    private MainFrame mainFrame;

    public ButtonUbahActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.mainFrame.getTable();

        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong pilih baris yang ingin diubah!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!this.mainFrame.getNama().equals("") || !this.mainFrame.getAlamat().equals("") || !this.mainFrame.getNoHp().equals("") || !this.mainFrame.getJenisKelamin().equals("")) {
            int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Form sedang digunakan, apakah kamu yakin ingin menimpanya?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirmStatus != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            this.mainFrame.clearForm();
        }

        int row = table.getSelectedRow();

        this.mainFrame.setId(table.getValueAt(row, 0).toString());
        this.mainFrame.setNama(table.getValueAt(row, 1).toString());
        this.mainFrame.setAlamat(table.getValueAt(row, 2).toString());
        this.mainFrame.setNoHp(table.getValueAt(row, 3).toString());
        this.mainFrame.setJenisKelamin(table.getValueAt(row, 4).toString());

    }
}