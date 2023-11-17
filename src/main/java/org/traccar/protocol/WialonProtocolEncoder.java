/*
 * Copyright 2016 - 2019 Anton Tananaev (anton@traccar.org)
 * Copyright 2016 Andrey Kunitsyn (andrey@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.protocol;

import org.traccar.StringProtocolEncoder;
import org.traccar.model.Command;
import org.traccar.Protocol;

public class WialonProtocolEncoder extends StringProtocolEncoder {

    public WialonProtocolEncoder(Protocol protocol) {
        super(protocol);
    }

    @Override
    protected Object encodeCommand(Command command) {
        switch (command.getType()) {
            case Command.TYPE_REBOOT_DEVICE:
                return formatCommand(command, "reboot\r\n");
            case Command.TYPE_SEND_USSD:
                return formatCommand(command, "USSD:%s\r\n", Command.KEY_PHONE);
            case Command.TYPE_IDENTIFICATION:
                return formatCommand(command, "VER?\r\n");
            case Command.TYPE_OUTPUT_CONTROL:
                return formatCommand(command, "L%s=%s\r\n", Command.KEY_INDEX, Command.KEY_DATA);
            case Command.TYPE_ENGINE_STOP:
                return formatCommand(command, "OUTPUT0=1\r\n");
            case Command.TYPE_ENGINE_RESUME:
                return formatCommand(command, "OUTPUT0=0\r\n");
            default:
                return null;
        }
    }

}
