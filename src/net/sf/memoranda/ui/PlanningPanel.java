package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.text.DateFormat;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.text.html.HTMLDocument;

import net.sf.memoranda.History;
import net.sf.memoranda.Note;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.CurrentNote;
import net.sf.memoranda.ui.htmleditor.HTMLEditor;
import net.sf.memoranda.util.Util;
import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.HTMLFileExport;
import net.sf.memoranda.util.HTMLFileImport;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Configuration;


/*$Id: EditorPanel.java,v 1.21 2006/06/28 22:58:31 alexeya Exp $*/
public class PlanningPanel extends JPanel {

	BorderLayout borderLayout1 = new BorderLayout();
	
	GridBagLayout gridLayout = new GridBagLayout();
	GridBagConstraints gconstraints = new GridBagConstraints();

	JPanel jpanel1 = new JPanel();
	
	JPanel jpanel2 = new JPanel();
	
	public HTMLEditor editor = null;

	JButton importB = new JButton();

	JButton exportB = new JButton();

	JButton redoB = new JButton();

	JButton copyB = new JButton();

	JButton historyBackB = new JButton();

	JToolBar editorToolBar = new JToolBar();

	JButton pasteB = new JButton();

	JButton historyForwardB = new JButton();

	JButton insDateB = new JButton();

	JButton insTimeB = new JButton();

	// JButton printB = new JButton();
	JButton undoB = new JButton();

	JButton cutB = new JButton();

	BorderLayout borderLayout2 = new BorderLayout();

	JToolBar titleBar = new JToolBar();
	
	JToolBar authorBar = new JToolBar();
	
	JToolBar dateBar = new JToolBar();

	JLabel titleLabel = new JLabel();
	
	JLabel authorLabel = new JLabel();
	
	JLabel dateLabel = new JLabel();

	public JTextField titleField = new JTextField();
	
	JTextField authorField = new JTextField();
	
	JButton newB = new JButton();

	JButton previewB = new JButton();

	DailyItemsPanel parentPanel = null;

	public PlanningPanel(DailyItemsPanel parent) {
        this.setPreferredSize(new Dimension(1000, 1000));
		try {
			parentPanel = parent;
			jbInit();
		} catch (Exception ex) {
			new ExceptionDialog(ex);
		}
	}

