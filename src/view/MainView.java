package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tablemodel;
	private Registry referenciaServicoNomes;
	/**
	 * Create the frame.
	 */
	public MainView(Registry referenciaServicoNomes) {
		this.referenciaServicoNomes = referenciaServicoNomes;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					for(String a : getNomes()){
						String[] o ={a} ;
						tablemodel.addRow(o);
					}
				} catch (AccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(178, 11, 89, 23);
		contentPane.add(btnRefresh);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 414, 215);
		contentPane.add(scrollPane);
		
		tablemodel = new DefaultTableModel();
		tablemodel.addColumn("Clients");
				
		table = new JTable(tablemodel);
		scrollPane.setViewportView(table);
	}
	public String[] getNomes() throws AccessException, RemoteException{
		return referenciaServicoNomes.list();
	}
}
