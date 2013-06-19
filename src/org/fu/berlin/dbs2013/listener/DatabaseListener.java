package org.fu.berlin.dbs2013.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.fu.berlin.dbs2013.Database;

/**
 * Application Lifecycle Listener implementation class DatabaseListener
 *
 */
@WebListener
public class DatabaseListener implements ServletContextListener, LifecycleListener {

    /**
     * Default constructor. 
     */
    public DatabaseListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
    	Logger.getGlobal().log(Level.INFO, "Creating database connection");
        Database.createInstance();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
//    	Logger.getGlobal().log(Level.INFO, "Shutting down the database.");
//		Database.destroyInstance();
	}

	@Override
	public void lifecycleEvent(LifecycleEvent arg0) {
		if (arg0.getType().equals(Lifecycle.BEFORE_STOP_EVENT)) {
			Logger.getGlobal().log(Level.INFO, "Shutting down the database.");
			Database.destroyInstance();
		}
	}
	
}
