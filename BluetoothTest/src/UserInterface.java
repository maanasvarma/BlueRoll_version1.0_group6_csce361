import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextArea;

import javax.swing.JLabel;

import java.util.ArrayList;
import java.util.Timer;
import java.awt.Color;


public class UserInterface {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public UserInterface() {

		//create frame
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("BlueRoll");
		frame.setLocationRelativeTo(null);

		//create button
		final JButton btnRollCall = new JButton("START ROLL CALL");
		btnRollCall.setBackground(SystemColor.activeCaption);
		btnRollCall.setForeground(SystemColor.text);
		btnRollCall.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		btnRollCall.setBounds(182, 291, 300, 50);
		frame.getContentPane().add(btnRollCall);

		//create logo
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(UserInterface.class.getResource("/images/BlueRollLogo.jpg")));
		label_1.setBounds(210, 130, 564, 133);
		frame.getContentPane().add(label_1);

		JButton btnViewSemesterReport = new JButton("VIEW SEMESTER REPORT");
		btnViewSemesterReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSemesterReport();	
			}
		});

		btnViewSemesterReport.setForeground(Color.WHITE);
		btnViewSemesterReport.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 20));
		btnViewSemesterReport.setBackground(SystemColor.activeCaption);
		btnViewSemesterReport.setBounds(494, 291, 300, 50);
		frame.getContentPane().add(btnViewSemesterReport);
		
		JLabel lblRights = new JLabel("@2015 Datla, Kulwicki, Salzman");
		lblRights.setForeground(SystemColor.activeCaption);
		lblRights.setFont(new Font("Arial Narrow", Font.ITALIC, 12));
		lblRights.setHorizontalAlignment(SwingConstants.CENTER);
		lblRights.setBounds(146, 427, 692, 16);
		frame.getContentPane().add(lblRights);

		btnRollCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewSummaryReport();
			}



		});
	}

	private void viewSemesterReport(){
		String worked = Main.viewSemesterReport();
		JFrame semesterReportFrame = new JFrame();
		
		semesterReportFrame.getContentPane().setBackground(SystemColor.window);
		semesterReportFrame.setBounds(100, 100, 1000, 500);
		semesterReportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		semesterReportFrame.getContentPane().setLayout(null);
		semesterReportFrame.setTitle("BlueRoll | Semester Report");
		semesterReportFrame.setLocationRelativeTo(null);
		semesterReportFrame.setVisible(true);

		JLabel lblNewLabel = new JLabel("SUMMARY REPORT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(308, 32, 367, 71);
		semesterReportFrame.getContentPane().add(lblNewLabel);
		
		TextArea textArea = new TextArea();
		textArea.setText(worked);
		textArea.setForeground(SystemColor.textInactiveText);
		textArea.setBackground(SystemColor.window);
		textArea.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		textArea.setBounds(28, 130, 933, 307);
		semesterReportFrame.getContentPane().add(textArea);
	}

	private void viewSummaryReport(){
		JFrame summaryReportFrame = new JFrame();
		
		summaryReportFrame.getContentPane().setBackground(SystemColor.window);
		summaryReportFrame.getContentPane().setLayout(null);
		summaryReportFrame.setBounds(100, 100, 1000, 500);
		summaryReportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		summaryReportFrame.getContentPane().setLayout(null);
		summaryReportFrame.setTitle("BlueRoll | Summary Report");
		summaryReportFrame.setLocationRelativeTo(null);
		summaryReportFrame.setVisible(true);
		
		

		JLabel lblNewLabel = new JLabel("SUMMARY REPORT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.activeCaption);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(308, 32, 367, 71);
		summaryReportFrame.getContentPane().add(lblNewLabel);
		
		TextArea textArea = new TextArea();
		textArea.setText("");
		textArea.setForeground(SystemColor.textInactiveText);
		textArea.setBackground(SystemColor.window);
		textArea.setFont(new Font("Arial Narrow", Font.BOLD, 14));
		textArea.setBounds(28, 130, 933, 307);
		textArea.setEditable(false);
			
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = Main.rollCall();
		StringBuilder summaryReport = new StringBuilder();
		int count = 0;
		for(Student st : studentList){
			if(st.isPresent()){
				summaryReport.append(st.getStudentName() +  " is present,  " + "\n");

				count++;
			}else{
				summaryReport.append(st.getStudentName() + " is absent,  " + "\n");
			}
		}
		
		double percent = (count*1.0/studentList.size())*100;
		String percentString = "That is " +percent+ "% of the class!";
		summaryReport.append(percentString);
		String results = summaryReport.toString();
		textArea.setText(results);
		summaryReportFrame.getContentPane().add(textArea);
		
	}

	
	public JFrame getFrame() {
		return frame;
	}


	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}