
 
//THE IMPORTED LIBRARIES
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.*;
import java.text.*;
import java.util.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import java.net.*;



public class SAP_Editor extends JFrame
{
	
	//ALL THE VARIABLES USED IN THIS APPLICATION
	JTextArea area = new JTextArea(0,0);
	JScrollPane scroller = new JScrollPane(area);
	
	public JPopupMenu pop = new JPopupMenu();
	
	JMenu menu = new JMenu("Edit");
	JMenuItem menuItem = new JMenuItem("copy");
	JMenuItem menucut = new JMenuItem("cut");
	
	JMenuItem menupaste = new JMenuItem("paste");
	
	JToolBar toolBar = new JToolBar();
	
	JButton newFile = new JButton(new ImageIcon("new.jpg"));
	JButton openFile = new JButton(new ImageIcon("open.jpg"));
	JButton saveFile = new JButton(new ImageIcon("save.jpg"));
	
	JButton cutFile = new JButton(new ImageIcon("cut.jpg"));
	JButton copyFile = new JButton(new ImageIcon("copy.jpg"));
	JButton pasteFile = new JButton(new ImageIcon("paste.jpg"));
	
	JMenuBar menuBar = new JMenuBar();
	
	JMenu FILE = new JMenu("File");
	JMenu EDIT = new JMenu("Edit");
	JMenu FORMAT = new JMenu("Format");
	

	JMenu Team = new JMenu("Team");
	
	JMenuItem NEWFILE = new JMenuItem("New", new ImageIcon("new.jpg"));
	JMenuItem OPENFILE = new JMenuItem("Open", new ImageIcon("open.jpg"));
	JMenuItem SAVEFILE = new JMenuItem("Save", new ImageIcon("save.jpg"));
	JMenuItem SAVEASFILE = new JMenuItem("Save As...");
	JMenuItem PRINTFILE = new JMenuItem("Print...");
	JMenuItem EXITFILE = new JMenuItem("Exit");
	
	JMenuItem UNDOEDIT = new JMenuItem("Undo");
	JMenuItem REDOEDIT = new JMenuItem("Redo");
	JMenuItem COPYEDIT = new JMenuItem("Copy");
	JMenuItem CUTEDIT = new JMenuItem("Cut");
	JMenuItem PASTEDIT = new JMenuItem("Paste");
	JMenuItem DELETEDIT = new JMenuItem("Delete");
	JMenuItem FINDEDIT = new JMenuItem("Find");
	JMenuItem FINDNEXTEDIT = new JMenuItem("Find Next");
	JMenuItem REPLACEDIT = new JMenuItem("Replace");
	
	JMenuItem SELECTEDIT = new JMenuItem("Select All");
	
	
	
	JMenuItem FONT = new JMenuItem("Font");
	JMenuItem FONT_Color1 = new JMenuItem("Font Color-BLUE");
	JMenuItem FONT_Color2 = new JMenuItem("Font Color-RED");
	JMenuItem FONT_Color3 = new JMenuItem("Font Color-GREEN");
	JMenuItem FONT_Color4 = new JMenuItem("Font Color-BLACK");
	
	
	
	JMenuItem ABOUT = new JMenuItem("About");
	
	String file = null;
	String fileN;
	
	boolean opened = false;
	
	JPanel statusPanel = new JPanel();
	
	JLabel statusLabel;
	
	JPanel aboutPanel = new JPanel();
	
	int ind = 0;
	
	StringBuffer sbufer;
	String findString;
	
	fontSelector fontS = new fontSelector();
	
	UndoManager undo = new UndoManager();
	UndoAction undoAction = new UndoAction();
	RedoAction redoAction = new RedoAction();
	
