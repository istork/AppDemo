package com.wotingfm.main.listener;

import java.util.EventListener;
import java.util.EventObject;

/**监听器接口*/
public interface IListener extends EventListener {
	void handEvent(EventObject event);
}
