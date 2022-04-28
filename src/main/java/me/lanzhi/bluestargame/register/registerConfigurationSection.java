package me.lanzhi.bluestargame.register;

import java.util.HashMap;

import static me.lanzhi.bluestargame.BluestarGame.Data;
import static me.lanzhi.bluestargame.commands.mutedCommand.muted;
import static me.lanzhi.bluestargame.Type.Elevator.elevators;

public class registerConfigurationSection
{
    public static void registerConfigurationSections()
    {
        muted=Data.getConfigurationSection("muted");
        muted=(muted==null)?Data.createSection("muted"):muted;

        elevators=Data.getConfigurationSection("elevators");
        elevators=(elevators==null)?Data.createSection("elevators"):elevators;
    }
}