	public Action insertTimeAction = new AbstractAction(Local
			.getString("Insert current time"), new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
					.getResource("resources/icons/time.png"))) {
		public void actionPerformed(ActionEvent event) {
			insTimeB_actionPerformed(event);
		}
	};

	public Action insertDateAction = new AbstractAction(Local
			.getString("Insert current date"), new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
					.getResource("resources/icons/date.png"))) {
		public void actionPerformed(ActionEvent event) {
			insDateB_actionPerformed(event);
		}
	};

	/*
	 * public Action printAction = new AbstractAction( "Print", new
	 * ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/print.png"))) {
	 * public void actionPerformed(ActionEvent event) { doPrint(); } };
	 */

	public Action newAction = new AbstractAction(Local.getString("New note"),
			new ImageIcon(net.sf.memoranda.ui.AppFrame.class
					.getResource("resources/icons/filenew.png"))) {
		public void actionPerformed(ActionEvent event) {
			newB_actionPerformed(event);
		}
	};

	public Action exportAction = new AbstractAction(Local
			.getString("Export note to file"), new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
					.getResource("resources/icons/export.png"))) {
		public void actionPerformed(ActionEvent event) {
			exportB_actionPerformed(event);
		}
	};

	public Action importAction = new AbstractAction(Local
			.getString("Insert file"), new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
					.getResource("resources/icons/import.png"))) {
		public void actionPerformed(ActionEvent event) {
			importB_actionPerformed(event);
		}
	};

	public Action previewAction = new AbstractAction(Local
			.getString("Preview note in browser"), new ImageIcon(
			net.sf.memoranda.ui.AppFrame.class
					.getResource("resources/icons/preview.png"))) {
		public void actionPerformed(ActionEvent event) {
			previewB_actionPerformed(event);
		}
	};

	void jbInit() throws Exception {

		if (!Configuration.get("DISABLE_L10N").equals("yes")) {
			net.sf.memoranda.ui.htmleditor.util.Local.setMessages(Local
					.getMessages());
		}
		
		gconstraints.anchor = GridBagConstraints.NORTHWEST;
		gconstraints.fill = GridBagConstraints.BOTH;

		jpanel1.setLayout(gridLayout);
		titleBar.setLayout(gridLayout);
		editorToolBar.setLayout(gridLayout);
		
		editor = new HTMLEditor();
		
		titleLabel.setFont(new java.awt.Font("Dialog", 1, 10));
		titleLabel.setText(Local.getString("Program") + "  ");
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		titleBar.add(titleLabel, gconstraints);
		
		titleField.setText("Enter name of program here");
		gconstraints.gridx = 1;
		gconstraints.weightx = 1;
		titleBar.add(titleField, gconstraints);
		
		authorLabel.setFont(new java.awt.Font("Dialog", 1, 10));
		authorLabel.setText(Local.getString("Programmer") + "  ");
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		authorBar.add(authorLabel, gconstraints);
		
		authorField.setText("Your name");
		gconstraints.gridx = 1;
		authorBar.add(authorField, gconstraints);
		
		dateLabel.setFont(new java.awt.Font("Dialog", 1, 10));
		dateLabel.setText("Date: " + CurrentDate.get().getFullDateString());
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		dateBar.add(dateLabel, gconstraints);
		
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		titleBar.setFloatable(false);
		jpanel1.add(titleBar, gconstraints);
		
		gconstraints.gridy = 1;
		authorBar.setFloatable(false);
		jpanel1.add(authorBar, gconstraints);
		
		gconstraints.gridy = 2;
		dateBar.setFloatable(false);
		jpanel1.add(dateBar, gconstraints);
		
		//jpanel2.setLayout(gridLayout);
		
		this.setLayout(gridLayout);
		
		newB.setAction(newAction);
		newB.setMaximumSize(new Dimension(24, 24));
		newB.setMinimumSize(new Dimension(24, 24));
		newB.setPreferredSize(new Dimension(24, 24));
		newB.setRequestFocusEnabled(false);
		newB.setToolTipText(Local.getString("New note"));
		newB.setBorderPainted(false);
		newB.setFocusable(false);
		newB.setText("");

		importB.setAction(importAction);
		importB.setBorderPainted(false);
		importB.setFocusable(false);
		importB.setPreferredSize(new Dimension(24, 24));
		importB.setRequestFocusEnabled(false);
		importB.setToolTipText(Local.getString("Insert file"));
		importB.setMinimumSize(new Dimension(24, 24));
		importB.setMaximumSize(new Dimension(24, 24));
		importB.setText("");

		exportB.setAction(exportAction);
		exportB.setMaximumSize(new Dimension(24, 24));
		exportB.setMinimumSize(new Dimension(24, 24));
		exportB.setPreferredSize(new Dimension(24, 24));
		exportB.setRequestFocusEnabled(false);
		exportB.setToolTipText(Local.getString("Export note to file"));
		exportB.setBorderPainted(false);
		exportB.setFocusable(false);
		exportB.setText("");

		redoB.setAction(editor.redoAction);
		redoB.setMaximumSize(new Dimension(24, 24));
		redoB.setMinimumSize(new Dimension(24, 24));
		redoB.setPreferredSize(new Dimension(24, 24));
		redoB.setRequestFocusEnabled(false);
		redoB.setToolTipText(Local.getString("Redo"));
		redoB.setBorderPainted(false);
		redoB.setFocusable(false);
		redoB.setText("");

		copyB.setAction(editor.copyAction);
		copyB.setMaximumSize(new Dimension(24, 24));
		copyB.setMinimumSize(new Dimension(24, 24));
		copyB.setPreferredSize(new Dimension(24, 24));
		copyB.setRequestFocusEnabled(false);
		copyB.setToolTipText(Local.getString("Copy"));
		copyB.setBorderPainted(false);
		copyB.setFocusable(false);
		copyB.setText("");

		historyBackB.setAction(History.historyBackAction);
		historyBackB.setMaximumSize(new Dimension(24, 24));
		historyBackB.setMinimumSize(new Dimension(24, 24));
		historyBackB.setPreferredSize(new Dimension(24, 24));
		historyBackB.setRequestFocusEnabled(false);
		historyBackB.setToolTipText(Local.getString("History back"));
		historyBackB.setBorderPainted(false);
		historyBackB.setFocusable(false);
		historyBackB.setText("");

		historyForwardB.setAction(History.historyForwardAction);
		historyForwardB.setBorderPainted(false);
		historyForwardB.setFocusable(false);
		historyForwardB.setPreferredSize(new Dimension(24, 24));
		historyForwardB.setRequestFocusEnabled(false);
		historyForwardB.setToolTipText(Local.getString("History forward"));
		historyForwardB.setMinimumSize(new Dimension(24, 24));
		historyForwardB.setMaximumSize(new Dimension(24, 24));
		historyForwardB.setText("");

		pasteB.setAction(editor.pasteAction);
		pasteB.setMaximumSize(new Dimension(24, 24));
		pasteB.setMinimumSize(new Dimension(24, 24));
		pasteB.setPreferredSize(new Dimension(24, 24));
		pasteB.setRequestFocusEnabled(false);
		pasteB.setToolTipText(Local.getString("paste"));
		pasteB.setBorderPainted(false);
		pasteB.setFocusable(false);
		pasteB.setText("");

		insDateB.setAction(insertDateAction);
		insDateB.setBorderPainted(false);
		insDateB.setFocusable(false);
		insDateB.setPreferredSize(new Dimension(24, 24));
		insDateB.setRequestFocusEnabled(false);
		insDateB.setToolTipText(Local.getString("Insert current date"));
		insDateB.setMinimumSize(new Dimension(24, 24));
		insDateB.setMaximumSize(new Dimension(24, 24));
		insDateB.setText("");

		insTimeB.setAction(insertTimeAction);
		insTimeB.setMaximumSize(new Dimension(24, 24));
		insTimeB.setMinimumSize(new Dimension(24, 24));
		insTimeB.setPreferredSize(new Dimension(24, 24));
		insTimeB.setRequestFocusEnabled(false);
		insTimeB.setToolTipText(Local.getString("Insert current time"));
		insTimeB.setBorderPainted(false);
		insTimeB.setFocusable(false);
		insTimeB.setText("");

		undoB.setAction(editor.undoAction);
		undoB.setBorderPainted(false);
		undoB.setFocusable(false);
		undoB.setPreferredSize(new Dimension(24, 24));
		undoB.setRequestFocusEnabled(false);
		undoB.setToolTipText(Local.getString("Undo"));
		undoB.setMinimumSize(new Dimension(24, 24));
		undoB.setMaximumSize(new Dimension(24, 24));
		undoB.setText("");

		cutB.setAction(editor.cutAction);
		cutB.setBorderPainted(false);
		cutB.setFocusable(false);
		cutB.setPreferredSize(new Dimension(24, 24));
		cutB.setRequestFocusEnabled(false);
		cutB.setToolTipText(Local.getString("Cut"));
		cutB.setMinimumSize(new Dimension(24, 24));
		cutB.setMaximumSize(new Dimension(24, 24));
		cutB.setText("");

		previewB.setAction(previewAction);
		previewB.setBorderPainted(false);
		previewB.setFocusable(false);
		previewB.setPreferredSize(new Dimension(24, 24));
		previewB.setRequestFocusEnabled(false);
		previewB.setToolTipText(previewAction.getValue(Action.NAME).toString());
		previewB.setMinimumSize(new Dimension(24, 24));
		previewB.setMaximumSize(new Dimension(24, 24));
		previewB.setText("");

		/*
		 * printB.setAction(printAction); printB.setMaximumSize(new
		 * Dimension(24, 24)); printB.setMinimumSize(new Dimension(24, 24));
		 * printB.setPreferredSize(new Dimension(24, 24));
		 * printB.setRequestFocusEnabled(false);
		 * printB.setToolTipText(Local.getString("Print"));
		 * printB.setBorderPainted(false); printB.setFocusable(false);
		 * printB.setText("");
		 */

		/*editorToolBar.setFloatable(false);
		editor.editToolbar.setFloatable(false);
		//this.add(jpanel1, BorderLayout.CENTER);
		g.gridx = 0;
		g.gridy = 0;
		editorToolBar.add(newB, g);
		editorToolBar.addSeparator(new Dimension(8, 24));
		g.gridx = 1;
		editorToolBar.add(historyBackB, g);
		g.gridx = 2;
		editorToolBar.add(historyForwardB, g);
		g.gridx = 3;
		editorToolBar.addSeparator(new Dimension(8, 24));
		g.gridx = 4;
		editorToolBar.add(undoB, g);
		g.gridx = 5;
		editorToolBar.add(redoB, g);
		g.gridx = 6;
		editorToolBar.addSeparator(new Dimension(8, 24));
		editorToolBar.add(cutB, g);
		g.gridx = 7;
		editorToolBar.add(copyB, g);
		g.gridx = 8;
		editorToolBar.add(pasteB, g);
		g.gridx = 9;
		editorToolBar.addSeparator(new Dimension(8, 24));
		editorToolBar.add(insDateB, g);
		g.gridx = 10;
		editorToolBar.add(insTimeB, g);
		g.gridx = 11;
		editorToolBar.addSeparator(new Dimension(8, 24));
		editorToolBar.add(importB, g);
		g.gridx = 12;
		editorToolBar.add(exportB, g);
		g.gridx = 13;
		editorToolBar.addSeparator(new Dimension(8, 24));
		editorToolBar.add(previewB, g);
		//editorToolBar.add(printB, null);*/
		
		gconstraints.gridx = 0;
		gconstraints.gridy = 0;
		gconstraints.fill = GridBagConstraints.NONE;
		gconstraints.weightx = 0;
		gconstraints.weighty = 1;
		//jpanel2.add(editorToolBar, g);
		
		//jpanel1.add(editorToolBar, BorderLayout.NORTH);
		jpanel1.setPreferredSize(new Dimension(500,250));
		this.add(jpanel1, gconstraints);
		//g.gridy = 1;
		//jpanel2.setPreferredSize(new Dimension(8,100));
		//this.add(jpanel2, g);
		//g.gridy = 2;
		//editor.setPreferredSize(new Dimension(750, 500));
		//this.add(editor, g); 

		//titleBar.add(titleLabel, null);
		//titleBar.add(titleField, null);
		initCSS();
		//editor.editor.setAntiAlias(
		//Configuration.get("ANTIALIAS_TEXT").toString().equalsIgnoreCase("yes"));
		//editor.editor.enableInputMethods(false);
		//editor.editor.getInputContext().selectInputMethod(Locale.getDefault());
		titleField.addKeyListener(new KeyListener() {

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
			editor.editor.requestFocus();
				}
			}

			public void keyReleased(KeyEvent arg0) {
			}

			public void keyTyped(KeyEvent arg0) {
			}
		});
	}

	public void initCSS() {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				net.sf.memoranda.ui.EditorPanel.class
						.getResourceAsStream("resources/css/default.css")));
		String css = "";
		try {
			String str = br.readLine();
			while (str != null) {
				css = css + str + "\n";
				str = br.readLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		final String NORMAL_FONT = Configuration.get("NORMAL_FONT").toString();
		final String HEADER_FONT = Configuration.get("HEADER_FONT").toString();
		final String MONO_FONT = Configuration.get("MONO_FONT").toString();
		final String BASE_FONT_SIZE = Configuration.get("BASE_FONT_SIZE").toString();
		css = css.replaceAll("%NORMAL_FONT%",
				NORMAL_FONT.length() > 0 ? "\"" + NORMAL_FONT + "\""
				: "serif");
		css = css.replaceAll("%HEADER_FONT%",
				HEADER_FONT.length() > 0 ? "\"" + HEADER_FONT + "\""
				: "sans-serif");
		css = css.replaceAll("%MONO_FONT%", MONO_FONT.length() > 0 ? "\"" + MONO_FONT + "\""
				: "monospaced");
		css = css.replaceAll("%BASE_FONT_SIZE%",
				BASE_FONT_SIZE.length() > 0 ? BASE_FONT_SIZE : "16");		
		editor.setStyleSheet(new StringReader(css));
		String usercss = (String) Configuration.get("USER_CSS");
		if (usercss.length() > 0) {
			try {
				// DEBUG
				System.out.println("***[DEBUG] User css used: " + usercss);
				editor.setStyleSheet(new InputStreamReader(
						new java.io.FileInputStream(usercss)));
			} catch (Exception ex) {
				System.out.println("***[DEBUG] Failed to open: " + usercss);
				ex.printStackTrace();
			}
		}

	}

	void insDateB_actionPerformed(ActionEvent event) {
		editor.editor.replaceSelection(CurrentDate.get().getFullDateString());
	}

	void insTimeB_actionPerformed(ActionEvent event) {
		java.util.Date date = new java.util.Date();
		editor.editor.replaceSelection(DateFormat.getTimeInstance(
				DateFormat.SHORT, Local.getCurrentLocale()).format(date));
	}

	void exportB_actionPerformed(ActionEvent event) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Save in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.saveButtonText", Local.getString("Save"));
		UIManager.put("FileChooser.saveButtonToolTipText", Local
				.getString("Save selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local.getString("Export note"));
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser
				.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.XHTML));
		chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));
		// chooser.addChoosableFileFilter(new
		// AllFilesFilter(AllFilesFilter.RTF));
		String lastSel = (String) Context.get("LAST_SELECTED_EXPORT_FILE");
		if (lastSel != null) {
			chooser.setCurrentDirectory(new File(lastSel));
		}

		FileExportDialog dlg = new FileExportDialog(App.getFrame(), Local
				.getString("Export note"), chooser);
		String enc = (String) Context.get("EXPORT_FILE_ENCODING");
		if (enc != null) {
			dlg.encCB.setSelectedItem(enc);
		}
		String templ = (String) Context.get("EXPORT_TEMPLATE");
		if (templ != null) {
			dlg.templF.setText(templ);
		}
		String xhtml = (String) Context.get("EXPORT_XHTML");
		if ((xhtml != null) && (xhtml.equalsIgnoreCase("YES"))) {
			dlg.xhtmlChB.setSelected(true);
		}
		String num = (String) Context.get("EXPORT_NUMENT");
		if ((num != null) && (num.equalsIgnoreCase("YES"))) {
			dlg.numentChB.setSelected(true);
		}
		Dimension dlgSize = new Dimension(550, 475);
		dlg.setSize(dlgSize);
		Dimension frmSize = App.getFrame().getSize();
		Point loc = App.getFrame().getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setVisible(true);
		if (dlg.CANCELLED) {
			return;
		}

		Context.put("LAST_SELECTED_EXPORT_FILE", chooser.getSelectedFile()
				.getPath());
		Context.put("EXPORT_FILE_ENCODING", dlg.encCB.getSelectedItem());
		Context.put("EXPORT_NUMENT", dlg.numentChB.isSelected() ? "YES" : "NO");
		Context.put("EXPORT_XHTML", dlg.xhtmlChB.isSelected() ? "YES" : "NO");
		String template = null;
		if (dlg.usetemplChB.isSelected() && dlg.templF.getText().length() > 0) {
			template = dlg.templF.getText();
			Context.put("EXPORT_TEMPLATE", template);
		}
		/*
		 * if (chooser.getFileFilter().getDescription().equals("Rich Text
		 * Format")) new RTFFileExport(chooser.getSelectedFile(),
		 * editor.document); else
		 */
		int ei = dlg.encCB.getSelectedIndex();
		enc = null;
		if (ei == 1) {
			enc = "UTF-8";
		}
		File file = chooser.getSelectedFile();
		new HTMLFileExport(file, editor.document, CurrentNote.get(), enc,
				dlg.numentChB.isSelected(), template, dlg.xhtmlChB.isSelected());
	}

	String initialTitle = "";

	public void setDocument(Note note) {
		// Note note = CurrentProject.getNoteList().getActiveNote();
		// try {
		// this.editor.editor.setPage(CurrentStorage.get().getNoteURL(note));
		editor.document = (HTMLDocument) CurrentStorage.get().openNote(note);
		editor.initEditor();
		if (note != null) {
			titleField.setText(note.getTitle());
		} else {
			titleField.setText("");
		}
		initialTitle = titleField.getText();
		/*
		 * } catch (Exception ex) { new ExceptionDialog(ex); }
		 */
		/*
		 * Document doc = CurrentStorage.get().openNote(note); try {
		 * this.editor.editor.setText(doc.getText(0, doc.getLength())); } catch
		 * (Exception ex){ ex.printStackTrace(); }
		 */
		 // .setDocument(CurrentStorage.get().openNote(note));
	}

	public javax.swing.text.Document getDocument() {
		return this.editor.document;
	}

	public boolean isDocumentChanged() {
		return editor.isDocumentChanged()
				|| !titleField.getText().equals(initialTitle);
	}

	void importB_actionPerformed(ActionEvent event) {
		// Fix until Sun's JVM supports more locales...
		UIManager.put("FileChooser.lookInLabelText", Local
				.getString("Look in:"));
		UIManager.put("FileChooser.upFolderToolTipText", Local
				.getString("Up One Level"));
		UIManager.put("FileChooser.newFolderToolTipText", Local
				.getString("Create New Folder"));
		UIManager.put("FileChooser.listViewButtonToolTipText", Local
				.getString("List"));
		UIManager.put("FileChooser.detailsViewButtonToolTipText", Local
				.getString("Details"));
		UIManager.put("FileChooser.fileNameLabelText", Local
				.getString("File Name:"));
		UIManager.put("FileChooser.filesOfTypeLabelText", Local
				.getString("Files of Type:"));
		UIManager.put("FileChooser.openButtonText", Local.getString("Open"));
		UIManager.put("FileChooser.openButtonToolTipText", Local
				.getString("Open selected file"));
		UIManager
				.put("FileChooser.cancelButtonText", Local.getString("Cancel"));
		UIManager.put("FileChooser.cancelButtonToolTipText", Local
				.getString("Cancel"));

		JFileChooser chooser = new JFileChooser();
		chooser.setFileHidingEnabled(false);
		chooser.setDialogTitle(Local.getString("Insert file"));
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.addChoosableFileFilter(new AllFilesFilter(AllFilesFilter.HTML));
		chooser.setPreferredSize(new Dimension(550, 375));
		String lastSel = (String) Context.get("LAST_SELECTED_IMPORT_FILE");
		if (lastSel != null) {
			chooser.setCurrentDirectory(new java.io.File(lastSel));
		}
		if (chooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		Context.put("LAST_SELECTED_IMPORT_FILE", chooser.getSelectedFile()
				.getPath());

		File file = chooser.getSelectedFile();
		new HTMLFileImport(file, editor);
	}

	void newB_actionPerformed(ActionEvent event) {
		CurrentNote.set(null, true);
		setDocument(null);
		this.titleField.requestFocus();
	}

	void previewB_actionPerformed(ActionEvent event) {
		File file;
		try {
			file = Util.getTempFile();
			new HTMLFileExport(file, editor.document, CurrentNote.get(), "UTF-8",
					false, null, false);
			Util.runBrowser("file:" + file.getAbsolutePath());
		} catch (IOException ioe) {
			new ExceptionDialog(ioe, "Cannot create temporary file", null);
		}
	}
}