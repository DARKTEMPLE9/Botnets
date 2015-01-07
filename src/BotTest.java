import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * this class is for testing BotAction
 * it also can generate attack by manual or auto
 *
 */
class BotTest {
	private final static long uscInternshipId = 2806479107L;
	private final static long uscFreeTicketsId = 2806602765L;
	private final static long uscAnnouncementId = 2808576466L;
	private final static long wysId = 2814040394L;
	private final static long psuFootballClubId = 2870190785L;
	private final static long psuHappyValleyId = 2870202425L;
	private final static long psuAlumniId = 2871055293L;
	
	private final static String wysScreenName = "wesleylbynum";
	private final static String uscInternshipScreenName = "uscinternship";
	private final static String uscFreeTicketsScreenName = "uscfreetickets";
	private final static String uscAnnouncementScreenName = "uscannouncement";
	private final static String psuFootballClubScreenName = "psufootbaII";
	private final static String psuHappyValleyScreenName = "happyvalley_psu";
	private final static String psuAlumniScreenName = "pennstate_alums";
	
	
	syslogFunc syslog = new syslogFunc("kylin",true);
	private final static String teamName = "kylin";
	private final static String network = "twitter";
	private final static String targetUniversity = "USC";
	private final static String targetUniversity2 = "PSU";
	
	BotAction centrlbot = new BotAction("ucY3LoNII3VzlhONbNJ6sHBn2",
			"xjTgjJOAbhxUFlM2UUCqRfEfJudfbbd1S5aYRIWG93OmKBjRPP");
	
	URLAction urlAction = new URLAction();
	RetweetAction retweetAction = new RetweetAction();
	public BotTest() {
	}
	
	/**
	 * this function can make our bots to do some function by themselves
	 * 
	 */
	public void autoAttack(){
		/**
		centrlbot.retweetStatus(psuFootballClubId, retweetAction.randomUpdateStatus(psuFootballClubScreenName));
		syslog.log(psuFootballClubScreenName, "2870190785", teamName, targetUniversity2, network, "retweet status", "retweet status from real account PennStateFball ");
		
		centrlbot.updateStatus(psuFootballClubId, urlAction.getPSUFootballClub());
		syslog.log(psuFootballClubScreenName, "2870190785", teamName, targetUniversity2, network, "update status", "post a malicious link about psufootbaII ");
		
		centrlbot.retweetStatus(psuAlumniId, retweetAction.randomUpdateStatus(psuAlumniScreenName));
		syslog.log(psuAlumniScreenName, "2871055293", teamName, targetUniversity2, network, "retweet status","retweet status from read account PennStateAlums");
		
		centrlbot.updateStatus(psuAlumniId, urlAction.getPSUAlumni());
		syslog.log(psuAlumniScreenName, "2871055293", teamName, targetUniversity2, network, "update status","post a malicious link about pennstate_alums");
		*/
		/**
		centrlbot.retweetStatus(psuHappyValleyId, retweetAction.randomUpdateStatus(psuHappyValleyScreenName));
		syslog.log(psuHappyValleyScreenName, "2870202425", teamName, targetUniversity2, network, "retweet status", "retweet status from real account PSUWorldCampus");
		*/
		
		centrlbot.updateStatus(psuHappyValleyId, urlAction.getHappyValley());
		syslog.log(psuHappyValleyScreenName, "2870202425", teamName, targetUniversity2, network, "update status", "post a malicious link about happyvalley_psu");
		/**
		centrlbot.retweetStatus(uscInternshipId, retweetAction.randomUpdateStatus(uscInternshipScreenName));
		syslog.log(uscInternshipScreenName, "2806479107", teamName, targetUniversity, network, "retweet status", "retweet status from real account USCCareercenter");
		
		centrlbot.retweetStatus(uscAnnouncementId, retweetAction.randomUpdateStatus(uscAnnouncementScreenName));
		syslog.log(uscAnnouncementScreenName, "2808576466", teamName, targetUniversity, network, "retweet status", "retweet status from real account USC");
		
		centrlbot.retweetStatus(uscFreeTicketsId, retweetAction.randomUpdateStatus(uscFreeTicketsScreenName));
		syslog.log(uscFreeTicketsScreenName, "2806602765", teamName, targetUniversity, network, "retweet status", "retweet status from real account USCStudentsTickets");

		centrlbot.createFavoriteToStatus(uscInternshipId, retweetAction.randomUpdateStatus(uscInternshipScreenName));
		syslog.log(uscInternshipScreenName, "2806479107", teamName, targetUniversity, network, "create favorite", "favorite a status from real account USCCareercenter");
		
		centrlbot.createFavoriteToStatus(uscAnnouncementId, 525746455298859008L);
		syslog.log(uscAnnouncementScreenName, "2808576466", teamName, targetUniversity, network, "create favorite", "favorite a status from real account USC");
		
		
		centrlbot.updateStatus(uscInternshipId, urlAction.getInternshipStatus());
		syslog.log(uscInternshipScreenName, "2806479107", teamName, targetUniversity, network, "update status", "post a malicious link about internship");
		
		
		centrlbot.updateStatus(uscAnnouncementId, urlAction.getAnnouncement());
		syslog.log(uscAnnouncementScreenName, "2808576466", teamName, targetUniversity, network, "update status", "post a malicious link about announcement");
		
		
		centrlbot.updateStatus(uscFreeTicketsId, urlAction.getTicketStatus());
		syslog.log(uscFreeTicketsScreenName, "2806602765", teamName, targetUniversity, network, "update status", "post a malicious link about freetickets");
		*/
	}
	
