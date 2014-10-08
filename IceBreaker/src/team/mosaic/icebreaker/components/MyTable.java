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
	private Color headerBackground = new Color(255,255,255);//表头背景色
	private Color headerForeground = new Color(138,43,226);//表头字体颜色
	private Color selectionColor = new Color(250,240,250);// 行选择颜色
	private Color evenRowColor = new Color(233, 242, 241);// 奇数行颜色
	private Color oddRowColor = new Color(255, 255, 255);// 偶数行颜色
	private Color gridColor = new Color(236, 233, 216);// 网格颜色
	private Color wordColor = new Color(0,0,0);//表中字体颜色
	private int rowHeight = 28;// 行高度
	private int headHeight = 33;//表头高度
	private JTableHeader header;//表头对象

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
	    //获得表头
	    header = this.getTableHeader();
	    //设置表头的背景色
	    header.setBackground(headerBackground);
	    //设置表头的文字颜色
	    header.setForeground(headerForeground);
	    //设置表头字体
	    header.setFont(new Font("微软雅黑", Font.BOLD, 16));
	    //动态！设置表头的高度
		header.setPreferredSize(new Dimension(new Dimension(this.getTableHeader().getPreferredSize().width,headHeight)));
	    
	    header.setReorderingAllowed(false);//设置不允许拖动
	    header.setResizingAllowed(false);//不允许改变列宽
	    
	    this.setDragEnabled(false);
		this.setGridColor(gridColor);
		this.setRowHeight(rowHeight);
		this.setFont(new Font("微软雅黑", Font.PLAIN, 12));
	}
	
	private void fitTableColumns() {//调整列宽
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
			this.getTableHeader().setResizingColumn(column); // 此行很重要
			column.setWidth(width + this.getIntercellSpacing().width * 2);
			totalWidth += width + this.getIntercellSpacing().width * 2;
		}
		if (this.getParent() == null) {
			return;
		}
		// 如果表格实际宽度小于父容器的宽度，则让表格自适应
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
		 * 设置颜色
		 */
		private void setColor(Component component, JTable table,
				boolean isSelected, boolean hasFocus, int row, int column) {
			if (isSelected) {
				component.setBackground(selectionColor);
				component.setForeground(wordColor);
				setBorder(null);// 去掉边
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