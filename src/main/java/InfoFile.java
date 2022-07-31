
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("all")

public class InfoFile extends javax.swing.JFrame implements ActionListener, ChangeListener {

	LinkedList<String> listaDatos;

	private JTextField file;

	public Color color = new Color(240, 240, 240);

	JLabel contador;

	JLabel preview;

	int indice;

	JButton btnNewButton;

	JButton btnNewButton_2;

	JButton btnNewButton_3;

	JButton back;

	JButton next;

	Point mouseClickPoint;

	public void setColor(Color color) {

		getContentPane().setBackground(color);

		btnNewButton.setBackground(color);

		btnNewButton_2.setBackground(color);

		btnNewButton_3.setBackground(color);

		back.setBackground(color);

		next.setBackground(color);

	}

	private void rueda(MouseWheelEvent e) {

		if (e.getWheelRotation() < 0) {

			adelante();

		}

		else {

			atras();

		}

	}

	public InfoFile(List<String> lista) {

		addMouseListener(new MouseAdapter() {

			@Override

			public void mousePressed(MouseEvent e) {

				mouseClickPoint = e.getPoint();

			}

		});

		addMouseMotionListener(new MouseAdapter() {

			@Override
			public void mouseDragged(MouseEvent e) {

				Point newPoint = e.getLocationOnScreen();

				newPoint.translate(-mouseClickPoint.x, -mouseClickPoint.y); // Moves the point by given values from its
																			// location
				setLocation(newPoint); // set the new location

			}

		});

		if (lista.isEmpty()) {

			System.exit(0);

		}

		this.listaDatos = (LinkedList<String>) lista;

		setIconImage(Toolkit.getDefaultToolkit().getImage(InfoFile.class.getResource("/images/info.png")));

		getContentPane().setBackground(this.color);

		setTitle("InfoFile");

		initComponents();

		setUndecorated(true);

		setShape(new RoundRectangle2D.Double(0, 0, 498, 150, 50, 50));

	}

	private void adelante() {

		indice++;

		if (indice >= listaDatos.size()) {

			indice = listaDatos.size();

			indice--;

		}

		previsualizar(listaDatos.get(indice));

	}

	private void atras() {

		if (indice > 0) {

			indice--;

		}

		previsualizar(listaDatos.get(indice));

	}

	private static Clipboard getSystemClipboard() {

		Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

		Clipboard systemClipboard = defaultToolkit.getSystemClipboard();

		return systemClipboard;
	}

	public static void copy(String text) {

		Clipboard clipboard = getSystemClipboard();

		clipboard.setContents(new StringSelection(text), null);

	}

	public static String saberSeparador() {

		if (System.getProperty("os.name").contains("indows")) {

			return "\\";

		}

		else {

			return "/";

		}

	}

	public void verImagen(int index) {

		this.indice = 0;

		if (index < listaDatos.size()) {

			this.indice = index;

			previsualizar(listaDatos.get(index));

		}

		else {

			previsualizar(listaDatos.get(0));

		}

	}

	private void abrir(boolean archivo) {

		String ruta = file.getText();

		if (!ruta.isEmpty()) {

			try {

				if (archivo) {

					ruta = file.getText().substring(0, file.getText().lastIndexOf(saberSeparador()));

				}

				if (System.getProperty("os.name").contains("indows")) {

					Runtime.getRuntime().exec("cmd /c C:\\Windows\\explorer.exe " + "\"" + ruta + "\"");

				}

				if (System.getProperty("os.name").contains("inux")) {

					Runtime.getRuntime().exec("xdg-open " + ruta);

				}

				if (System.getProperty("os.name").contains("ac")) {

					Runtime.getRuntime().exec("open " + ruta);

				}

			}

			catch (Exception e) {

			}

		}

	}

