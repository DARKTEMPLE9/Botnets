import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * @author darktemple9
 * this class will get some status from Twitter user, 
 * then store these info to file so that our bot can retweet these thing randomly
 * It is a helper class for our botnet to make our bot more real.
 */
public class SpiderAction {
	BotAction bt = new BotAction("ucY3LoNII3VzlhONbNJ6sHBn2",
			"xjTgjJOAbhxUFlM2UUCqRfEfJudfbbd1S5aYRIWG93OmKBjRPP");
	private final static long wysId = 2814040394L;
	private final static String dbname = "SocialInfo";
	public SpiderAction(){}
	
	/**
	 * get latest 20 status and statusid
	 * @param targetId
	 * @return
	 */
	private ResponseList<Status> getUserTimeLine(long targetId){
		ResponseList<Status> statusList = null;
		try {
			bt.accessToken = bt.loadAccessToken(wysId);
			bt.twitter.setOAuthAccessToken(bt.accessToken);
			statusList = bt.twitter.getUserTimeline(targetId);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
	}
	
	/**
	 * store status into our db
	 * @param targetId
	 * @param screenName
	 * @throws SQLException
	 * @throws ParseException 
	 */
	public void storeInforToDB(long targetId, String screenName) throws SQLException, ParseException{
		ResponseList<Status> statusList = getUserTimeLine(targetId);
		String query = null;
		String latestDate = null;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Connection conn = SQLConn.getSQLConn(dbname);
		PreparedStatement preparedStat = null;
		String sql = "select date from RetweetInfo where screenname ='"+screenName+"' order by date desc limit 1 ";
		preparedStat = conn.prepareStatement(sql);
		ResultSet rs = preparedStat.executeQuery(sql);
		while(rs.next()){
			latestDate = rs.getString("date");
		}
		for(int i = 0; i<statusList.size();i++){
			if(statusList.get(i).getCreatedAt().after(df.parse(latestDate))){
				query = "insert into TwitterStatus(statusid,status,screenName,date)"+"values(?,?,?,?)";
				preparedStat = conn.prepareStatement(query);
				preparedStat.setString(1, String.valueOf(statusList.get(i).getId()));
				preparedStat.setString(2, statusList.get(i).getText());
				preparedStat.setString(3, screenName);
				preparedStat.setString(4, df.format(statusList.get(i).getCreatedAt()));
				preparedStat.execute();
			}else{
				break;
			}
		}
		System.out.println("success insert!");
		conn.close();
	}
	
	/**
	 * clean database for next update
	 */
	public void deleteAllInfoInDB(){
		Connection conn = SQLConn.getSQLConn(dbname);
		String sql = "delete from TwitterStatus";
		try {
			PreparedStatement preparestat = conn.prepareStatement(sql);
			preparestat.execute();
			System.out.println("success delete!");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * we can crawl status one time / two days
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String []args) throws ParseException{
		//for uscannouncement
		long USC_targetId = 19809471L;
		String USC_screenName = "USC";
		//for uscinternship
		long USCCareerCenter_targetId = 102803242L;
		String USCCareerCenter_screenName = "USCCareerCenter";
		//for uscfreetickets
		long USCTickets_targetId = 110749020L;
		String USCTickets_screenName = "USCstudentTIX";
		
		//for psuonline
		long PSUWorldCampus_targetId = 9090052L;
		String PSUWorldCampus_screenName = "PSUWorldCampus";
		//for psufootball
		long PSUFootball_targetId = 53103297L;
		String PSUFootball_screenName = "PennStateFball";
		//for psualumni
		long PSUAlumni_targetId = 15562285L;
		String PSUAlumni_screenName = "PennStateAlums";
		
		SpiderAction sa = new SpiderAction();
		sa.deleteAllInfoInDB();
		try {
			//sa.storeInforToDB(USC_targetId, USC_screenName);
			//sa.storeInforToDB(USCCareerCenter_targetId,USCCareerCenter_screenName);
			//sa.storeInforToDB(USCTickets_targetId, USCTickets_screenName);
			sa.storeInforToDB(PSUWorldCampus_targetId, PSUWorldCampus_screenName);
			sa.storeInforToDB(PSUFootball_targetId, PSUFootball_screenName);
			sa.storeInforToDB(PSUAlumni_targetId, PSUAlumni_screenName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