	public SAP_Editor()
	{
		//DEFAULT TITLE OF FRAME
		super("SAP Editor");
		
		//SETS THE SIZE
		this.setSize(800,600);
		
		//SETS THE LAYOUT				
		this.getContentPane().setLayout(new BorderLayout());
		
		
		
		//SETS FOCUS ON THE TEXTAREA
		area.requestFocus(true);
		
		//ADDS THE SCROLLPANE CONTAINING THE TEXTAREA TO THE CONTAINER
		this.getContentPane().add(scroller, BorderLayout.CENTER);
		
		//ADDS THE STATUSPANEL
		this.getContentPane().add(statusPanel, BorderLayout.SOUTH);
		
		//TO ENABLE DRAG MODE
		area.setDragEnabled(true);
		
		//SETS THE TOOLBAR TO BE STATIC I.E DISALLOW THE USER FROM CHANGING THE DOCKING POSITION		
		toolBar.setFloatable(false); 
		
		//ADD THE TOOLBAR
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//ADD A MOUSELISTENER TO RIGHT CLICK FOR THE POPUPLISTENER
		MouseListener popupListener = new PopupListener();
		area.addMouseListener(popupListener);
		
		//UNDO LISTENER ON AREA
		area.getDocument().addUndoableEditListener(new MyUndoableEditListener());
		
	
		//SETS THE MENUBAR
		FILE.add(NEWFILE);
		FILE.add(OPENFILE);
		FILE.add(SAVEFILE);
		FILE.add(SAVEASFILE);
		FILE.addSeparator();
		FILE.add(PRINTFILE);
		FILE.addSeparator();
		FILE.add(EXITFILE);
		
		EDIT.add(undoAction);
		EDIT.add(redoAction);
		EDIT.add(CUTEDIT);
		EDIT.add(COPYEDIT);
		EDIT.add(PASTEDIT);
		EDIT.add(DELETEDIT);
		EDIT.addSeparator();
		EDIT.add(FINDEDIT);
		EDIT.add(FINDNEXTEDIT);
		EDIT.add(REPLACEDIT);
		
		EDIT.addSeparator();
		EDIT.add(SELECTEDIT);
		
		
		
		FORMAT.add(FONT);
		FORMAT.add(FONT_Color1);
		FORMAT.add(FONT_Color2);
		FORMAT.add(FONT_Color3);
		FORMAT.add(FONT_Color4);
		
		
		
		Team.add(ABOUT);
				
		FILE.setMnemonic(KeyEvent.VK_F);
		menuBar.add(FILE);
		EDIT.setMnemonic(KeyEvent.VK_E);
		menuBar.add(EDIT);
		FORMAT.setMnemonic(KeyEvent.VK_T);
		menuBar.add(FORMAT);
		
		Team.setMnemonic(KeyEvent.VK_H);
		menuBar.add(Team);
		
		//ADD MENUBAR TO THE FRAME
		this.setJMenuBar(menuBar);
		
		//ADD ITEMS TO THE POPUP
		pop.add(undoAction);
		pop.add(redoAction);
		pop.addSeparator();
		pop.add(menuItem);
		pop.add(menucut);
		pop.add(menupaste);
		
		
		//VALIDATION
		newFile.setBorder(null);
		openFile.setBorder(null);
		saveFile.setBorder(null);
		cutFile.setBorder(null);
		copyFile.setBorder(null);
		pasteFile.setBorder(null);
		
		//ADD TO TOOLBAR
		toolBar.add(newFile);
		newFile.setToolTipText("New file");
		toolBar.addSeparator();
		toolBar.add(openFile); 
		openFile.setToolTipText("Open file");
		toolBar.add(saveFile);
		saveFile.setToolTipText("Save file");
		toolBar.addSeparator();
		toolBar.add(cutFile);
		cutFile.setToolTipText("Cut");
		toolBar.add(copyFile);
		copyFile.setToolTipText("Copy");
		toolBar.add(pasteFile);
		pasteFile.setToolTipText("Paste");
		
		
		//ACTION FOR NEW FILE ON THE TOOLBAR
		newFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opened = false;
				if(area.getText().equals(""))
				{
					System.out.print("text is empty");
				}
				else
				{
					int confirm = JOptionPane.showConfirmDialog(null,
					"Would you like to save?",
					"New File",
					JOptionPane.YES_NO_CANCEL_OPTION);
	
	 				if( confirm == JOptionPane.YES_OPTION )
	 				{
	 					saveFile();
						area.setText(null);
						statusPanel.removeAll();
						statusPanel.validate();
					}
					else
	 				if( confirm == JOptionPane.CANCEL_OPTION )
	 				{}
					else if( confirm == JOptionPane.NO_OPTION )
					{
						area.setText(null);
						statusPanel.removeAll();
						statusPanel.validate();
					}
				}
			}
		});
		
		////OPEN BUTTON ON THE TOOLBAR
		openFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		
		////SAVE BUTTON ON THE TOOLBAR
		saveFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});
		
		//ACTION FOR CUT BUTTON ON THE TOOLBAR
		cutFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.cut();
			}
		});
		
		//ACTION FOR COPY BUTTON ON THE TOOLBAR
		copyFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.copy();
			}
		});
		
		//ACTION FOR PASTE BUTTON ON THE TOOLBAR
		pasteFile.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.paste();
			}
		});
		
		
		//ACTIONLISTENER FOR OTHER ITEMS ON THE TOOLBAR
		//COPY BUTTON ON THE TOOLBAR
		menuItem.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.copy();
				menupaste.setEnabled(true);
				pasteFile.setEnabled(true);
				PASTEDIT.setEnabled(true);
			}
		});
		
		//CUT BUTTON ON THE TOOLBAR
		menucut.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.cut();
				menupaste.setEnabled(true);
				pasteFile.setEnabled(true);
				PASTEDIT.setEnabled(true);
			}
		});
		
		//PASTE BUTTON ON THE TOOLBAR
		menupaste.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.paste();
			}
		});
		
		
		
		//ACTIONLISTENER FOR ITEMS IN THE MENUBAR
		
		//OPEN A NEW FILE
		NEWFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		NEWFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opened = false;
				int confirm = JOptionPane.showConfirmDialog(null,
				"Would you like to save?",
				"New File",
				JOptionPane.YES_NO_CANCEL_OPTION);

 				if( confirm == JOptionPane.YES_OPTION )
 				{
 					saveFile();
					area.setText(null);
					statusPanel.removeAll();
					statusPanel.validate();
				}
				else
 				if( confirm == JOptionPane.CANCEL_OPTION )
 				{}
				else
				{
					area.setText(null);
					statusPanel.removeAll();
					statusPanel.validate();
				}
			}
		});
		
		//SAVE OPTION. HAS A VALIDATION CHECK THAT CHECKS WETHER ITS AN OPENED FILE OR NEW FILE
		SAVEFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		SAVEFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				saveFile();
			}
		});
		
		//OPTION THAT WILL BRING UP A DIALOG WHICH SAVES THE FILE WITH A NAME AND FORMAT DESIRED
		SAVEASFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				opened = false;
				saveFile();
			}
		});
		SELECTEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		SELECTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.selectAll();
			}
		});
		
		//PRINTS WHATEVER IS IN THE TEXT AREA
		PRINTFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		PRINTFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(area.getText().equals(""))
				JOptionPane.showMessageDialog(null, "Text Area is empty.");
				else
				print(createBuffer());
				
			}
		});
		
		
		
		
		//ACTION FOR OPEN BUTTON
		OPENFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		OPENFILE.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				openFile();
			}
		});
		
		//ACTION FOR CUT BUTTON
		CUTEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		CUTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.cut();
			}
		});
		
		//ACTION FOR COPY BUTTON
		COPYEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		COPYEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.copy();
			}
		});

		//ACTION FOR Blue Color BUTTON
		FONT_Color1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		FONT_Color1.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.setForeground(Color.blue);
			}
		});
		//ACTION FOR Red Color BUTTON
		FONT_Color2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		FONT_Color2.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.setForeground(Color.red);
			}
		});
		//ACTION FOR Green Color BUTTON
		FONT_Color3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		FONT_Color3.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.setForeground(Color.green);
			}
		});
		//ACTION FOR Black Color BUTTON
		FONT_Color4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, ActionEvent.CTRL_MASK));
		FONT_Color4.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.setForeground(Color.black);
			}
		});
		
		//ACTION FOR PASTE BUTTON
		PASTEDIT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		PASTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.paste();
			}
		});
		
		//FONT SELECTOR OPTION
		FONT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
		FONT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fontS.setVisible(true);
				fontS.okButton.addActionListener(new ActionListener ()
					{
						public void actionPerformed(ActionEvent ae)
						{
							Font selectedFont = fontS.returnFont();
							area.setFont(selectedFont);
							fontS.setVisible(false);
						}
					});
					
				fontS.cancelButton.addActionListener(new ActionListener ()
					{
						public void actionPerformed(ActionEvent ae)
						{
							fontS.setVisible(false);
						}
					});
			}
		});

		
		
		
		
		//FINDS A WORD IN THE SAP_Editor
		FINDEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					sbufer = new StringBuffer(area.getText());
					findString = JOptionPane.showInputDialog(null, "Find");
					ind = sbufer.indexOf(findString);
					area.setCaretPosition(ind);
					area.setSelectionStart(ind);
					area.setSelectionEnd(ind+findString.length());
				}
				catch(IllegalArgumentException npe)
				{
					JOptionPane.showMessageDialog(null, "String not found");
				}catch(NullPointerException nfe){}
			}
		});
		
		//FINDS A WORD IN THE SAP_Editor
		FINDNEXTEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					sbufer = new StringBuffer(area.getText());
					findString = JOptionPane.showInputDialog(null, "Find Next");
					ind = sbufer.indexOf(findString, area.getCaretPosition());
					area.setCaretPosition(ind);
					area.setSelectionStart(ind);
					area.setSelectionEnd(ind+findString.length());
				}
				catch(IllegalArgumentException npe)
				{
					JOptionPane.showMessageDialog(null, "String not found");
				}catch(NullPointerException nfe){}
			}
		});
		
		//EXITS THE APPLICATION AND CHECKS FOR ANY CHANGES MADE
		EXITFILE.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		EXITFILE.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent e)
            {
            	int confirm = JOptionPane.showConfirmDialog(null,
				"Would you like to save?",
				"Exit Application",
				JOptionPane.YES_NO_CANCEL_OPTION);

 				if( confirm == JOptionPane.YES_OPTION )
 				{
 					saveFile();
 					dispose();
 					System.exit(0);
 				}
 				else
 				if( confirm == JOptionPane.CANCEL_OPTION )
 				{}
 				else
 				{
 					dispose();
 					System.exit(0);
 				}
            }
        });
		
		
		//ACTION FOR REPLACE OPTION
		REPLACEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					String replace = JOptionPane.showInputDialog(null, "Replace");
					area.replaceSelection(replace);
				}catch(NumberFormatException nfe){}
			}
		});
		
		
		
		//ACTION FOR DELETE OPTION
		DELETEDIT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				area.replaceSelection(null);
			}
		});
		
		
		
		//ABOUT
		ABOUT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
		ABOUT.addActionListener( new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				URL img = getClass().getResource("logo.jpg");
				String imagesrc = "<img src=\"" + img + "\" width=\"323\" height=\"185\">";
	    		String message = "Text Editor- IIIT Naya Raipur Sem-3 Mini Project\n"+
 									 "By Sourav Kunal , Arijit Saha, Piyush Yadav\n"+
 									 "Version 1.01\n"+
	    							 "Under the guidance of Prof.OP Vyas & Mr.Nachiket Tapas\n"+
									 "Copyright under the name of SAP Editor.\n"+
									 "If you have any enquiries please do not hesitate to email us\n"+
									 "arijit15100@iiitnr.edu.in";
				JOptionPane.showMessageDialog(aboutPanel,"<html><center>" + imagesrc + "<br>" + message);
			}
		});
		
		
		//CLOSES THE WINDOW WHEN THE CLOSE BUTTON IS PRESSED
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null,
				"Would you like to save?",
				"Exit Application",
				JOptionPane.YES_NO_CANCEL_OPTION);

 				if( confirm == JOptionPane.YES_OPTION )
 				{
 					saveFile();
 					dispose();
 					System.exit(0);
 				}
 				else
 				if( confirm == JOptionPane.CANCEL_OPTION )
 				{
 					
 				}
 				else
 				{
 					dispose();
 					System.exit(0);
 				}
            }
        });
						
	}
	
	//MAIN FUNCTION.
	public static void main(String args[])
	{
		SAP_Editor l = new SAP_Editor();
		l.setVisible(true);
	}
	
	//FUNCTION CALLED BY THE SAVE BUTTON
	public void saveFile()
	{
		String line = area.getText();
				if(opened==true)
				{
					try
					{
						FileWriter output = new FileWriter(file);
						BufferedWriter bufout = new BufferedWriter(output);
						bufout.write(line, 0, line.length());
						JOptionPane.showMessageDialog(null, "Save Successful");
						bufout.close();
						output.close();
					}catch(IOException ioe){ioe.printStackTrace();}
				}
				else
				{
					JFileChooser fc = new JFileChooser();
					int result = fc.showSaveDialog(new JPanel());
					
					if(result == JFileChooser.APPROVE_OPTION) 
					{
				       fileN = String.valueOf(fc.getSelectedFile());
				    
						try
						{
							FileWriter output = new FileWriter(fileN);
							BufferedWriter bufout = new BufferedWriter(output);
							bufout.write(line, 0, line.length());
							JOptionPane.showMessageDialog(null, "Save Successful");
							bufout.close();
							output.close();
							opened = true;
						}catch(IOException ioe){ioe.printStackTrace();}
					}
				}
	}
	
	//PRINT FUNCTION
	public String createBuffer()
	{
		String buffer;
		buffer = area.getText();
		return buffer;
	}

    private void print(String s)
	{
		StringReader sr = new StringReader(s);
		LineNumberReader lnr = new LineNumberReader(sr);
		Font typeface = new Font("Monospaced", Font.PLAIN, 12);
		Properties p = new Properties();
		PrintJob pjob = getToolkit().getPrintJob(this, "Print report", p);

		if (pjob != null) {
			Graphics pg = pjob.getGraphics();
			if (pg != null) {
				FontMetrics fm = pg.getFontMetrics(typeface);
				int margin = 20;
				int pageHeight = pjob.getPageDimension().height - margin;
    			int fontHeight = fm.getHeight();
    			int fontDescent = fm.getDescent();
    			int curHeight = margin;

				String nextLine;
				pg.setFont (area.getFont());

				try
				{
					do
					{
						nextLine = lnr.readLine();
						if (nextLine != null) {
							if ((curHeight + fontHeight) > pageHeight)
							{ // New Page
								pg.dispose();
								pg = pjob.getGraphics();
								curHeight = margin;
							}

							curHeight += fontHeight;

							if (pg != null)
							{
								pg.setFont (typeface);
								pg.drawString (nextLine, margin, curHeight - fontDescent);
							}
						}
					}
					while (nextLine != null);

				}
				catch (EOFException eof)
				{
				}
				catch (Throwable t)
				{
					t.printStackTrace();
				}
			}
			pg.dispose();
		}
		if (pjob != null)
			pjob.end();
	}
	
	//FUNCTION TO OPEN THE FILE
	public void openFile()
	{
				statusPanel.removeAll();
				statusPanel.validate();
				area.setText(null);
				JFileChooser fc = new JFileChooser();
				int result = fc.showOpenDialog(new JPanel());
				
				if(result == JFileChooser.APPROVE_OPTION) 
				{
					String file = String.valueOf(fc.getSelectedFile());
					//String dirn = fc.getDirectory();
					
					File fil = new File(file);
					newFile.setEnabled(false);
	
					//START THIS THREAD WHILE READING FILE
					Thread loader = new FileLoader(fil, area.getDocument());
					loader.start();
					statusPanel.removeAll();
					statusPanel.revalidate();
				}else{}
	}
	

