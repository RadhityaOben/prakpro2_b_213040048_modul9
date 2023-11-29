package Pertemuan10.biodata;

import javax.swing.table.AbstractTableModel;
import java.io.FileWriter;
import java.util.List;

public class BiodataTableModel extends AbstractTableModel {
    private String[] columnNames = {"ID", "Nama", "Alamat", "No HP", "Jenis Kelamin"};
    private List<Biodata> data;

    public BiodataTableModel(List<Biodata> data) {
        this.data = data;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        Biodata rowItem = data.get(row);
        String value = "";

        switch(col) {
            case 0:
                value = rowItem.getId();
                break;
            case 1:
                value = rowItem.getNama();
                break;
            case 2:
                value = rowItem.getAlamat();
                break;
            case 3:
                value = rowItem.getNoHp();
                break;
            case 4:
                value = rowItem.getJenisKelamin();
                break;
        }
        return value;
    }

    public boolean isCellEditable(int row, int col) {
        return false;
    }

    public void add(Biodata value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void update(int row, int col, Biodata value) {
        Biodata rowItem = data.get(row);

        switch (col) {
            case 0:
                rowItem.setId(value.getId());
                break;
            case 1:
                rowItem.setNama(value.getNama());
                break;
            case 2:
                rowItem.setAlamat(value.getAlamat());
                break;
            case 3:
                rowItem.setNoHp(value.getNoHp());
                break;
            case 4:
                rowItem.setJenisKelamin(value.getJenisKelamin());
                break;
        }

        data.set(row, rowItem);
        fireTableRowsUpdated(row, col);
    }

    public void delete(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

    public void saveToTxt() throws Exception {
        FileWriter saveText = new FileWriter("data.txt");
        int rowLength = getRowCount();
        int colLength = getColumnCount();

        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength; j++) {
                if(j != 0) saveText.write(" - ");
                saveText.write(getValueAt(i, j).toString());
            }
            saveText.write("\n");
        }

        saveText.close();
    }
}
