import java.io.File;
import java.net.InetAddress;

public class ScheduleDemon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String groupid = null;
		
		if(args == null || args.length == 0) {
			groupid = "Manager";			
		}else {
			groupid = args[0];			
		}
		
		if(args !=null && args.length == 2 && args[1].equals("NOMANAGER")) {
			StaticValues.isCheckManager = false;
		}
		
		StaticValues.groupId = groupid;
		Util.printOut("Daemon init...");
		
		File f = new File(".project");
		if(f.exists()) {
			StaticValues.javaCommandLine = "cmd.exe /c start \"FAB ACS GetServer :group_id\" java -cp bin;libs/*; conf FABGetServer ";	//windows OS
			//StaticValues.javaCommandLine = "java -cp bin:libs/*conf GetServer";		//linux or Unix
		}else {
			StaticValues.javaCommandLine = "cmd.exe /c start \"FAB ACS GetServer :group_id\" java -jar FABGetServer.jar";
		}
		QueryLoader loader = new QueryLoader();
		if(loader.load() == false) {
			if(loader.isSqlLoad) {
				Util.printAndExit("접속에 실패하였습니다.");
			}else{
				Util.printAndExit("로딩에 실패하여 종료합니다.");
			}
		}
		
		if(groupid.equals("Manager")) {
			DaemonManager manager = new DaemonManager();
			manager.run();
		}else {
			try {
				StaticValues.hostName = InetAddress.getLocalHost().getHostName();
			}catch (Exception e) {
				// TODO: handle exception
				Util.printError(e.printStackTrace());
			}
			Dadmon daemon = new Daemon();
			daemon.start();
		}
	}
}