//CLASS FOR THE MOUSELIsTENER
class PopupListener extends MouseAdapter {
   
    public void mousePressed(MouseEvent e) {
    	    	
    	maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            pop.show(e.getComponent(),
                       e.getX(), e.getY());
        }
    }
}

//CLASS FOR UNDOLISTENER
public class MyUndoableEditListener
          implements UndoableEditListener
{
    public void undoableEditHappened(UndoableEditEvent e) {
        //Remember the edit and update the menus
        undo.addEdit(e.getEdit());
        undoAction.update();
        redoAction.update();
    }
}  



    /**
     * Thread to load a file into the text storage model
     */
class FileLoader extends Thread {
	
	JLabel state;

	FileLoader(File f, Document doc) {
	    setPriority(4);
	    this.f = f;
	    this.doc = doc;
	}

        public void run() {
	    try {
		// initialize the statusbar
		statusPanel.removeAll();
		JProgressBar progress = new JProgressBar();
		progress.setMinimum(0);
		progress.setMaximum((int) f.length());
		statusPanel.add(new JLabel("opened so far "));
		statusPanel.add(progress);
		statusPanel.revalidate();

		// try to start reading
		Reader in = new FileReader(f);
		char[] buff = new char[4096];
		int nch;
	    while ((nch = in.read(buff, 0, buff.length)) != -1) {
		    doc.insertString(doc.getLength(), new String(buff, 0, nch), null);
		    progress.setValue(progress.getValue() + nch);
		}

		// we are done... get rid of progressbar
		statusPanel.removeAll();
		statusPanel.revalidate();

	    }
	    catch (IOException e) {
		System.err.println(e.toString());
	    }
	    catch (BadLocationException e) {
		System.err.println(e.getMessage());
	    }
		newFile.setEnabled(true);
	}
	Document doc;
	File f;
}


