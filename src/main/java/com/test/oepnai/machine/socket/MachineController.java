package com.test.oepnai.machine.socket;

import com.test.oepnai.machine.socket.dto.Instruction;
import com.test.oepnai.machine.socket.dto.Machine;
import org.springframework.stereotype.Component;


interface MachineController {
    String getImage(final Machine machine);
    void sendInstruction(final Machine machine, final Instruction instruction);
}
