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
import br.univel.server.Server;

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
import java.net.InetAddress;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
		gbl_panel.columnWidths = new int[]{29, 69, 40, 82, 0, 0, 0, 35, 40, 52, 0, 0};
		gbl_panel.rowHeights = new int[]{24, 20, 17, 0, 47, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		
		JLabel lblIpLocal_1 = new JLabel("IP Local:");
		GridBagConstraints gbc_lblIpLocal_1 = new GridBagConstraints();
		gbc_lblIpLocal_1.anchor = GridBagConstraints.WEST;
		gbc_lblIpLocal_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblIpLocal_1.gridx = 1;
		gbc_lblIpLocal_1.gridy = 0;
		panel.add(lblIpLocal_1, gbc_lblIpLocal_1);
		
		JLabel labelIP = new JLabel("");
		GridBagConstraints gbc_labelIP = new GridBagConstraints();
		gbc_labelIP.insets = new Insets(0, 0, 5, 5);
		gbc_labelIP.gridx = 2;
		gbc_labelIP.gridy = 0;
		panel.add(labelIP, gbc_labelIP);
		
		JLabel lblNome = new JLabel("Nome: ");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 1;
		panel.add(lblNome, gbc_lblNome);
		
		txtNome = new JTextField();
		GridBagConstraints gbc_txtNome = new GridBagConstraints();
		gbc_txtNome.gridwidth = 4;
		gbc_txtNome.insets = new Insets(0, 0, 5, 5);
		gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNome.gridx = 2;
		gbc_txtNome.gridy = 1;
		panel.add(txtNome, gbc_txtNome);
		txtNome.setColumns(10);
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 9;
		gbc_btnConectar.gridy = 1;
		panel.add(btnConectar, gbc_btnConectar);
		
		btnDesconectar = new JButton("Desconectar");
		btnDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				desconectar();
			}
		});
		
		JLabel lblEndereoIp = new JLabel("Endere\u00E7o IP:");
		GridBagConstraints gbc_lblEndereoIp = new GridBagConstraints();
		gbc_lblEndereoIp.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndereoIp.anchor = GridBagConstraints.WEST;
		gbc_lblEndereoIp.gridx = 1;
		gbc_lblEndereoIp.gridy = 2;
		panel.add(lblEndereoIp, gbc_lblEndereoIp);
		
		txtIP = new JTextField();
		GridBagConstraints gbc_txtIP = new GridBagConstraints();
		gbc_txtIP.gridwidth = 4;
		gbc_txtIP.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtIP.insets = new Insets(0, 0, 5, 5);
		gbc_txtIP.gridx = 2;
		gbc_txtIP.gridy = 2;
		panel.add(txtIP, gbc_txtIP);
		txtIP.setColumns(10);
		
		JLabel lblPorta_1 = new JLabel("Porta:");
		GridBagConstraints gbc_lblPorta_1 = new GridBagConstraints();
		gbc_lblPorta_1.anchor = GridBagConstraints.EAST;
		gbc_lblPorta_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta_1.gridx = 6;
		gbc_lblPorta_1.gridy = 2;
		panel.add(lblPorta_1, gbc_lblPorta_1);
		
		txtPorta = new JTextField();
		GridBagConstraints gbc_txtPorta = new GridBagConstraints();
		gbc_txtPorta.insets = new Insets(0, 0, 5, 5);
		gbc_txtPorta.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPorta.gridx = 7;
		gbc_txtPorta.gridy = 2;
		panel.add(txtPorta, gbc_txtPorta);
		txtPorta.setColumns(10);
		GridBagConstraints gbc_btnDesconectar = new GridBagConstraints();
		gbc_btnDesconectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDesconectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnDesconectar.gridx = 9;
		gbc_btnDesconectar.gridy = 2;
		panel.add(btnDesconectar, gbc_btnDesconectar);
		
		JLabel lblListaDeArquivos = new JLabel("Arquivos:");
		GridBagConstraints gbc_lblListaDeArquivos = new GridBagConstraints();
		gbc_lblListaDeArquivos.anchor = GridBagConstraints.WEST;
		gbc_lblListaDeArquivos.insets = new Insets(0, 0, 5, 5);
		gbc_lblListaDeArquivos.gridx = 1;
		gbc_lblListaDeArquivos.gridy = 5;
		panel.add(lblListaDeArquivos, gbc_lblListaDeArquivos);
		
		table = new JTable();
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 4;
		gbc_table.gridwidth = 8;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 6;
		panel.add(table, gbc_table);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.insets = new Insets(0, 0, 5, 0);
		gbc_btnDownload.anchor = GridBagConstraints.WEST;
		gbc_btnDownload.gridwidth = 2;
		gbc_btnDownload.gridx = 9;
		gbc_btnDownload.gridy = 6;
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
				iniciarServico();
			}
		});
		GridBagConstraints gbc_btnIniciarServio = new GridBagConstraints();
		gbc_btnIniciarServio.insets = new Insets(0, 0, 5, 5);
		gbc_btnIniciarServio.gridx = 8;
		gbc_btnIniciarServio.gridy = 2;
		panel_1.add(btnIniciarServio, gbc_btnIniciarServio);
		
		JButton btnParaServio = new JButton("Para Servi\u00E7o");
		btnParaServio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pararServico();
			}
		});
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
		
		
		labelIP.setText(pegarIPLocal());
	}
	



	private String pegarIPLocal() {
		String ip = "N�o foi possivel encontrar nem um IP Local";
		try {
           ip = InetAddress.getLocalHost().getHostAddress();
         return ip;
        } catch (Exception e1) {
        	e1.printStackTrace();
        }
		return ip;
		
	}


	Cliente c = new Cliente();

	private Cliente cliente;
	
	private IServer servidor;
	
	private Registry registry;
	
	
	private JTextField txtIP;
	private JTextField txtPorta;
	private JTable table;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JTextField txtNome;

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
		Server.getInstance().iniciarServico();
	}
	protected void pararServico(){
		Server.getInstance().pararServico();
	}
	
	protected void conectar(){
		
		String nome = txtNome.getText().trim();
		if (nome.length() == 0) {
			JOptionPane.showMessageDialog(this, "Você precisa digitar um nome!");
			return;
		}

		
		String host = txtIP.getText().trim();
		if (!host.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}")) {
			JOptionPane.showMessageDialog(this, "O endereço ip parece inválido!");
			return;
		}
		String strPorta = txtPorta.getText().trim();
		if (!strPorta.matches("[0-9]+") || strPorta.length() > 5) {
			JOptionPane.showMessageDialog(this, "A porta deve ser um valor numérico de no máximo 5 dígitos!");
			return;
		}
		int intPorta = Integer.parseInt(strPorta);
		
		
		
		c.setNome(nome);
		c.setIp(pegarIPLocal());
		c.setPorta(1818);
		Server.getInstance().conectar(host, intPorta, c);
		txtIP.setEnabled(false);
		txtPorta.setEnabled(false);
		btnConectar.setEnabled(false);
		
	}
	protected void desconectar() {
		try {
			Server.getInstance().desconectar(c);
		} catch (Exception e) {
			// TODO: handle exception
		}

		

		
	}
	

}
