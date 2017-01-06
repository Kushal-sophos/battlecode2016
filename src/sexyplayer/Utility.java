package sexyplayer;

import java.util.ArrayList;

import battlecode.common.*;


public class Utility 
{
	
	static int[] possibleDirections = {0, 1, -1, 2, -2, 3, -3, 4};
	static ArrayList<MapLocation> pastLocations = new ArrayList<MapLocation>();
	static int patience = 48;
	
	public static void forwardish(RobotController rc, Direction ahead) throws GameActionException
	{
		for (int i: possibleDirections)
		{
			Direction candidateDirection = Direction.values()[(ahead.ordinal() + i + 8) % 8];
			MapLocation candidateLocation = rc.getLocation().add(candidateDirection);
			if (patience <= 0)
			{
				if (rc.canMove(candidateDirection))
				{
					rc.move(candidateDirection);
					patience += 2;
				}
				else
				{
					if (rc.senseRubble(candidateLocation) > GameConstants.RUBBLE_OBSTRUCTION_THRESH)
					{
						rc.clearRubble(candidateDirection);
					}
				}
				return;
			}
			if (rc.canMove(candidateDirection) && !pastLocations.contains(candidateLocation))
			{
				pastLocations.add(rc.getLocation());
				if (pastLocations.size() > 20)
					pastLocations.remove(0);
				rc.move(candidateDirection);
				return;
			}
		}
		patience -= 8;
	}
	
	public static RobotInfo[] joinRobotInfo(RobotInfo[] zombieEnemies, RobotInfo[] normalEnemies) 
	{
		RobotInfo[] enemies = new RobotInfo[zombieEnemies.length + normalEnemies.length];
		int index = 0;
		for (RobotInfo i: zombieEnemies)
		{
			enemies[index++] = i;
		}
		for (RobotInfo i: normalEnemies)
		{
			enemies[index++] = i;
		}
		return enemies;
	}
}
