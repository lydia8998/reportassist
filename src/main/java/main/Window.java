package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.Box;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;

import NLP.NLP;
import crawl.Crawler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import main.ResultPanel.TableFiller;
import database.SQLop;

public class Window{

	private JFrame frame;
	private JTextField textField;
	private JButton button;
	private JPanel panel = new JPanel();
	private Component verticalGlue;
	private Component verticalGlue_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JCheckBox ck1;
	private JCheckBox ck2;
	private JCheckBox ck3;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JCheckBox ck4;
	private JCheckBox ck5;
	private JCheckBox ck6;
	private JPanel panel_5;
	private Component verticalGlue_2;
	private Component verticalGlue_3;
	private Component verticalGlue_4;
	private Component verticalGlue_5;
	private JProgressBar progressBar;
	private JButton button_1;
	private JPanel searchPanel;
	private ResultPanel resultPanel;
	
	private Crawler crawler;
	
	private boolean isRunning = false;
	public String keyword = null;
	private JLabel lblNewLabel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window(){
		initialize();
		actionListner();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = dimension.height;
		int glueHeight = screenHeight/2-100;
		
		frame = new JFrame();
		frame.setTitle("智能信息管理系统");
		frame.setBackground(Color.WHITE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);//最大化窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
		
		searchPanel = new JPanel();
		frame.getContentPane().add(searchPanel);
		searchPanel.setVisible(true);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
		verticalStrut = Box.createVerticalStrut(glueHeight);
		searchPanel.add(verticalStrut);
		
		verticalGlue = Box.createVerticalGlue();
		searchPanel.add(verticalGlue);
		searchPanel.add(panel);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		verticalGlue_2 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_2);
		
		panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		textField = new JTextField("数控机床");
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_5.add(textField);
		textField.setToolTipText("请键入您想要搜索的关键词");
		textField.setColumns(30);
		
		progressBar = new JProgressBar();
		panel_5.add(progressBar);
		progressBar.setStringPainted(true);
		progressBar.setString("正在搜索...");
		progressBar.setFont(new Font("宋体", Font.PLAIN, 15));
		progressBar.setVisible(false);
		progressBar.setIndeterminate(true);
		
		button = new JButton("搜索");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_5.add(button);
		
		button_1 = new JButton("显示结果");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_5.add(button_1);
		
