package com.omnihome.patterns.command;

import com.omnihome.patterns.observer.SmartAlarm;

public class ArmAlarmCommand implements Command {
    private SmartAlarm alarm;

    public ArmAlarmCommand(SmartAlarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.arm();
    }

    @Override
    public void undo() {
        alarm.disarm();
    }
}
