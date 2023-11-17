/*
 * Copyright 2017 - 2019 Anton Tananaev (anton@traccar.org)
 * Copyright 2017 Anatoliy Golubev (darth.naihil@gmail.com)
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

public class AdmProtocolEncoder extends StringProtocolEncoder {

    public AdmProtocolEncoder(Protocol protocol) {
        super(protocol);
    }

    @Override
    protected Object encodeCommand(Command command) {

        switch (command.getType()) {
            case Command.TYPE_GET_DEVICE_STATUS:
                return formatCommand(command, "STATUS\r\n");

            case Command.TYPE_CUSTOM:
                return formatCommand(command, "%s\r\n", Command.KEY_DATA);

            case Command.TYPE_ENGINE_RESUME:
                return formatCommand(command, "OUTPUT 0,0\r\n");

            case Command.TYPE_ENGINE_STOP:
                return formatCommand(command, "OUTPUT 1,0\r\n");

            default:
                return null;
        }
    }

}
