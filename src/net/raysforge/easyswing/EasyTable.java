package net.raysforge.easyswing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class EasyTable implements Scrollable {

	JTable table = new JTable();
	JScrollPane scroller = new JScrollPane(table);
	TableRowSorter<DefaultTableModel> rs = new TableRowSorter<DefaultTableModel>();
	private DefaultTableModel model;
	private JPopupMenu pop;

	public EasyTable(final Class<?>[] columnClasses) {
		table.setModel(new DefaultTableModel() {
			public Class<?> getColumnClass(int c) {
				return columnClasses[c];
			}
		});
		init();
	}

	public EasyTable() {
		init();
	}

	private void init() {
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		model = (DefaultTableModel) table.getModel();
		rs.setModel(model);
		table.setRowSorter(rs);
	}

	public JTable getJTable() {
		return table;
	}

	public JScrollPane getScrollPane() {
		return scroller;
	}

	public void setColumnWidth(int col, int width) {
		setColumnWidth(col, width, true);
	}

	public void setColumnWidth(int col, int width, boolean setMax) {
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn column = table.getColumnModel().getColumn(col);
		column.setPreferredWidth(width);
		//column.setMinWidth( width);
		if (setMax)
			column.setMaxWidth(width);
		column.setWidth(width);
	}

	public void addColumn(String name) {
		model.addColumn(name);

		/*
		TableColumn tableColumn = new TableColumn();
		tableColumn.setHeaderValue(name);
		table.addColumn( tableColumn);
		*/
	}

	public int addValue(String text) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(new Object[] { text, "" });

		return model.getRowCount();
		// model.setValueAt(model, 0, 0)
	}

	public void addValue(Object... values) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(values);
		// model.setValueAt(model, 0, 0)
	}

	public void addRow(Object... values) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(values);
	}

	public void appendRow(Object... values) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(values);
	}

	public void addSelectionListener(ListSelectionListener lsl) {
		table.getSelectionModel().addListSelectionListener(lsl);

	}

	public String getValue(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	public void setFilter(String regex) {
		rs.setRowFilter(RowFilter.regexFilter(regex, 2));
	}

	// top left cell is 0,0.
	public void setValue(Object value, int row, int column) {
		if ((row + 1) > model.getRowCount()) {
			model.setRowCount(row + 1);
			model.fireTableRowsInserted(row, row);
		}
		table.setValueAt(value, row, column);
	}

	public void removeAllColumns() {
		boolean b = false;
		if (b) {
			TableColumnModel cm = table.getColumnModel();
			while (cm.getColumnCount() > 0)
				cm.removeColumn(cm.getColumn(1));
		} else {
			model.setColumnCount(0);
		}

	}

	public static void main(String[] args) {
		EasySwing es = new EasySwing("TestEasyTable", 800, 600);
		EasyTable et = es.setTableAsMainContent();
		et.addColumn("Key");
		et.addColumn("Value");
		et.setValue("test-0.0", 0, 0);
		et.setValue("test-10.10", 10, 1);
		es.show();
	}

	public int getSelectedRow() {
		return table.getSelectedRow();
	}

	public int getColumnIndex(Object columnIdentifier) {
		return table.getColumnModel().getColumnIndex(columnIdentifier);
	}

	public void setShowGrid(boolean b) {
		table.setShowGrid(b);
	}

	public void removeAllRows() {
		model.setNumRows(0);
	}

	public void setPopupMenu(EasyPopupMenu pop) {
		this.pop = pop.pop;
		//table.setComponentPopupMenu(pop);
	}

	public void setPopupMenu(JPopupMenu pop) {
		this.pop = pop;
		//table.setComponentPopupMenu(pop);
	}

	public void activateRightMouseSelect() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0 || pop == null)
					return;
				if (SwingUtilities.isRightMouseButton(e) /* && e.isPopupTrigger() */&& e.getComponent() instanceof JTable) {
					pop.show(e.getComponent(), e.getX(), e.getY());
				}

			}
		});
	}

	public void addActionListener(final ActionListener al) {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int r = table.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table.getRowCount()) {
					table.setRowSelectionInterval(r, r);
				} else {
					table.clearSelection();
				}

				int rowindex = table.getSelectedRow();
				if (rowindex < 0)
					return;
				if (e.getClickCount() == 2) {
					al.actionPerformed(new ActionEvent(e, rowindex, "tableAction"));
				}

			}
		});

	}

	public void incRowHeight(int rowHeight) {
		table.setRowHeight(table.getRowHeight()+rowHeight);
	}

	public void incFont(int n) {
		Font font = table.getFont();
		table.setFont(font.deriveFont(font.getStyle(), font.getSize()+n));
	}

	public int getRowCount() {
		return table.getRowCount();
	}

}
