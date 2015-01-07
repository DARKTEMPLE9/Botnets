import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * this class will auto generate retweet statusId
 * our bet can use this retweet statusId to retweet, favorite
 * @author darktemple9
 *
 */
public class RetweetAction {
	private final static String dbname = "SocialInfo";
	/**
	 * get which status will be choosed to reweet
	 * @param screenName
	 * @return
	 */
	private List<InfoEntity> getReweetStatusIdByName(String screenName){
		List<InfoEntity> list = new ArrayList<InfoEntity>();
		Connection conn = SQLConn.getSQLConn(dbname);
		String sql = "select * from TwitterStatus where screenName = '"+screenName+"'"; 
		PreparedStatement preparedStat = null;
		try {
			preparedStat = conn.prepareStatement(sql);
			ResultSet rs = preparedStat.executeQuery(sql);
			while(rs.next()){
				InfoEntity info = new InfoEntity();
				info.setStatusId(rs.getString("statusId"));
				info.setDate(rs.getString("date"));
				info.setScreenName(rs.getString("screenName"));
				list.add(info);
			}
			conn.close();
			System.out.println("successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * when retweet this status, according to date and statusid delete some relevant record from db
	 * @param statusId
	 */
	private void deleteStatusByStatusIdAndDate(String screenName,String date){
		Connection conn = SQLConn.getSQLConn(dbname);
		String sql = "delete from TwitterStatus where screenName = ? and date <= ?";
		PreparedStatement preparedStat = null;
		try {
			preparedStat = conn.prepareStatement(sql);
			preparedStat.setString(1, screenName);
			preparedStat.setString(2, date);
			preparedStat.execute();
			conn.close();
			System.out.println("delete success!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertRetweetInfoToDB(String screenName, String statusid, String date){
		Connection conn = SQLConn.getSQLConn(dbname);
		PreparedStatement preparedStat = null;
		String sql = "insert into RetweetInfo(statusId,date,screenname)"+"values(?,?,?)";
		try {
			preparedStat = conn.prepareStatement(sql);
			preparedStat.setString(1, statusid);
			preparedStat.setString(2, date);
			preparedStat.setString(3, screenName);
			preparedStat.execute();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * generate retweet statusId
	 * @param screenName
	 * @return
	 */
	public long randomUpdateStatus(String screenName){
		String myscreenName = null;
		if(screenName.equals("uscannouncement")){
			myscreenName = "USC";
		}
		else if(screenName.equals("uscfreetickets")){
			myscreenName = "USCstudentTIX";
		}
		else if(screenName.equals("uscinternship")){
			myscreenName = "USCCareerCenter";
		}
		else if(screenName.equals("psufootbaII")){
			myscreenName = "PennStateFball";
		}
		else if(screenName.equals("happyvalley_psu")){
			myscreenName = "PSUWorldCampus";
		}
		else if(screenName.equals("pennstate_alums")){
			myscreenName = "PennStateAlums";
		}
		else{
			System.out.println("cannot find match");
		}
		List<InfoEntity> list = getReweetStatusIdByName(myscreenName);
		int capacity = list.size();
		System.out.println(capacity);
		int i = StdRandom.uniform(capacity);
		long statusId =Long.parseLong(list.get(i).getStatusId(),10);
		insertRetweetInfoToDB(list.get(i).getScreenName(),list.get(i).getStatusId(),list.get(i).getDate());
		deleteStatusByStatusIdAndDate(myscreenName,list.get(i).getDate());
		return statusId;
	}
	
}