//UNDO AND REDO ACTION CLASSES

class UndoAction extends AbstractAction {
	public UndoAction() {
	    super("Undo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    try {
		undo.undo();
	    } catch (CannotUndoException ex) {
		System.out.println("Unable to undo: " + ex);
		ex.printStackTrace();
	    }
	    update();
	    redoAction.update();
	}

	protected void update() {
	    if(undo.canUndo()) {
		setEnabled(true);
		putValue("Undo", undo.getUndoPresentationName());
	    }
	    else {
		setEnabled(false);
		putValue(Action.NAME, "Undo");
	    }
	}
    }

class RedoAction extends AbstractAction {
	public RedoAction() {
	    super("Redo");
	    setEnabled(false);
	}

	public void actionPerformed(ActionEvent e) {
	    try {
		undo.redo();
	    } catch (CannotRedoException ex) {
		System.out.println("Unable to redo: " + ex);
		ex.printStackTrace();
	    }
	    update();
	    undoAction.update();
	}

	protected void update() {
	    if(undo.canRedo()) {
		setEnabled(true);
		putValue("Redo", undo.getRedoPresentationName());
	    }
	    else {
		setEnabled(false);
		putValue(Action.NAME, "Redo");
	    }
	}
    }
    

//END MAIN CLASS
}