	private void initComponents() {

		indice = 0;

		JButton btnNewButton_1 = new JButton("");

		contador = new JLabel("New label");

		contador.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		preview = new JLabel("");

		preview.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		setResizable(false);

		Date myDate = new Date();

		btnNewButton_1.setBorder(null);

		btnNewButton_1.setIcon(new ImageIcon(InfoFile.class.getResource("/images/close.png")));

		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();

			}

		});

		JButton btnNewButton_1_1 = new JButton("");

		btnNewButton_1_1.setBorder(null);

		btnNewButton_1_1.setFocusPainted(false);

		btnNewButton_1_1.setIcon(new ImageIcon(InfoFile.class.getResource("/images/min.png")));

		btnNewButton_1_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				setState(JFrame.ICONIFIED);

			}

		});

		file = new JTextField();

		file.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		file.setFont(new Font("Tahoma", Font.PLAIN, 14));

		file.setColumns(10);

		btnNewButton = new JButton("Copy");

		btnNewButton.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				copy(file.getText());

			}

		});

		btnNewButton.setFocusPainted(false);

		btnNewButton.setBorder(null);

		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnNewButton.setBackground(color);

		btnNewButton.setIcon(new ImageIcon(InfoFile.class.getResource("/images/copy.png")));

		btnNewButton_2 = new JButton("Open");

		btnNewButton_2.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		btnNewButton_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				abrir(true);

			}

		});

		btnNewButton_2.setFocusPainted(false);

		btnNewButton_2.setBorder(null);

		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnNewButton_2.setBackground(color);

		btnNewButton_2.setIcon(new ImageIcon(InfoFile.class.getResource("/images/abrir.png")));

		btnNewButton_3 = new JButton("View");

		btnNewButton_3.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		btnNewButton_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				abrir(false);

			}

		});

		btnNewButton_3.setFocusPainted(false);

		btnNewButton_3.setBorder(null);

		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));

		btnNewButton_3.setBackground(color);

		btnNewButton_3.setIcon(new ImageIcon(InfoFile.class.getResource("/images/view.png")));

		back = new JButton("");

		back.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				atras();

			}

		});

		back.setFocusPainted(false);

		back.setBorder(null);

		back.setIcon(new ImageIcon(InfoFile.class.getResource("/images/back.png")));

		back.setBackground(color);

		next = new JButton("");

		next.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {

				rueda(e);

			}

		});

		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				adelante();

			}

		});

		next.setFocusPainted(false);

		next.setBorder(null);

		next.setIcon(new ImageIcon(InfoFile.class.getResource("/images/next.png")));

		next.setBackground(color);

		preview.setHorizontalAlignment(SwingConstants.CENTER);

		file.setText(listaDatos.get(0));

		contador.setText("1 / 1");

		contador.setFont(new Font("Tahoma", Font.PLAIN, 16));

		if (listaDatos.size() == 1) {

			back.setEnabled(false);

			next.setEnabled(false);

		}

		previsualizar(listaDatos.get(0));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
								.addComponent(back, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(next, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(preview, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton).addGap(4)
								.addComponent(btnNewButton_2).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton_3).addGap(12))
								.addGroup(layout.createSequentialGroup()
										.addComponent(contador, GroupLayout.PREFERRED_SIZE, 247,
												GroupLayout.PREFERRED_SIZE)
										.addGap(154)
										.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 24,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(25))
				.addGroup(layout.createSequentialGroup()
						.addComponent(file, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(18, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(contador, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnNewButton_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(btnNewButton_1_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
												25, GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(file, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
								.createParallelGroup(Alignment.LEADING, false)
								.addGroup(layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 36,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
								.addComponent(back, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
								.addComponent(next, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
								.addComponent(preview, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		getContentPane().setLayout(layout);

		setSize(new Dimension(498, 150));

		setLocationRelativeTo(null);

	}

	private void previsualizar(String string) {

		try {

			int index = indice;

			index++;

			contador.setText(index + " / " + listaDatos.size());

			file.setText(string);

			BufferedImage image = ImageIO.read(new File(string));

			Image resizedImage = image.getScaledInstance(150, 60, 0);

			preview.setIcon(new ImageIcon(resizedImage));

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

	public void stateChanged(ChangeEvent e) {

	}

	public void actionPerformed(ActionEvent e) {

	}

}
