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
	
	private Registry registro;
	
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
		System.out.println(c.getNome() + " mandou a lista");
		
	}

	@Override
	public Map<Cliente, List<Arquivo>> procurarArquivo(String nome) throws RemoteException {
		HashMap<Cliente, List<Arquivo>> resultado = new HashMap<>();
		List<Arquivo> arquivosCliente = new ArrayList<>();
		for(Map.Entry<Cliente, List<Arquivo>> lista : mapaArquivos.entrySet()) {
			for(Arquivo arquivo: mapaArquivos.get(lista.getKey())){
				if (arquivo.getNome().contains(nome.toLowerCase()) || 
						arquivo.getNome().contains(nome.toUpperCase())) {	
						arquivosCliente.add(arquivo);
				}
			}	
			
			if (!arquivosCliente.isEmpty()){
				Cliente c = new Cliente();
				c.setNome(lista.getKey().getNome());
				c.setIp(lista.getKey().getIp());
				c.setPorta(lista.getKey().getPorta());

				resultado.put(c, arquivosCliente);
			}
		}
		return resultado;
	}

	@Override
	public byte[] baixarArquivo(Arquivo arq) throws RemoteException {
		
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
	public void imprimir(){
		
	}
	
	

	
}
