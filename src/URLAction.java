/**
 * 
 * @author darktemple9
 * auto generate status for bots
 */
public class URLAction {
	public final static String[] urlArray = {
		//"http://goo.gl/04DrMs",
		//"http://goo.gl/ZxBGJT"
		"http://goo.gl/U9Eu8m"
	};
	private int urlLen = urlArray.length;
	private String[] urlAux = null;
	
	public final static String[] internship = {
		//"FIND INTERNSHIPS IN CALIFORNIA",
		//"A good website about internship!",
		"Internship salary reference!",
		//"PART TIME JOB IN USC",
		"INTERSHIP IN 2015"
	};
	
	public final static String[] tickets = {
		//"5 Free tickets about USC Football training match in tomorrow afternoon",
		//"DO YOU WANT TO SEE KOBE IN STAPLES CENTER？ SOME DISCOUNT FOR USC STUDENTS!!",
		"WANT TO SEE THE TROJANS BUT DO NOT HAVE TICKET? Do not worry!",
		"REPOST THIS STATUS, HAVING CHANCE TO GET LOS LAKERS TICKETS",
		"MAYBE USEFUL FOR SOMEONE WHO LIKE SWIMMING"
	};
	
	public final static String[] announcements = {
		"A LECTURE ABOUT COMPUTER SECURITY IN Nov 2",
		//"Here's a top 10 list of popular classes at USC. Students, alumni - what are yours?",
		//"SOME NOTICE FROME CAFETERIA",
		"HAPPY HOUR ON NEXT FRIDAY AFTERNOON",
		"SECURITY ALERT!!!"
	};
	
	public final static String[] psufootballclub = {
		"NEXT FRIDAY Nittany Lions FOOTBALL TRAINING MATCH TICKETS"
	};
	
	public final static String[] psualumni = {
		"internal referral for PSU Students"
	};
	
	public final static String[] happyvalley = {
		"Happy hour in this weekend"
	};
	
	public URLAction(){
		urlAux = new String[urlLen];
		for(int i=0;i<urlLen;i++){
			urlAux[i] = urlArray[i];
		}
	}
	
	private void copyTo(){
		urlLen = urlArray.length;
		urlAux = new String[urlLen];
		for(int i=0;i<urlLen;i++){
			urlAux[i] = urlArray[i];
		}
	}
	
	private boolean isEmpty(){
		return urlLen == 0;
	}
	
	private String getURLForStatus(){
		if(isEmpty()){
			copyTo();
		}
		int i = StdRandom.uniform(urlLen);
		String url = urlAux[i];
		if(i!=urlLen-1){
			urlAux[i] = urlAux[urlLen-1];
		}
		urlAux[urlLen-1] = null;
		urlLen--;
		return url;
	}
	
	public String getInternshipStatus(){
		String status = "";
		int i = StdRandom.uniform(internship.length);
		status = internship[i] + " " + getURLForStatus();
		return status;
	}
	
	public String getTicketStatus(){
		String status = "";
		int i = StdRandom.uniform(tickets.length);
		status = tickets[i] + " " + getURLForStatus();
		return status;
	}
	
	public String getAnnouncement(){
		String status = "";
		int i = StdRandom.uniform(announcements.length);
		status = announcements[i] + " " + getURLForStatus();
		return status;
	}
	
	public String getPSUFootballClub(){
		String status = "";
		int i = StdRandom.uniform(psufootballclub.length);
		status = psufootballclub[i]+" "+ getURLForStatus();
		return status;
	}
	
	public String getHappyValley(){
		String status = "";
		int i = StdRandom.uniform(happyvalley.length);
		status = happyvalley[i]+" "+getURLForStatus();
		return status;
	}
	
	public String getPSUAlumni(){
		String status = "";
		int i = StdRandom.uniform(psualumni.length);
		status = psualumni[i]+" "+getURLForStatus();
		return status;
	}
	/**
	 * URLAction Test
	 * @param args
	 */
	public static void main(String args[]){
		URLAction a = new URLAction();
		for(int i = 0;i<10;i++){
			System.out.println(a.getInternshipStatus());
			System.out.println(a.getTicketStatus());
			System.out.println(a.getAnnouncement());
		}
	}
}
