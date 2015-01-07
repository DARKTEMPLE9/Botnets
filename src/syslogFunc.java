import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;
/**
 * syslogFunc
 * This class is used by Hopkins students to perform bot syslog functionality with JVM based applications
 */
public class syslogFunc {
	private Logger theLogger = null;
	private String teamName = "default";
    private String outputFile = "default.log";
    private Handler fileHandler = null;
    private SimpleFormatter formatter = null;
    /**
     * Constructor -- specify teamName to name the logger
     * @param teamName The name of the team
     */
	public syslogFunc(String teamName, boolean enableConsoleHandler) {
		this.teamName = teamName;
		this.theLogger = Logger.getLogger(this.teamName);
        try {
            this.fileHandler = new FileHandler(this.teamName + ".log");
            this.theLogger.addHandler(this.fileHandler);
            this.formatter = new SimpleFormatter();
            this.fileHandler.setFormatter(this.formatter);
            this.theLogger.setUseParentHandlers(enableConsoleHandler);
        } catch(SecurityException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    /**
     * Gets the teamName of this logger
     * @return the name of the team
     */
	public String getTeamName() {
		return this.teamName;
	}

    /**
     * sets the teamName of this logger
     * @param teamName the name of the team
     */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
		theLogger = Logger.getLogger(this.teamName);
	}
    /**
     *
     * @param botName the botname or handle
     * @param uid the network uid
     * @param teamName your team name
     * @param targetUniversity your target university
     * @param network the network name
     * @param action_name the action the bot is taking
     * @param details details of the action
     */
	public void log(String botName, String uid, String teamName, String targetUniversity, String network, String actionName, String details) {  
		String msg = botName + "," + uid + "," + teamName + "," + targetUniversity + "," + network + "," + actionName + "," + details;
		this.theLogger.log(Level.INFO, msg);
	}
	
}