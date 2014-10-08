package team.mosaic.icebreaker.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MyTable extends JTable {
	private Color headerBackground = new Color(255,255,255);//��ͷ����ɫ
	private Color headerForeground = new Color(138,43,226);//��ͷ������ɫ
	private Color selectionColor = new Color(250,240,250);// ��ѡ����ɫ
	private Color evenRowColor = new Color(233, 242, 241);// ��������ɫ
	private Color oddRowColor = new Color(255, 255, 255);// ż������ɫ
	private Color gridColor = new Color(236, 233, 216);// ������ɫ
	private Color wordColor = new Color(0,0,0);//����������ɫ
	private int rowHeight = 28;// �и߶�
	private int headHeight = 33;//��ͷ�߶�
	private JTableHeader header;//��ͷ����

	public MyTable(TableModel tableModel,boolean needFit) {
		super(tableModel);
		if(needFit)
			fitTableColumns();
		setTable();
//		addListener();
	}

	public TableCellRenderer getCellRenderer(int row, int column) {
		return new MyCellRenderer();
	}
	
	private void setTable(){
	    //��ñ�ͷ
	    header = this.getTableHeader();
	    //���ñ�ͷ�ı���ɫ
	    header.setBackground(headerBackground);
	    //���ñ�ͷ��������ɫ
	    header.setForeground(headerForeground);
	    //���ñ�ͷ����
	    header.setFont(new Font("΢���ź�", Font.BOLD, 16));
	    //��̬�����ñ�ͷ�ĸ߶�
		header.setPreferredSize(new Dimension(new Dimension(this.getTableHeader().getPreferredSize().width,headHeight)));
	    
	    header.setReorderingAllowed(false);//���ò������϶�
	    header.setResizingAllowed(false);//������ı��п�
	    
	    this.setDragEnabled(false);
		this.setGridColor(gridColor);
		this.setRowHeight(rowHeight);
		this.setFont(new Font("΢���ź�", Font.PLAIN, 12));
	}
	
	private void fitTableColumns() {//�����п�
		this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int columnCount = this.getColumnCount();
		int rowCount = this.getRowCount();

		int totalWidth = 0;
		for (int col = 0; col < columnCount; col++) {
			int width = this.getColumnModel().getColumn(col)
					.getPreferredWidth();
			for (int row = 0; row < rowCount; row++) {

				Object value = this.getValueAt(row, col);

				Component c = this.getCellRenderer(row, col)
						.getTableCellRendererComponent(this, value, false,
								false, row, col);
				int preferedWidth = (int) c.getPreferredSize().getWidth();
				width = Math.max(width, preferedWidth);
			}
			TableColumn column = this.getColumnModel().getColumn(col);
			this.getTableHeader().setResizingColumn(column); // ���к���Ҫ
			column.setWidth(width + this.getIntercellSpacing().width * 2);
			totalWidth += width + this.getIntercellSpacing().width * 2;
		}
		if (this.getParent() == null) {
			return;
		}
		// ������ʵ�ʿ��С�ڸ������Ŀ�ȣ����ñ������Ӧ
		if (totalWidth < this.getParent().getPreferredSize().width) {
			this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		}
		return;
	}

	class MyCellRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			Component cell = super.getTableCellRendererComponent(table, value,
					isSelected, hasFocus, row, column);
			this.setColor(cell, table, isSelected, hasFocus, row, column);
			return cell;
		}
		/*
		 * 
		 * ������ɫ
		 */
		private void setColor(Component component, JTable table,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				component.setBackground(selectionColor);
				component.setForeground(wordColor);
				setBorder(null);// ȥ����
			} 
			else {
				if (row % 2 == 0) {
					component.setBackground(evenRowColor);
				}
				else {
					component.setBackground(oddRowColor);
				}
			}
		}
	}
	
	private void addListener(){
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int row = (arg0.getY() - headHeight)/rowHeight;
				
			}
			
			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}