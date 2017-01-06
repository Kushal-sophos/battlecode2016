package sexyplayer;


import battlecode.common.*;

public class RobotPlayer
{
	static RobotController rc;
	static Direction movingDirection = Direction.NORTH_EAST;
	
	public static void run(RobotController rcIn)
	{
		rc = rcIn;
		if (rc.getTeam() == Team.B)
			movingDirection = Direction.SOUTH_WEST;
		while (true)
		{
			try
			{
				repeat();
				Clock.yield();
			}
			catch (GameActionException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void repeat() throws GameActionException
	{
		RobotInfo[] zombieEnemies = rc.senseNearbyRobots(rc.getType().attackRadiusSquared, Team.ZOMBIE);
		RobotInfo[] normalEnemies = rc.senseNearbyRobots(rc.getType().attackRadiusSquared, rc.getTeam().opponent());
		RobotInfo[] enemies = Utility.joinRobotInfo(zombieEnemies, normalEnemies);
		
		if (rc.getType().canAttack() && enemies.length > 0)
		{
			if (rc.isWeaponReady())
			{
				rc.attackLocation(enemies[0].location);
			}
		}
		else
		{
			if (rc.isCoreReady())
			{
				Utility.forwardish(rc, movingDirection);
			}
		}
	}
}