package com.jqy.server.csptl.user;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsRespProtocol;

public class RegUserResp extends AbsRespProtocol {

  private static final byte TYPE=Constant.RESP;

  private static final short ID=0x0002;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  private byte result;

  public RegUserResp(byte result) {
    this.result=result;
  }

  @Override
  public void encode(MyBuffer buf) {
    buf.put(result);
  }
}
