package com.jqy.server.csptl.chat;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.jqy.server.common.Constant;
import com.jqy.server.core.MyBuffer;
import com.jqy.server.core.protocol.AbsReqProtocol;
import com.jqy.server.core.protocol.AbsRespProtocol;
import com.jqy.server.entity.player.Player;
import com.jqy.server.service.IChatService;

/**
 * 聊天 请求协议
 * 
 * 此类协议都要使用原型模式
 * 
 * @author Simple
 * @date 2013-9-27 上午11:01:24
 * @Description TODO
 */
@Component
@Scope("prototype")
public class ChatReq extends AbsReqProtocol {

  private Logger log=Logger.getLogger(this.getClass());

  private static final byte TYPE=Constant.REQ;

  private static final short ID=0x0013;

  @Override
  public short getProtocolId() {
    return ID;
  }

  @Override
  public byte getProtocolType() {
    return TYPE;
  }

  @Resource
  private IChatService chatService;

  private byte type;

  private String message;

  @Override
  public void decode(MyBuffer buf) {
    // 数据解码
    type=buf.get();
    message=buf.getPrefixedString();
  }

  @Override
  public AbsRespProtocol execute(IoSession session, AbsReqProtocol req) {
    log.debug(String.format("chat execute"));
    Player p=getPlayer();
    String nickName=p.getNickName();
    String msg=String.format("[%s]说:%s", nickName, message);
    switch(type) {
      case Constant.CHAT_COMMON:
        chatService.sendMessage(msg);
        break;
      case Constant.CHAT_PRIVATE:
        break;
    }
    return null;
  }
}
