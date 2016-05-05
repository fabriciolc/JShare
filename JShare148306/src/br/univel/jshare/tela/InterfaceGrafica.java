package br.univel.jshare.tela;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;
import br.univel.jshare.modelo.TableModel;
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
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class InterfaceGrafica extends JFrame implements IServer{

	private JPanel contentPane;
	private static InterfaceGrafica instance = null;
	private SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy H:mm:ss:SSS");
	private TableModel modeloTabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGrafica frame = new InterfaceGrafica();
					frame.setVisible(true);
					instance = frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static InterfaceGrafica getInstance() {
	      if(instance == null) {
	         instance = new InterfaceGrafica();
	      }
	      return instance;
	}

	/**
	 * Create the frame.
	 */
	public InterfaceGrafica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 397);
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
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		JLabel lblPesquisar = new JLabel("Pesquisar");
		GridBagConstraints gbc_lblPesquisar = new GridBagConstraints();
		gbc_lblPesquisar.anchor = GridBagConstraints.EAST;
		gbc_lblPesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesquisar.gridx = 5;
		gbc_lblPesquisar.gridy = 5;
		panel.add(lblPesquisar, gbc_lblPesquisar);
		
		txtPesquisar = new JTextField();
		GridBagConstraints gbc_txtPesquisar = new GridBagConstraints();
		gbc_txtPesquisar.gridwidth = 3;
		gbc_txtPesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_txtPesquisar.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPesquisar.gridx = 6;
		gbc_txtPesquisar.gridy = 5;
		panel.add(txtPesquisar, gbc_txtPesquisar);
		txtPesquisar.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarArquivo();
			}
		});
		GridBagConstraints gbc_btnPesquisar = new GridBagConstraints();
		gbc_btnPesquisar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPesquisar.insets = new Insets(0, 0, 5, 5);
		gbc_btnPesquisar.gridx = 9;
		gbc_btnPesquisar.gridy = 5;
		panel.add(btnPesquisar, gbc_btnPesquisar);
		
		tableArquivos = new JTable();
		GridBagConstraints gbc_tableArquivos = new GridBagConstraints();
		gbc_tableArquivos.gridheight = 4;
		gbc_tableArquivos.gridwidth = 8;
		gbc_tableArquivos.insets = new Insets(0, 0, 0, 5);
		gbc_tableArquivos.fill = GridBagConstraints.BOTH;
		gbc_tableArquivos.gridx = 1;
		gbc_tableArquivos.gridy = 6;
		panel.add(tableArquivos, gbc_tableArquivos);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Servidor", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 93, 0, 0, 0, 0, 0, 0, 0, 73, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 67, 0, 0};
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
		
		JLabel labelIPSV = new JLabel("");
		GridBagConstraints gbc_labelIPSV = new GridBagConstraints();
		gbc_labelIPSV.insets = new Insets(0, 0, 5, 5);
		gbc_labelIPSV.gridx = 2;
		gbc_labelIPSV.gridy = 1;
		panel_1.add(labelIPSV, gbc_labelIPSV);
		
		JLabel lblPorta = new JLabel("Porta: ");
		GridBagConstraints gbc_lblPorta = new GridBagConstraints();
		gbc_lblPorta.insets = new Insets(0, 0, 5, 5);
		gbc_lblPorta.gridx = 4;
		gbc_lblPorta.gridy = 1;
		panel_1.add(lblPorta, gbc_lblPorta);
		
		JLabel label = new JLabel("1818");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 5;
		gbc_label.gridy = 1;
		panel_1.add(label, gbc_label);
		
		JLabel lblPastaCompartilhada = new JLabel("Pasta Compartilhada");
		GridBagConstraints gbc_lblPastaCompartilhada = new GridBagConstraints();
		gbc_lblPastaCompartilhada.insets = new Insets(0, 0, 5, 5);
		gbc_lblPastaCompartilhada.anchor = GridBagConstraints.WEST;
		gbc_lblPastaCompartilhada.gridx = 1;
		gbc_lblPastaCompartilhada.gridy = 2;
		panel_1.add(lblPastaCompartilhada, gbc_lblPastaCompartilhada);
		
		btnIniciarServio = new JButton("Iniciar Servi\u00E7o");
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
		
		btnParaServio = new JButton("Para Servi\u00E7o");
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
		gbc_lblPessoasConectadas.anchor = GridBagConstraints.WEST;
		gbc_lblPessoasConectadas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPessoasConectadas.gridx = 1;
		gbc_lblPessoasConectadas.gridy = 5;
		panel_1.add(lblPessoasConectadas, gbc_lblPessoasConectadas);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		txtAreaConectados = new JTextArea();
		scrollPane.setViewportView(txtAreaConectados);
		
		
		labelIP.setText(pegarIPLocal());
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDownload.gridx = 9;
		gbc_btnDownload.gridy = 9;
		panel.add(btnDownload, gbc_btnDownload);
		labelIPSV.setText(pegarIPLocal());
		btnParaServio.setEnabled(false);
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
	
	private IServer server;
	
	private Registry registro;
	
	private Map<String, Cliente> mapaClientes = new HashMap<>();
	
	
	private JTextField txtIP;
	private JTextField txtPorta;
	private JTable tableArquivos;
	private JButton btnConectar;
	private JButton btnDesconectar;
	private JTextField txtNome;
	private JTextArea txtAreaConectados;
	private JButton btnIniciarServio;
	private JButton btnParaServio;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;

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
		List<Arquivo> arquivos = criarListaDeArquivos();
		
		for (Arquivo arquivo : arquivos) {
			if (arquivo.getNome().contains(arq.getNome())){;
				byte[] dados = lerArquivo(new File("C:\\JShare\\Uploads\\"+arq.getNome()));
				
				return dados;
			}
		}
		return null;
	}
	private byte[] lerArquivo(File file) {
		Path path = Paths.get(file.getPath());
		try {
			byte[] dados = Files.readAllBytes(path);
			return dados;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public void desconectar(Cliente c) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	public void mostrarConectados(Map<String, Cliente> mapc){
		txtAreaConectados.setText("");
		for (Entry<String, Cliente> c : mapc.entrySet()){
			txtAreaConectados.append(data.format(new Date()));
			txtAreaConectados.append(" -- ");
			txtAreaConectados.append(c.getValue().getNome() + " " + c.getValue().getIp());
			txtAreaConectados.append("\n");
		}
		
	}

	public void atualizarTextArea (){
		
	}
	protected void iniciarServico(){
		Server.getInstance().iniciarServico();
		btnIniciarServio.setEnabled(false);
		btnParaServio.setEnabled(true);
		
	}
	protected void pararServico(){
		Server.getInstance().pararServico();
		btnIniciarServio.setEnabled(true);
		btnParaServio.setEnabled(false);
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
		try {
			registro = LocateRegistry.getRegistry(host, intPorta);
			server = (IServer)registro.lookup(IServer.NOME_SERVICO);
			
			
			server.registrarCliente(c);
			server.publicarListaArquivos(c, criarListaDeArquivos());
			System.out.println("Conectado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtIP.setEnabled(false);
		txtPorta.setEnabled(false);
		btnConectar.setEnabled(false);
		
	}
	protected void desconectar() {
		try {
			if (server != null) {
				Server.getInstance().removerDaLista(c);
				server = null;
				
			}
			txtIP.setEnabled(true);
			txtPorta.setEnabled(true);
			btnConectar.setEnabled(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		
	}
	private List<Arquivo> criarListaDeArquivos() {	
		File dirUpload = new File("C:/Teste/JShare/Uploads");
		File dirDownload = new File("C:/Teste/JShare/Downloads");

		if (!dirUpload.exists())
			dirUpload.mkdirs();
		if (!dirDownload.exists())
			dirDownload.mkdirs();
		
		
		List<Arquivo> listaArquivos = new ArrayList<>();
		List<Diretorio> listaDiretorios = new ArrayList<>();
		
		for (File file : dirUpload.listFiles()) {
			if (file.isFile()) {
				Arquivo arq = new Arquivo();
				arq.setNome(file.getName());
				arq.setTamanho(file.length());
				listaArquivos.add(arq);
			} else {
				Diretorio dir = new Diretorio();
				dir.setNome(file.getName());
				listaDiretorios.add(dir);				
			}
		}
		
		return listaArquivos;
	}
	protected void pesquisarArquivo() {

		String pesquisa = txtPesquisar.getText();
		Map<Cliente, List<Arquivo>> arquivosPesquisados = new HashMap<>();
		try {
			arquivosPesquisados = server.procurarArquivo(pesquisa);
			
			if (arquivosPesquisados.isEmpty()){
				JOptionPane.showMessageDialog(this, "Nao foram encontrados arquivos no servidor!");
				return;
			} else {
				modeloTabela = new TableModel(arquivosPesquisados);
				tableArquivos.setModel(modeloTabela);
			}
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void downloadArquivo() {
		
		String nomeArquivo = (String) tableArquivos.getValueAt(tableArquivos.getSelectedRow(), 0);
		String IP = (String) tableArquivos.getValueAt(tableArquivos.getSelectedRow(), 3);
		int porta = (int) tableArquivos.getValueAt(tableArquivos.getSelectedRow(), 4);
		Arquivo arquivo = new Arquivo();
		arquivo.setNome(nomeArquivo);
		
		
		try {
			registro = LocateRegistry.getRegistry(IP, porta);
			IServer clienteServidor = (IServer) registro.lookup(IServer.NOME_SERVICO);
			clienteServidor.registrarCliente(cliente);
			
			byte[] baixarArquivo = clienteServidor.baixarArquivo(arquivo);
			writeFile(new File("C:\\Teste\\JShare\\Downloads\\"+arquivo.getNome()), baixarArquivo);	
			
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(this, "Erro ao fazer download do Arquivo");
			e.printStackTrace();
		} catch (NotBoundException e) {
			JOptionPane.showMessageDialog(this, "Erro ao fazer download do Arquivo");
			e.printStackTrace();
		}
	}
	private void writeFile(File file, byte[] dados) {
		try {
			Files.write(Paths.get(file.getPath()), dados, StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	

}
