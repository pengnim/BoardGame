package web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class DBConnectionManager
 *
 */
@WebListener
public class DBConnectionManager implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public DBConnectionManager() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	Connection conn = (Connection) event.getServletContext().getAttribute("DBconnection");
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    	Connection conn = null;
		Properties connectionProps = new Properties();
		
		ServletContext sc = event.getServletContext();
		String url = sc.getInitParameter("dburl");
		String user = sc.getInitParameter("dbuser");
		String passwd = sc.getInitParameter("dbpasswd");
		
		connectionProps.put("user", user);
		connectionProps.put("password", passwd);

		try {
			conn = DriverManager.getConnection(url, connectionProps);
			System.out.println("Connection Sucessful!!!");
			if (conn != null) { sc.setAttribute("DBconnection", conn); sc.setAttribute("grpNum", 0);}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