	/**
	 * parse some commands to method,
	 * make sure our program can deal with user's command
	 * @param userid
	 */
	public void parseCommandToMethod(long userid) {
		System.out.println("please your operation:");
		System.out
				.println("the form is like method|arg1|arg2, the following gives correct example!");
		System.out.println("updateStatus|msg");
		System.out.println("tweetToSomeone|screenname|msg");
		System.out.println("followPerson|screenname");
		System.out.println("sendDirectMessage|screenname|msg");
		System.out.println("updateProfile|name|website|location|introduction");
		Scanner scanner = new Scanner(System.in);
		String line;
		String[] input;
		try {
			while ((line = scanner.nextLine()) != null) {
				input = line.split("\\|");//use | split we need to add \\ to make sure it is useful
				if (input[0].equals("updateStatus")) {
					centrlbot.updateStatus(userid, input[1]);
				} else if (input[0].equals("tweetToSomeone")) {
					centrlbot.tweetToSomeone(userid, input[1], input[2]);
				} else if (input[0].equals("followPerson")) {
					centrlbot.followPerson(userid, input[1]);
				} else if (input[0].equals("sendDirectMessage")) {
					centrlbot.sendDirectMessage(userid, input[1], input[2]);
				} else if (input[0].equals("updateProfile")) {
					centrlbot.updateProfile(userid, input[1], input[2],
							input[3], input[4]);
				} else {
					System.out.println("something wrong with your operation!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BotTest test = new BotTest();
		long uscInternshipId = 2806479107L;
		long uscFreeTicketsId = 2806602765L;
		long uscAnnouncementId = 2808576466L;
		long wysId = 2814040394L;
		System.out.println("some bot for centrl:");
		System.out.println("uscinternshipcenter");
		System.out.println("uscfreetickets");
		System.out.println("uscannouncement");
		System.out.println("autoattack");
		System.out.println("wys");
		System.out.println("Please choose your bot:");
		Scanner scanner = new Scanner(System.in);
		String bot = scanner.nextLine();
		if (bot.equals("uscinternshipcenter")) {
			System.out.println("please enter operation that you want!");
			test.parseCommandToMethod(uscInternshipId);
		} else if (bot.equals("uscfreetickets")) {
			System.out.println("please enter operation that you want!");
			test.parseCommandToMethod(uscFreeTicketsId);
		} else if (bot.equals("autoattack")) {
			System.out.println("please enter operation that you want!");
			test.autoAttack();
		} else if (bot.equals("uscannouncement")) {
			System.out.println("please enter operation that you want!");
			test.parseCommandToMethod(uscAnnouncementId);
		} else {
			System.out.println("error input!");
		}
	}
}