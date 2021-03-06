/*
 *     This file is part of Telegram Server
 *     Copyright (C) 2015  Aykut Alparslan KOÇ
 *
 *     Telegram Server is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Telegram Server is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.telegram.tl.service;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.*;

public class rpc_error extends TLObject {

    public static final int ID = 0x2144ca19;

    public int error_code;
    public String error_message;

    public rpc_error() {

    }

    public rpc_error(int error_code, String error_message){
        this.error_code = error_code;
        this.error_message = error_message;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        error_code = buffer.readInt();
        error_message = buffer.readString();
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(32);
        serializeTo(buffer);
        return buffer;
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeInt(error_code);
        buff.writeString(error_message);
    }

    public int getConstructor() {
        return ID;
    }

    public static rpc_error UNAUTHORIZED() {
        return new rpc_error(401, "UNAUTHORIZED");
    }

    public static rpc_error BAD_REQUEST() {
        return new rpc_error(400, "BAD_REQUEST");
    }
}