		verticalGlue_3 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_3);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		verticalGlue_4 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_4);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		ck1 = new JCheckBox("科技部",true);
		panel_3.add(ck1);
		
		ck2 = new JCheckBox("工信部",true);
		panel_3.add(ck2);
		
		ck3 = new JCheckBox("发改委",true);
		panel_3.add(ck3);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		ck4 = new JCheckBox("论文",true);
		panel_4.add(ck4);
		
		ck5 = new JCheckBox("专利",true);
		panel_4.add(ck5);
		
		ck6 = new JCheckBox("新闻",true);
		panel_4.add(ck6);
		
		verticalGlue_5 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_5);
		verticalGlue_1 = Box.createVerticalGlue();
		searchPanel.add(verticalGlue_1);
		verticalStrut_1 = Box.createVerticalStrut(glueHeight);
		searchPanel.add(verticalStrut_1);
		
		JPanel panelSign = new JPanel();
		panelSign.setLayout(new BorderLayout());
		searchPanel.add(panelSign);
		lblNewLabel = new JLabel("北航分布与移动计算实验室 v0.2       ",JLabel.RIGHT);
		panelSign.add(lblNewLabel, BorderLayout.EAST);
		
		resultPanel = new ResultPanel();
		frame.getContentPane().add(resultPanel);
		resultPanel.setVisible(false);
	}
	
	private void actionListner(){

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				keyword = textField.getText();
				boolean[] options = new boolean[6];
				options[0] = ck1.isSelected();
				options[1] = ck2.isSelected();
				options[2] = ck3.isSelected();
				options[3] = ck4.isSelected();
				options[4] = ck5.isSelected();
				options[5] = ck6.isSelected();
				if(isRunning){
					crawler.stop();
				}else{
					crawler = new Crawler(keyword, options);
				}
				stateChange();
			}
		});
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				keyword = textField.getText();
				resultPanel.getResult(keyword);
				UIswitch_search();
			}
		});
	}
	
	public void UIswitch_search(){
		searchPanel.setVisible(false);
		resultPanel.setVisible(true);
	}
	
	public void UIswitch_back(){
		resultPanel.setVisible(false);
		searchPanel.setVisible(true);
	}

	private void stateChange(){
		

		isRunning = !isRunning;
		if(isRunning){
			button.setText("停止");
			textField.setVisible(false);
			progressBar.setString("正在搜索: " + keyword);
			progressBar.setVisible(true);
			
		}else{
			button.setText("搜索");
			progressBar.setVisible(false);
			textField.setVisible(true);
			
		}
	}
	
	public class ResultPanel extends JPanel {
		private JTable table;
		private StyledDocument styleModel;
		private JTextPane textPane;
		private SQLop sqlop;
		private JPanel panel;

		private List<Map<String, String>> result;
		private int resultSize;

		String keyword = null;
		SQLop database = new SQLop();

		public ResultPanel() {
			
			iniStyleModel();
			setLayout(new BorderLayout(0, 0));
			
			JPanel panel_4 = new JPanel();
			add(panel_4, BorderLayout.CENTER);
			panel_4.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_2 = new JPanel();
			panel_4.add(panel_2, BorderLayout.NORTH);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			JButton button_back = new JButton("返回搜索界面");
			panel_2.add(button_back, BorderLayout.EAST);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			panel_4.add(tabbedPane);
			
			JPanel panel_3 = new JPanel();
			tabbedPane.addTab("搜索结果", null, panel_3, null);
			tabbedPane.addTab("全体数据", null, new JPanel(), null);
			panel_3.setLayout(new BorderLayout(0, 0));
			
			JPanel panel_up = new JPanel();
			panel_up.setVisible(false);
			panel_3.add(panel_up, BorderLayout.NORTH);
			panel_up.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JLabel label = new JLabel("筛选：");
			panel_up.add(label);
			
			JCheckBox checkBox = new JCheckBox("科技部");
			panel_up.add(checkBox);
			
			JCheckBox checkBox_1 = new JCheckBox("工信部");
			panel_up.add(checkBox_1);
			
			JCheckBox checkBox_2 = new JCheckBox("发改委");
			panel_up.add(checkBox_2);
			
			JCheckBox checkBox_3 = new JCheckBox("论文");
			panel_up.add(checkBox_3);
			
			JCheckBox checkBox_4 = new JCheckBox("专利");
			panel_up.add(checkBox_4);
			
			JCheckBox checkBox_5 = new JCheckBox("新闻");
			panel_up.add(checkBox_5);
			
			JButton button = new JButton("筛选");
			panel_up.add(button);
			button_back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0){
					UIswitch_back();
				}
			});
			
			JPanel panel_main = new JPanel();
			panel_3.add(panel_main, BorderLayout.CENTER);
			panel_main.setLayout(new BorderLayout(0, 0));
			
			panel = new JPanel();
			panel_main.add(panel);
			
			
		}
		
		public void getResult(String keyword){
			this.keyword = keyword;
			sqlop = new SQLop();
			sqlop.initialize();
			result = sqlop.search(keyword);
			sqlop.close();
			resultSize = result.size();

			table = new JTable(new DefaultTableModel(resultSize,1){
				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					return false;
				}
			});
			table.setRowHeight(80);
			table.getTableHeader().setVisible(false);
			table.setDefaultRenderer(Object.class, new TableFiller());
			table.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = table.rowAtPoint(evt.getPoint());
			        int col = table.columnAtPoint(evt.getPoint());
			        if (row >= 0 && col >= 0) {
			        	String article = result.get(row).get("content");
			        	new DetailFrame(NLP.stringsummary(article), article).setVisible(true);;
			        }
			    }
			});
			
			panel.setLayout(new BorderLayout(0, 0));
			JScrollPane scrollPane = new JScrollPane(table);
			panel.add(scrollPane, BorderLayout.CENTER);
		}
		
		class TableFiller implements TableCellRenderer{

			@Override
			public Component getTableCellRendererComponent(JTable arg0,
					Object arg1, boolean arg2, boolean arg3, int row, int column) {
				textPane = new JTextPane();
				StyledDocument sd = getNewStyledDocument();
				insertDoc(sd, result.get(row).get("type"), "STYLE_type");
				insertDoc(sd, "  " + result.get(row).get("title").toString() + "\n", "STYLE_title");
				if(result.get(row).get("time") != null)
					insertDoc(sd, result.get(row).get("time").toString() + "\t", "STYLE_author");
				insertDoc(sd, result.get(row).get("author").toString() + "\n", "STYLE_author");
				insertDoc(sd, result.get(row).get("url").toString() + "\n", "STYLE_url");
				textPane.setStyledDocument(sd);
				return textPane;
			}
			
		}
		
		public StyledDocument getNewStyledDocument() {
			
			return new DefaultStyledDocument();
		}
		
		

		public StyledDocument insertDoc(StyledDocument styledDoc, String content,
				String currentStyle) {
			if(content == null) content = "";
			try {
				int length = styledDoc.getLength();
				styledDoc.insertString(length, content,
						styleModel.getStyle(currentStyle));
			} catch (BadLocationException e) {
				System.err.println("BadLocationException: " + e);
			}
			return styledDoc;
		}
		
		private void iniStyleModel(){
			styleModel = new DefaultStyledDocument();
			createStyle("STYLE_url", styleModel, 16, false, false, true, Color.GRAY, Color.WHITE, "Times New Roman");
			createStyle("STYLE_title", styleModel, 20, true, false, false, Color.BLACK, Color.WHITE, "黑体");
			createStyle("STYLE_abstract", styleModel, 16, false, false, false, Color.BLACK, Color.WHITE, "宋体");
			createStyle("STYLE_author", styleModel, 16, false, false, false, Color.BLACK, Color.WHITE, "楷体");
			createStyle("STYLE_type", styleModel, 20, false, false, false, Color.WHITE, Color.BLACK, "黑体");	
		}

		public void createStyle(String style, StyledDocument doc, int size,
				boolean bold, boolean italic, boolean underline, Color color, Color bcolor, String fontName) {
			Style sys = StyleContext.getDefaultStyleContext().getStyle(
					StyleContext.DEFAULT_STYLE);
			try {
				doc.removeStyle(style);
			} catch (Exception e) {
			} // 先删除这种Style,假使他存在

			Style s = doc.addStyle(style, sys); // 加入
			StyleConstants.setFontSize(s, size); // 大小
			StyleConstants.setBold(s, bold); // 粗体
			StyleConstants.setItalic(s, italic); // 斜体
			StyleConstants.setUnderline(s, underline); // 下划线
			StyleConstants.setForeground(s, color); // 颜色
			StyleConstants.setFontFamily(s, fontName); // 字体
			StyleConstants.setBackground(s, bcolor);//背景颜色
		}
		
	}
	
}
