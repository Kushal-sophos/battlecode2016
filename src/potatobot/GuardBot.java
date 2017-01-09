package potatobot;
import battlecode.common.*;

public class GuardBot extends Globals
{
	static Direction moveDirection;
	static Direction buildDirection;
	static RobotType buildType[] = {RobotType.SCOUT, RobotType.SOLDIER, RobotType.GUARD, RobotType.TURRET};
	
	public static void loop()throws GameActionException
	{
		buildDirection = here.directionTo(theirInitialArchonCentre);
		moveDirection = buildDirection.opposite();
		int bt = 2;
		while (true)
		{
			if (rc.isCoreReady())
			{
				Globals.moveAhead(moveDirection);
			}
			Clock.yield();
		}
	}
}
