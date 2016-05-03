package br.univel.cliente;

import java.io.File;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private IServer server;
	
	private Registry registro;
	
	public File diretorio;
	
	private Map<Cliente, List<Arquivo>> mapaArquivos = new HashMap<>();
	
	
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
			server = (IServer) UnicastRemoteObject.exportObject(this, 0);
			registro = LocateRegistry.createRegistry(1818);
			registro.rebind(IServer.NOME_SERVICO, server);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
