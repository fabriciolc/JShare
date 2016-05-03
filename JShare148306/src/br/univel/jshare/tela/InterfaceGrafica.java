package br.univel.jshare.tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JEditorPane;
import java.awt.GridBagLayout;
import javax.swing.JLayeredPane;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTable;

public class InterfaceGrafica extends JFrame implements IServer{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGrafica frame = new InterfaceGrafica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfaceGrafica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		tabbedPane.addTab("Cliente", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{29, 100, 61, 82, 0, 0, 0, 35, 40, 52, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 47, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblEndereoIp = new JLabel("Endere\u00E7o IP:");
		GridBagConstraints gbc_lblEndereoIp = new GridBagConstraints();
		gbc_lblEndereoIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereoIp.anchor = GridBagConstraints.WEST;
		gbc_lblEndereoIp.gridx = 1;
		gbc_lblEndereoIp.gridy = 1;
		panel.add(lblEndereoIp, gbc_lblEndereoIp);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblPorta_1 = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta_1 = new GridBagConstraints();
		gbc_lblPorta_1.anchor = GridBagConstraints.EAST;
		gbc_lblPorta_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta_1.gridx = 6;
		gbc_lblPorta_1.gridy = 1;
		panel.add(lblPorta_1, gbc_lblPorta_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 7;
		gbc_textField_1.gridy = 1;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnConectar = new JButton("Conectar");
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 9;
		gbc_btnConectar.gridy = 1;
		panel.add(btnConectar, gbc_btnConectar);
		
		JLabel lblListaDeArquivos = new JLabel("Arquivos:");
		GridBagConstraints gbc_lblListaDeArquivos = new GridBagConstraints();
		gbc_lblListaDeArquivos.anchor = GridBagConstraints.WEST;
		gbc_lblListaDeArquivos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaDeArquivos.gridx = 1;
		gbc_lblListaDeArquivos.gridy = 3;
		panel.add(lblListaDeArquivos, gbc_lblListaDeArquivos);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 4;
		gbc_table.gridwidth = 8;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 4;
		panel.add(table, gbc_table);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.anchor = GridBagConstraints.WEST;
		gbc_btnDownload.gridwidth = 2;
		gbc_btnDownload.gridx = 9;
		gbc_btnDownload.gridy = 7;
		panel.add(btnDownload, gbc_btnDownload);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Servidor", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 93, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblIpLocal = new JLabel("IP Local:");
		GridBagConstraints gbc_lblIpLocal = new GridBagConstraints();
		gbc_lblIpLocal.anchor = GridBagConstraints.WEST;
		gbc_lblIpLocal.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpLocal.gridx = 1;
		gbc_lblIpLocal.gridy = 1;
		panel_1.add(lblIpLocal, gbc_lblIpLocal);
		
		JLabel lblPorta = new JLabel("Porta: ");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 4;
		gbc_lblPorta.gridy = 1;
		panel_1.add(lblPorta, gbc_lblPorta);
		
		JLabel lblPastaCompartilhada = new JLabel("Pasta Compartilhada");
		GridBagConstraints gbc_lblPastaCompartilhada = new GridBagConstraints();
		gbc_lblPastaCompartilhada.insets = new Insets(0, 0, 5, 5);
		gbc_lblPastaCompartilhada.anchor = GridBagConstraints.WEST;
		gbc_lblPastaCompartilhada.gridx = 1;
		gbc_lblPastaCompartilhada.gridy = 2;
		panel_1.add(lblPastaCompartilhada, gbc_lblPastaCompartilhada);
		
		JButton btnIniciarServio = new JButton("Iniciar Servi\u00E7o");
		btnIniciarServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnIniciarServio = new GridBagConstraints();
		gbc_btnIniciarServio.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciarServio.gridx = 8;
		gbc_btnIniciarServio.gridy = 2;
		panel_1.add(btnIniciarServio, gbc_btnIniciarServio);
		
		JButton btnParaServio = new JButton("Para Servi\u00E7o");
		GridBagConstraints gbc_btnParaServio = new GridBagConstraints();
		gbc_btnParaServio.insets = new Insets(0, 0, 5, 0);
		gbc_btnParaServio.gridx = 9;
		gbc_btnParaServio.gridy = 2;
		panel_1.add(btnParaServio, gbc_btnParaServio);
		
		JButton button = new JButton("...");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.WEST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 3;
		panel_1.add(button, gbc_button);
		
		JLabel lblPessoasConectadas = new JLabel("Pessoas Conectadas:");
		GridBagConstraints gbc_lblPessoasConectadas = new GridBagConstraints();
		gbc_lblPessoasConectadas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPessoasConectadas.gridx = 1;
		gbc_lblPessoasConectadas.gridy = 6;
		panel_1.add(lblPessoasConectadas, gbc_lblPessoasConectadas);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 7;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 1;
		gbc_list.gridy = 7;
		panel_1.add(list, gbc_list);
	}
	
	
	private Cliente cliente;
	
	private IServer servidor;
	
	private Registry registry;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
	protected void iniciarServico(){
		try {
			servidor = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registry = LocateRegistry.createRegistry(1818);
			registry.rebind(IServer.NOME_SERVICO, servidor);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void conectar(){
		
	}
	

}
