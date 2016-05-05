package br.univel.server;

import java.io.File;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.rmi.Remote;
import br.univel.jshare.tela.*;


import br.dagostini.jshare.comum.pojos.Arquivo;
import br.dagostini.jshare.comum.pojos.Diretorio;
import br.dagostini.jshare.comun.Cliente;
import br.dagostini.jshare.comun.IServer;

public class Server implements IServer {
	private static Server instance = null;
	
	private Server(){
		
	}
	public static Server getInstance() {
	      if(instance == null) {
	         instance = new Server();
	      }
	      return instance;
	}

	private Cliente cliente;
	
	private IServer server ;
	
	private IServer serverC ;
	
	private Registry registro;
	
	private Registry registroC;
	
	public File diretorio;
	
	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<>();
	
	private Map<String, Cliente> mapaClientes = new HashMap<>();
	
	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mapaClientes.put(c.getIp(), c);
		System.out.println("Cliente: "+c.getNome()+" IP: "+c.getIp()+"Entrou");
		InterfaceGrafica.getInstance().mostrarConectados(mapaClientes);
		
		
		
	}

	@Override
	public void publicarListaArquivos(Cliente c, List<Arquivo> lista) throws RemoteException {
		mapaArquivos.put(c, lista);
		
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
		
	}
	
	public void removerDaLista(Cliente c) {
		mapaClientes.remove(c.getIp());
		InterfaceGrafica.getInstance().mostrarConectados(mapaClientes);
		
	}
	
	public void iniciarServico(){
		try {
			server = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registro = LocateRegistry.createRegistry(1818);
			registro.rebind(IServer.NOME_SERVICO, server);
			System.out.println("Serviço Iniciado");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void pararServico(){
		try {
			UnicastRemoteObject.unexportObject(this, true);
			UnicastRemoteObject.unexportObject(registro, true);
			
			System.out.println("Serviço Parado");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
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

	
}
