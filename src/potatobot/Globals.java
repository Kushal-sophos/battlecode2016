package potatobot;

import java.util.ArrayList;

import battlecode.common.*;

public class Globals 
{
	public static RobotController rc;
	public static MapLocation here;
	public static Team us;
	public static Team them;
	public static MapLocation[] ourInitialArchons;
	public static MapLocation[] theirInitialArchons;
	public static MapLocation theirInitialArchonCentre;
	public static int[] possibleDirections = {0, 1, -1, 2, -2, 3, -3, 4};
	static ArrayList<MapLocation> pastLocations = new ArrayList<MapLocation>();
	public static int myID;
	public static RobotType myType;
	public static int myAttackRadiusSquared;
	public static int mySensorRadiusSquared;
	public static final int INFINITY = 800;
	
	static void init(RobotController rcinit)
	{
		rc = rcinit;
		us = rc.getTeam();
		them = us.opponent();
		ourInitialArchons = rc.getInitialArchonLocations(us);
		theirInitialArchons = rc.getInitialArchonLocations(them);
		theirInitialArchonCentre = theirInitialArchons[0];
		for (int i = 1; i < theirInitialArchons.length; i++)
		{
			theirInitialArchonCentre = new MapLocation(theirInitialArchonCentre.x + theirInitialArchons[i].x, theirInitialArchonCentre.y + theirInitialArchons[i].y);
		}
		theirInitialArchonCentre = new MapLocation(theirInitialArchonCentre.x / theirInitialArchons.length, theirInitialArchonCentre.y / theirInitialArchons.length);
		myID = rc.getID();
		myType = rc.getType();
		myAttackRadiusSquared = myType.attackRadiusSquared;
		mySensorRadiusSquared = myType.sensorRadiusSquared;
	}
	
	static void updateLocation()
	{
		here = rc.getLocation();
	}
	
	public static RobotInfo[] joinRobotInfo(RobotInfo[] enemies1, RobotInfo[] enemies2) 
	{
		RobotInfo[] enemies = new RobotInfo[enemies1.length + enemies2.length];
		int index = 0;
		for (RobotInfo i: enemies1)
		{
			enemies[index++] = i;
		}
		for (RobotInfo i: enemies2)
		{
			enemies[index++] = i;
		}
		return enemies;
	}
	
	public static void moveAhead(Direction ahead)throws GameActionException
	{
		for (int i: possibleDirections)
		{
			Direction candidateDirection = Direction.values()[(ahead.ordinal() + i + 8) % 8];
			MapLocation candidateLocation = rc.getLocation().add(candidateDirection);
			if (rc.canMove(candidateDirection) && !pastLocations.contains(candidateLocation))
			{
				pastLocations.add(rc.getLocation());
				if (pastLocations.size() > 20)
					pastLocations.remove(0);
				rc.move(candidateDirection);
				return;
			}
		}
	}
}
