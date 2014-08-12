import java.awt.EventQueue;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import view.MainView;



public class Main {
	private static Registry referenciaServicoNomes;
	public static void main(String[] args) {
		try {
			
			referenciaServicoNomes = LocateRegistry.createRegistry(1099); //cria o registro de nomes
			System.out.println("INFO: Servidor do serviço de nomes inicializado.");
			
		} catch (Exception e) {
			System.err.println("Erro: " + e.getLocalizedMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView(referenciaServicoNomes);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
