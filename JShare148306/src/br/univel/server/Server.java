package br.univel.server;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.rmi.Remote;


import br.dagostini.jshare.comum.pojos.Arquivo;
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
	
	private Registry registro;
	
	public File diretorio;
	
	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<>();
	
	private Map<String, Cliente> mapaClientes = new HashMap<>();
	
	@Override
	public void registrarCliente(Cliente c) throws RemoteException {
		mapaClientes.put(c.getIp(), c);
		System.out.println("Cliente: "+c.getNome()+" IP: "+c.getIp()+"Entrou");
		
		
		
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
		try {
			if (server != null) {
				UnicastRemoteObject.unexportObject(this, true);
				removerDaLista(c);
				server = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void removerDaLista(Cliente c) {
		mapaClientes.remove(c.getIp());
		
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
	
	public void conectar(String host,int porta, Cliente c){
	
		
		try {
			registro = LocateRegistry.getRegistry(host, porta);
			server = (IServer)registro.lookup(IServer.NOME_SERVICO);
			
			
			System.out.println("Conectado");
			registrarCliente(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
