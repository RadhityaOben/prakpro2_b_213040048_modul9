package Pertemuan10.biodata;

import Pertemuan10.biodata.BiodataTableModel;
import Pertemuan10.dao.BiodataDao;
import Pertemuan10.main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonDeleteActionListener implements ActionListener{
    private MainFrame mainFrame;
    private BiodataDao biodataDao;
    private BiodataTableModel tableModel;

    public ButtonDeleteActionListener(MainFrame mainFrame, BiodataDao bidoataDao) {
        this.mainFrame = mainFrame;
        this.biodataDao = new BiodataDao();
        this.tableModel = new BiodataTableModel(biodataDao.findAll());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = this.mainFrame.getTable();

        if(table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(mainFrame, "Tolong pilih baris yang ingin dihapus!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table.getSelectedRow();

        int confirmStatus = JOptionPane.showConfirmDialog(mainFrame, "Apakah kamu yakin ingin menghapus data ini?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if(confirmStatus != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(mainFrame, "Proses Dibatalkan!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(mainFrame, "Data berhasil dihapus!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        String id = this.mainFrame.getTable().getValueAt(this.mainFrame.getTable().getSelectedRow(), 0).toString();

        this.mainFrame.deleteBiodata();
        this.biodataDao.delete(id);
        this.mainFrame.clearForm();

    }
}
