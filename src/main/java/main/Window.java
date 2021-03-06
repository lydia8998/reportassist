package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
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

import AutoComplete.AutoCompleteComponet;
import AutoComplete.HistoricRecords;
import NLP.NLP;
import crawl.Crawler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;
import javax.swing.plaf.IconUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import output.MakeReport;
import chart.Chart;
import main.ResultPanel1.ResultTableFiller;
import database.SQLop;

public class Window {

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
	private JPanel panel_logo;
	private JLabel lable_logo;
	private Component verticalStrut;
	private JLabel label_1;
	private JPanel panel_6;

	private HistoricRecords history;
	private AutoCompleteComponet auto;

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
	public Window() {
		initialize();
		actionListner();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = dimension.height;
		int glueHeight = screenHeight / 2 - 100;

		frame = new JFrame();
		frame.setTitle("智能信息搜索管理系统");
		frame.setBackground(Color.WHITE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);// 最大化窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		searchPanel = new JPanel();
		frame.getContentPane().add(searchPanel);
		searchPanel.setVisible(true);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));

		verticalGlue = Box.createVerticalGlue();
		searchPanel.add(verticalGlue);

		panel_logo = new JPanel();
		searchPanel.add(panel_logo);
		ImageIcon icon = new ImageIcon(".\\resource\\logo.png");
		// icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),icon.getIconHeight(),
		// Image.SCALE_DEFAULT));
		panel_logo.setLayout(new BorderLayout(0, 0));

		panel_6 = new JPanel();
		panel_logo.add(panel_6, BorderLayout.SOUTH);
		panel_6.setLayout(new BorderLayout(0, 0));

		lable_logo = new JLabel();
		panel_6.add(lable_logo, BorderLayout.CENTER);
		lable_logo.setIcon(icon);
		lable_logo.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		lable_logo.setHorizontalAlignment(JLabel.CENTER);

		label_1 = new JLabel("智能信息搜索管理系统", JLabel.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 40));
		panel_6.add(label_1, BorderLayout.SOUTH);
		searchPanel.add(panel);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		verticalGlue_2 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_2);

		panel_5 = new JPanel();
		panel_1.add(panel_5);

		textField = new JTextField("");
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

		auto = new AutoCompleteComponet();
		history = new HistoricRecords();
		auto.updateHistory(this.getItems());
		auto.setupAutoComplete(textField);

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

		ck1 = new JCheckBox("科技部", true);
		panel_3.add(ck1);

		ck2 = new JCheckBox("工信部", true);
		panel_3.add(ck2);

		ck3 = new JCheckBox("发改委", true);
		panel_3.add(ck3);

		panel_4 = new JPanel();
		panel_2.add(panel_4);

		ck4 = new JCheckBox("论文", true);
		//ck4 = new JCheckBox("评论", true);
		panel_4.add(ck4);

		ck5 = new JCheckBox("专利", true);
		panel_4.add(ck5);

		ck6 = new JCheckBox("新闻", true);
		panel_4.add(ck6);

		verticalGlue_5 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_5);
		verticalGlue_1 = Box.createVerticalGlue();
		searchPanel.add(verticalGlue_1);

		verticalStrut = Box.createVerticalStrut(20);
		searchPanel.add(verticalStrut);

		JPanel panelSign = new JPanel();
		panelSign.setLayout(new BorderLayout());
		searchPanel.add(panelSign);
		lblNewLabel = new JLabel("北航分布与移动计算实验室 v0.2       ", JLabel.RIGHT);
		panelSign.add(lblNewLabel, BorderLayout.EAST);

		resultPanel = new ResultPanel();
		frame.getContentPane().add(resultPanel);
		resultPanel.setVisible(false);
	}

	public ArrayList<String> getItems() {
		ArrayList<String> items = new ArrayList<String>();
		Map map = auto.getStationMap(history.getHistory());
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String stationName = iterator.next().toString();
			items.add(stationName);
		}
		return items;
	}

	private void actionListner() {

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				keyword = textField.getText();
				if (keyword.trim().equals("")) {
					JOptionPane.showConfirmDialog(frame, "请在文本框中输入关键词", "提示",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				boolean[] options = new boolean[6];
				options[0] = ck1.isSelected();
				options[1] = ck2.isSelected();
				options[2] = ck3.isSelected();
				options[3] = ck4.isSelected();
				options[4] = ck5.isSelected();
				options[5] = ck6.isSelected();
				if (isRunning) {
					crawler.stop();
				} else {
					crawler = new Crawler(keyword, options);
				}
				stateChange();
			}
		});

		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				keyword = textField.getText();
				if (keyword.trim().equals("")) {
					JOptionPane.showConfirmDialog(frame, "请在文本框中输入关键词", "提示",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				resultPanel.getResult(keyword);
				UIswitch_search();
			}
		});
	}

	public void UIswitch_search() {
		searchPanel.setVisible(false);
		resultPanel.validate();
		resultPanel.setVisible(true);
	}

	public void UIswitch_back() {
		resultPanel.setVisible(false);
		searchPanel.setVisible(true);
	}

	private void stateChange() {

		isRunning = !isRunning;
		if (isRunning) {
			button.setText("停止");
			textField.setVisible(false);
			progressBar.setString("正在搜索: " + keyword);
			progressBar.setVisible(true);

		} else {
			button.setText("搜索");
			progressBar.setVisible(false);
			textField.setVisible(true);

		}
	}

	public class ResultPanel extends JPanel implements ActionListener {
		private JTable tableResult;
		private JTable tableAll;
		private StyledDocument styleModel;
		private JTextPane textPane;
		private JLabel text;
		private SQLop sqlop;
		private JPanel panel_resultList;
		private JPanel panel_allTab;
		JLabel label_chart11;
		JLabel label_chart12;
		JLabel label_chart21;
		JLabel label_chart22;
		JLabel label_chart31;
		JLabel label_chart32;
		JLabel label_chart41;
		JLabel label_chart42;
		JLabel label_chart51;

		private JButton button_gov;
		private JButton button_news;
		private JButton button_pa;

		private DefaultTableModel tableModel;
		private JPopupMenu menu;
		private JMenuItem item;
		int count = 0;// judge the table item be clicked before

		private List<Map<String, String>> result;
		private List<Map<String, String>> resultAll;
		private int resultSize;
		private int resultAllSize;

		String keyword = null;

		// SQLop database = new SQLop();

		public ResultPanel() {

			iniStyleModel();
			setLayout(new BorderLayout(0, 0));

			JPanel panel_4 = new JPanel();
			add(panel_4, BorderLayout.CENTER);
			panel_4.setLayout(new BorderLayout(0, 0));

			JPanel panel_2 = new JPanel();
			panel_4.add(panel_2, BorderLayout.NORTH);
			panel_2.setLayout(new BorderLayout(0, 0));

			JPanel panel_2_1 = new JPanel();
			JButton button_history = new JButton("保存搜索记录");
			panel_2_1.add(button_history);
			button_history.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JOptionPane.showConfirmDialog(frame, "操作完成！", "完成",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");// 设置日期格式
					history.addHistory(keyword + " " + df.format(new Date()));
					auto.updateHistory(getItems());
				}
			});

			JButton button_report = new JButton("生成报告");
			panel_2_1.add(button_report);
			button_report.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					new MakeReport(keyword, new NLP().summary(keyword),
							".\\output\\report.html", new NLP().report(keyword));
					JOptionPane.showConfirmDialog(frame,
							"操作完成！\n存储在.\\output\\report.html文件中", "完成",
							JOptionPane.CLOSED_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
				}
			});

			panel_2.add(panel_2_1, BorderLayout.EAST);

			JButton button_back = new JButton("返回搜索界面");
			button_back.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					UIswitch_back();// TODO
					
				}
			});
			JPanel panel_2_2 = new JPanel();
			panel_2_2.add(button_back);
			panel_2.add(panel_2_2, BorderLayout.WEST);

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			panel_4.add(tabbedPane);

			JPanel panel_resultTab = new JPanel();
			tabbedPane.addTab("搜索结果", null, panel_resultTab, null);
			panel_resultTab.setLayout(new BorderLayout(0, 0));

			JPanel panel_up = new JPanel();
			panel_up.setVisible(true);
			panel_resultTab.add(panel_up, BorderLayout.NORTH);
			panel_up.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			JLabel label = new JLabel("筛选：");
			panel_up.add(label);

			button_gov = new JButton("政府公文");
			panel_up.add(button_gov);

			button_pa = new JButton("论文专利");
			panel_up.add(button_pa);

			button_news = new JButton("新闻报道");
			panel_up.add(button_news);

			JPanel panel_charts = new JPanel();

			JPanel panel_chartsTab = new JPanel();
			panel_chartsTab.setLayout(new BorderLayout(0, 0));
			JScrollPane scroll_charts = new JScrollPane(panel_charts);
			panel_chartsTab.add(scroll_charts);
			tabbedPane.addTab("结果统计", null, panel_chartsTab, null);
			panel_charts
					.setLayout(new BoxLayout(panel_charts, BoxLayout.Y_AXIS));

			label_chart11 = new JLabel();
			label_chart12 = new JLabel();
			label_chart21 = new JLabel();
			label_chart22 = new JLabel();
			label_chart31 = new JLabel();
			label_chart32 = new JLabel();
			label_chart41 = new JLabel();
			label_chart42 = new JLabel();
			label_chart51 = new JLabel();

			JPanel panel_chart1 = new JPanel();
			panel_chart1
					.setLayout(new BoxLayout(panel_chart1, BoxLayout.X_AXIS));
			panel_charts.add(panel_chart1);

			panel_chart1.add(label_chart11);
			panel_chart1.add(label_chart12);

			JPanel panel_chart2 = new JPanel();
			panel_chart2
					.setLayout(new BoxLayout(panel_chart2, BoxLayout.X_AXIS));
			panel_charts.add(Box.createVerticalStrut(10));
			panel_charts.add(panel_chart2);

			panel_chart2.add(label_chart21);
			panel_chart2.add(label_chart22);

			JPanel panel_chart3 = new JPanel();
			panel_chart3
					.setLayout(new BoxLayout(panel_chart3, BoxLayout.X_AXIS));
			panel_charts.add(Box.createVerticalStrut(10));
			panel_charts.add(panel_chart3);

			panel_chart3.add(label_chart31);
			panel_chart3.add(label_chart32);

			JPanel panel_chart4 = new JPanel();
			panel_chart4
					.setLayout(new BoxLayout(panel_chart4, BoxLayout.X_AXIS));
			panel_charts.add(Box.createVerticalStrut(10));
			panel_charts.add(panel_chart4);

			panel_chart4.add(label_chart41);
			panel_chart4.add(label_chart42);

			JPanel panel_chart5 = new JPanel();
			panel_chart5
					.setLayout(new BoxLayout(panel_chart5, BoxLayout.X_AXIS));
			panel_charts.add(panel_chart5);

			panel_chart5.add(label_chart51);

			panel_resultList = new JPanel();
			panel_allTab = new JPanel();
			panel_allTab.setLayout(new BorderLayout(0, 0));
			tabbedPane.addTab("全体数据", null, panel_allTab, null);

			panel_resultTab.add(panel_resultList);

			button_gov.addActionListener(this);
			button_news.addActionListener(this);
			button_pa.addActionListener(this);
		}

		private void updateRowHeights() {
			for (int row = 0; row < tableResult.getRowCount(); row++) {
				int rowHeight = tableResult.getRowHeight();

				for (int column = 0; column < tableResult.getColumnCount(); column++) {
					Component comp = tableResult.prepareRenderer(
							tableResult.getCellRenderer(row, column), row,
							column);
					rowHeight = Math.max(rowHeight,
							comp.getPreferredSize().height);
				}

				tableResult.setRowHeight(row, rowHeight);
			}
		}

		private void getTableResult(String keyword, int type) {
			sqlop = new SQLop();
			sqlop.initialize();
			result = sqlop.search(keyword, type);
			sqlop.close();

			resultSize = result.size();
			tableResult = new JTable(new DefaultTableModel(resultSize, 1) {
				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					return false;
				}
			});
			tableResult.getTableHeader().setVisible(false);
			tableResult.setDefaultRenderer(Object.class,
					new ResultTableFiller());

			this.updateRowHeights();
			tableResult.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = tableResult.rowAtPoint(evt.getPoint());
					int col = tableResult.columnAtPoint(evt.getPoint());
					if (row >= 0 && col >= 0) {
						String url = result.get(row).get("url");
						// new DetailFrame(NLP.stringsummary(article), article)
						// .setVisible(true);
						try {
							java.net.URI uri = java.net.URI.create(url);
							// 获取当前系统桌面扩展
							java.awt.Desktop dp = java.awt.Desktop.getDesktop();
							// 判断系统桌面是否支持要执行的功能
							if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
								dp.browse(uri);// 获取系统默认浏览器打开链接
							}
						} catch (java.io.IOException e) {
							// 此为无法获取系统默认浏览器
							e.printStackTrace();
							String article = result.get(row).get("content");
							new DetailFrame(NLP.stringsummary(article), article)
									.setVisible(true);
						}
					}
				}
			});

			panel_resultList.setLayout(new BorderLayout(0, 0));
			JScrollPane scrollPane = new JScrollPane(tableResult);
			panel_resultList.add(scrollPane, BorderLayout.CENTER);
		}

		public void getResult(String keyword) {
			new Chart(keyword);
			this.keyword = keyword;
			sqlop = new SQLop();
			sqlop.initialize();
			resultAll = sqlop.getAll();
			sqlop.close();

			resultAllSize = resultAll.size();

			// 搜索结果表格
			getTableResult(keyword, 0);// 0: gov..data

			// 统计图表
			//Image img = Toolkit.getDefaultToolkit().createImage(".\\output\\patent_type.jpg");
			label_chart11.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\site.jpg")));
			label_chart12.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\year_gov.jpg")));
			label_chart21.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\journal.jpg")));
			label_chart22.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\year_paper.jpg")));
			label_chart31.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\news_source.jpg")));
			label_chart32.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\year_news.jpg")));
			label_chart41.setIcon(new ImageIcon(
					Toolkit.getDefaultToolkit().createImage(".\\output\\patent_applicant.jpg")));
			label_chart42.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\year_patent.jpg")));
			label_chart51.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(".\\output\\patent_type.jpg")));
			this.validate();
			// 所有数据
			tableModel = new DefaultTableModel(resultAllSize, 7) {
				@Override
				public boolean isCellEditable(int arg0, int arg1) {
					return false;
				}

				public String getColumnName(int n) {
					String columnNames[] = { "类型", "标题", "时间", "作者", "其他",
							"正文", "源地址" };
					return columnNames[n];
				}
			};

			for (int row = 0; row < resultAllSize; row++) {
				for (int column = 0; column < 7; column++) {
					String string = null;
					switch (column) {
					case 0:
						string = resultAll.get(row).get("type");
						break;
					case 1:
						string = resultAll.get(row).get("title");
						break;
					case 2:
						string = resultAll.get(row).get("time");
						break;
					case 3:
						string = resultAll.get(row).get("author");
						break;
					case 4:
						string = resultAll.get(row).get("other");
						break;
					case 5:
						string = resultAll.get(row).get("content");
						break;
					case 6:
						string = resultAll.get(row).get("url");
						break;
					default:
						break;
					}
					tableModel.setValueAt(string, row, column);
				}
			}

			tableAll = new JTable(tableModel);
			tableAll.getColumnModel().getColumn(0).setPreferredWidth(3);
			// tableAll.getColumnModel().getColumn(1).setPreferredWidth(30);
			// tableAll.getColumnModel().getColumn(2).setPreferredWidth(20);
			// tableAll.getColumnModel().getColumn(3).setPreferredWidth(20);
			// tableAll.getColumnModel().getColumn(4).setPreferredWidth(20);
			// tableAll.getColumnModel().getColumn(5).setPreferredWidth(200);
			// tableAll.getColumnModel().getColumn(6).setPreferredWidth(30);
			// tableAll.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			menu = new JPopupMenu();
			item = new JMenuItem("删除该行");
			menu.add(item);

			item.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame,
							"确定删除所选记录吗", "删除", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						String deleteTitle = tableModel.getValueAt(
								tableAll.getSelectedRow(), 1).toString();
						sqlop = new SQLop();
						sqlop.initialize();
						sqlop.removeRecord(deleteTitle);
						sqlop.close();
						tableModel.removeRow(tableAll.getSelectedRow());
					}
				}
			});

			tableAll.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (count == 0)
							count++;
					}

					if (e.getButton() == MouseEvent.BUTTON3) {// right key click
						if (count == 1) {
							menu.show(e.getComponent(), e.getX(), e.getY());
						}
						count = 0;
					}
				}
			});
			JScrollPane scrollPane_all = new JScrollPane(tableAll);
			panel_allTab.add(scrollPane_all, BorderLayout.CENTER);

		}

		class ResultTableFiller implements TableCellRenderer {

			@Override
			public Component getTableCellRendererComponent(JTable arg0,
					Object arg1, boolean arg2, boolean arg3, int row, int column) {
				textPane = new JTextPane();
				StyledDocument sd = getNewStyledDocument();
				insertDoc(sd, result.get(row).get("type"), "STYLE_type");
				insertDoc(sd, "  " + result.get(row).get("title").toString()
						+ "\n", "STYLE_title");
				if (result.get(row).get("time") != null)
					insertDoc(sd,
							result.get(row).get("time").toString() + "\t",
							"STYLE_author");
				insertDoc(sd, result.get(row).get("author").toString() + "\n",
						"STYLE_author");
				insertDoc(sd, result.get(row).get("url").toString() + "\n",
						"STYLE_url");
				insertDoc(
						sd,
						new NLP().stringsummary(result.get(row).get("content")
								.toString())
								+ "\n", "STYLE_text");
				textPane.setStyledDocument(sd);
				return textPane;
			}

		}

		public StyledDocument getNewStyledDocument() {

			return new DefaultStyledDocument();
		}

		public StyledDocument insertDoc(StyledDocument styledDoc,
				String content, String currentStyle) {
			if (content == null)
				content = "";
			try {
				int length = styledDoc.getLength();
				styledDoc.insertString(length, content,
						styleModel.getStyle(currentStyle));
			} catch (BadLocationException e) {
				System.err.println("BadLocationException: " + e);
			}
			return styledDoc;
		}

		private void iniStyleModel() {
			styleModel = new DefaultStyledDocument();
			createStyle("STYLE_url", styleModel, 16, false, false, true,
					Color.GRAY, Color.WHITE, "Times New Roman");
			createStyle("STYLE_title", styleModel, 20, true, false, false,
					Color.BLACK, Color.WHITE, "黑体");
			createStyle("STYLE_abstract", styleModel, 16, false, false, false,
					Color.BLACK, Color.WHITE, "宋体");
			createStyle("STYLE_author", styleModel, 16, false, false, false,
					Color.BLACK, Color.WHITE, "楷体");
			createStyle("STYLE_type", styleModel, 20, false, false, false,
					Color.WHITE, Color.BLACK, "黑体");
			createStyle("STYLE_text", styleModel, 15, true, false, false,
					Color.BLACK, Color.WHITE, "宋体");
		}

		public void createStyle(String style, StyledDocument doc, int size,
				boolean bold, boolean italic, boolean underline, Color color,
				Color bcolor, String fontName) {
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
			StyleConstants.setBackground(s, bcolor);// 背景颜色
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button_gov) {
				panel_resultList.removeAll();
				this.getTableResult(keyword, 0);
				panel_resultList.validate();
			} else if (e.getSource() == button_news) {
				panel_resultList.removeAll();
				this.getTableResult(keyword, 2);
				panel_resultList.validate();
			} else if (e.getSource() == button_pa) {
				panel_resultList.removeAll();
				this.getTableResult(keyword, 1);
				panel_resultList.validate();
			}
		}

	}
}
