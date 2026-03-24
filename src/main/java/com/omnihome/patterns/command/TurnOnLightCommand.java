package com.omnihome.patterns.command;

import com.omnihome.patterns.factory.SmartLight;

public class TurnOnLightCommand implements Command {
    private SmartLight light;

    public TurnOnLightCommand(SmartLight light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}
