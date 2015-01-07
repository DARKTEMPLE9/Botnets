import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * this class define Twitter bot action
 * @author darktemple9
 */
public class BotAction {
	Twitter twitter = null;
	AccessToken accessToken = null;
	//customkey, customsecret are your app key!
	public BotAction(String customkey, String customsecret){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey(customkey);
		cb.setOAuthConsumerSecret(customsecret);
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	/**
	 * This function can help our program get auth from our bot!
	 * we only execute this function when we need to get authorization from new bot!
	 */
	public void oauthTwitterClient(){
		accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			RequestToken requestToken = twitter.getOAuthRequestToken();
			while(accessToken == null){
				System.out.println("Open the following URL and grant access to your account:");
				System.out.println(requestToken.getAuthorizationURL());
				System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
				try {
					String pin = br.readLine();
					if(pin.length() > 0){
						accessToken = twitter.getOAuthAccessToken(requestToken, pin);
					}
					else{
						accessToken = twitter.getOAuthAccessToken();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (TwitterException e) {
			if(e.getStatusCode() == 401){
				System.out.println("Unable to get the access token.");
			}
			else{
				e.printStackTrace();
			}
		}

		try {
			long userid = twitter.verifyCredentials().getId();
			String screenName = twitter.getScreenName();
			storeAccessToken(userid,screenName,accessToken);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This function stores accesstoken information in the file
	 * @param userid
	 * @param screenName
	 * @param accessToken
	 */
	public void storeAccessToken(long userid, String screenName,AccessToken accessToken){
		System.out.print("Storing access token for " + userid + "...");
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("token.txt",true));//let continue to write after the last line
			out.append(""+userid);
			out.append(" "+accessToken.getToken());
			out.append(" "+accessToken.getTokenSecret());
			out.append(" "+screenName);
			out.newLine();
			out.close();
			System.out.println("write is done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This function can get accesstoken information from what you want
	 * @param userid
	 * @return
	 */
	public AccessToken loadAccessToken(long userid){
		AccessToken accessToken = null;
		String id = ""+userid;
		String line;
		String[] data;
		try{
			BufferedReader in = new BufferedReader(new FileReader("token.txt"));
			while((line = in.readLine())!=null){
				data = line.split(" ");
				if(id.equals(data[0])){
					accessToken = new AccessToken(data[1],data[2]);
					System.out.println("Access token found for " + userid);
					break;
				}
			}
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return accessToken;
	}
	
	/**
	 * update bot status
	 * @param userid
	 * @param status
	 */
	public void updateStatus(long userid, String status){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		try {
			twitter.updateStatus(status);
			System.out.println("successful update status!");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * tweet to someone with some message
	 * @param userid
	 * @param screenName
	 * @param status
	 */
	public void tweetToSomeone(long userid, String screenName, String status){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		String str = "@"+screenName+" "+status;
		try {
			twitter.updateStatus(str);
			System.out.println("successful tweet to someone!");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * send someone direct message
	 * @param userid
	 * @param screenName
	 * @param message
	 */
	public void sendDirectMessage(long userid, String screenName, String message){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		try {
			twitter.sendDirectMessage(screenName, message);
			System.out.println("DM is done!");
		} catch (TwitterException e) {
			e.printStackTrace();
			 System.out.println("Failed to send a direct message: " + e.getMessage());
		}
	}
	
	/**
	 * follow someone!
	 * @param userid
	 * @param screenName
	 */
	public void followPerson(long userid, String screenName){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		try {
			twitter.createFriendship(screenName);
			System.out.println("follow is done!");
		} catch (TwitterException e) {
			e.printStackTrace();
			System.out.println("Failed to follow: " + e.getMessage());
		}
	}
	
	/**
	 * update bot profile,like name, website, location and bio!
	 * @param userid
	 * @param name
	 * @param website
	 * @param location
	 * @param bio
	 */
	public void updateProfile(long userid,String name, String website, String location, String bio){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		try {
			twitter.updateProfile(name, website, location, bio);
			System.out.println("update profile success");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * retweet some status
	 * @param userid
	 * @param statusid
	 */
	public void retweetStatus(long userid, long statusid){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		try {
			twitter.retweetStatus(statusid);
			System.out.println("retweet successful!");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * create favorite by one status!
	 * @param statusid
	 */
	public void createFavoriteToStatus(long userid, long statusid){
		accessToken = loadAccessToken(userid);
		twitter.setOAuthAccessToken(accessToken);
		try {
			twitter.createFavorite(statusid);
			System.out.println("favorite have finished!");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	/**
	 * when you have a new user want to use this app, do authenication
	 
	public static void main(String []args){
		BotAction aa = new BotAction("ucY3LoNII3VzlhONbNJ6sHBn2",
				"xjTgjJOAbhxUFlM2UUCqRfEfJudfbbd1S5aYRIWG93OmKBjRPP");
		aa.oauthTwitterClient();
	}
	*/
}
