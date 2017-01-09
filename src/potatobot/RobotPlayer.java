package potatobot;

import battlecode.common.*;
/** Inspiration for code organisaion taken from future perfect,
 *  winners of Battlecode 2016 Finals.
 */


public class RobotPlayer extends Globals 
{

    /**
     * run() is the method that is called when a robot is instantiated in the Battlecode world.
     * If this method returns, the robot dies.
     * @throws Exception 
     **/
	
    @SuppressWarnings("unused")
    public static void run(RobotController rcinit) throws Exception 
    {
    	Globals.init(rcinit);
    	Globals.updateLocation();
		switch (rc.getType()) 
		{
		    case ARCHON:
				ArchonBot.loop();
		        break;	
		    case GUARD:
		    	GuardBot.loop();
		    	break;
		    
		   /*case SCOUT:
		    	ScoutBot.loop();
		    	break;
		    	
		    case SOLDIER:
		    	SoldierBot.loop();
		        break;
		        
		    case TURRET:
		    	TurretBot.loop();
		    	break;
		    	
		    case VIPER:
		    	ViperBot.loop();
		    	break;
		    default:
		    	throw new Exception("Unknown Type : " + rc.getType());
		    */	
		}
    }
}
