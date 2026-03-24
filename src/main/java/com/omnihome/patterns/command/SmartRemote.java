package com.omnihome.patterns.command;

import java.util.Stack;

public class SmartRemote {
    private Command[] slots = new Command[10];
    private Stack<Command> history = new Stack<>();

    public void setCommand(int slot, Command command) {
        if (slot >= 0 && slot < slots.length) {
            slots[slot] = command;
        }
    }

    public void pressButton(int slot) {
        if (slot >= 0 && slot < slots.length && slots[slot] != null) {
            slots[slot].execute();
            history.push(slots[slot]);
        } else {
            System.out.println("Slot " + slot + " is empty.");
        }
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}
