import java.util.Scanner;


public class Updater {
	public static String CLIENT_DEST = "jms/Client";
	public static String CLIENT_FACTORY = "jms/ClientConnection";
	public static String UPDATER_DEST = "jms/Updater";
	public static String UPDATER_FACTORY = "jms/UpdaterConnection";
    
    /**
     * Start the ServerConsumer, getting the destination from the command line args
     */
    public static void main(String [] args){  
        
        // create a publisher (topic sender)
        ServerProducer spub = new ServerProducer();    
        
        // create the ServerSubscriber (topic receiver)
        ServerSubscriber scon = new ServerSubscriber();
        scon.start(spub);
        
        //loop this until user enters input
        Scanner s = new Scanner(System.in);
        System.out.println("To exit, type 'exit' then hit enter.");
        
        while(!s.hasNext()) {
        }
        
        scon.close();
    }
}
